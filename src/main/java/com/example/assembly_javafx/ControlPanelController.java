package com.example.assembly_javafx;

import Observer.IObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import models.CommandModel.BCommandModel;
import models.CommandModel.ICommandModel;
import models.CpuModel.ICpuModel;
import models.ExecuterModel.BExecuterModel;
import models.ExecuterModel.ExecuterState;
import models.ExecuterModel.IExecuterModel;
import models.ProgramModel.BProgramModel;
import models.ProgramModel.IProgramModel;

public class ControlPanelController implements IObserver
{
    IProgramModel programModel = BProgramModel.build();
    IExecuterModel executerModel = BExecuterModel.build();

    @FXML
    Button addButton;

    @FXML
    TextField commandTextField;     // текстовое поле в которую пользователь вводит команду

    @FXML
    Button nextButton;

    @FXML
    void initialize()
    {
        executerModel.addObserver(this);
    }

    @FXML
    void handleAddButtonClick()     // при нажатии на кнопку добавления инструкции
    {
        String commandString = commandTextField.getText();  // получаем текст из текстового поля

        if (commandString.isEmpty())
        {
            return;     // если пустая строка, то ничего не делаем
        }

        ICommandModel command = BCommandModel.build(commandString); // иначе создаем новую команду и добавляем в модель
        programModel.add(command);

        commandTextField.clear();
    }

    @FXML
    void handleNextButtonClick() throws Exception       // при нажатии на кнопку следующей инструкции
    {
        executerModel.next();
    }

    @FXML
    void handleDiscardButtonClick() throws Exception    // при нажатии на кнопку !отмена!
    {
        executerModel.discard();
    }

    @Override
    public void event(IProgramModel m)
    {
    }

    @Override
    public void event(ICpuModel c)
    {
    }

    @Override
    public void event(IExecuterModel e)
    {
        switch (executerModel.getState())
        {
            case ExecuterState.IDE ->       // если готово к выполнению, то можно тыкать на кнопки
            {
                nextButton.setText("Начать программу");
                nextButton.setDisable(false);
                addButton.setDisable(false);
                commandTextField.setDisable(false);
            }
            case ExecuterState.RUNNING ->       // если выполняется, то уже нельзя
            {
                nextButton.setText("Выполнить следущую");
                addButton.setDisable(true);
                commandTextField.setDisable(true);
            }
            case ExecuterState.ENDED ->
            {
                nextButton.setDisable(true);
                addButton.setDisable(true);
                commandTextField.setDisable(true);
            }
        }

    }
}
