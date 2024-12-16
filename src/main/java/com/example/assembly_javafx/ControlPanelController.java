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

public class ControlPanelController implements IObserver {
    IProgramModel programModel = BProgramModel.build();
    IExecuterModel executerModel = BExecuterModel.build();

    @FXML
    Button addButton;

    @FXML
    TextField commandTextField;

    @FXML
    Button nextButton;

    @FXML
    void initialize() {
        executerModel.addObserver(this);
    }

    @FXML
    void handleAddButtonClick() {
        String commandString = commandTextField.getText();

        if (commandString.isEmpty()) return;

        ICommandModel command = BCommandModel.build(commandString);
        programModel.add(command);

        commandTextField.clear();
    }

    @FXML
    void handleNextButtonClick() throws Exception {
        executerModel.next();
    }

    @FXML
    void handleDiscardButtonClick() throws Exception {
        executerModel.discard();
    }

    @Override
    public void event(IProgramModel m) {
    }

    @Override
    public void event(ICpuModel c) {
    }

    @Override
    public void event(IExecuterModel e) {
        switch (executerModel.getState()) {
            case ExecuterState.IDE -> {
                nextButton.setText("Начать программу");
                nextButton.setDisable(false);
                addButton.setDisable(false);
                commandTextField.setDisable(false);
            }
            case ExecuterState.RUNNING -> {
                nextButton.setText("Выполнить следущую");
                addButton.setDisable(true);
                commandTextField.setDisable(true);
            }
            case ExecuterState.ENDED -> {
                nextButton.setDisable(true);
                addButton.setDisable(true);
                commandTextField.setDisable(true);
            }
        }

    }
}
