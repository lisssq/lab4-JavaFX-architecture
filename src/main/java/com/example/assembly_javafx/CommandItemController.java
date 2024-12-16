package com.example.assembly_javafx;

import Observer.IObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import models.CpuModel.ICpuModel;
import models.ExecuterModel.BExecuterModel;
import models.ExecuterModel.IExecuterModel;
import models.ProgramModel.BProgramModel;
import models.ProgramModel.IProgramModel;

public class CommandItemController implements IObserver {
    IProgramModel programModel = BProgramModel.build();
    IExecuterModel executerModel = BExecuterModel.build();

    int index;
    @FXML
    Label commandNameString;
    @FXML
    Label commandArgsString;
    @FXML
    Button deleteButton;
    @FXML
    Button moveUpButton;
    @FXML
    Button moveDownButton;

    int getIndex() {
        return index;
    }

    void setIndex(int _index) {
        index = _index;
    }

    @FXML
    void initialize() {
        executerModel.addObserver(this);
    }

    void setCommandNameString(String _commandNameString) {
        commandNameString.setText(_commandNameString);
    }

    void setCommandArgsString(String _commandArgsString) {
        commandArgsString.setText(_commandArgsString);
    }

    @FXML
    void handleDeleteButtonClick() throws Exception {
        programModel.remove(index);
    }

    @FXML
    void handleMoveUpButtonClick() throws Exception {
        programModel.swap(index, index - 1);
    }

    @FXML
    void handleMoveDownButtonClick() throws Exception {
        programModel.swap(index, index + 1);
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
            case IDE -> {
                moveDownButton.setDisable(false);
                moveUpButton.setDisable(false);
                deleteButton.setDisable(false);
            }
            case ENDED, RUNNING -> {
                moveDownButton.setDisable(true);
                moveUpButton.setDisable(true);
                deleteButton.setDisable(true);
            }
        }

        if (executerModel.getIteration() == index) {
            commandNameString.setTextFill(Color.RED);
            commandArgsString.setTextFill(Color.RED);
        } else {
            commandNameString.setTextFill(Color.BLACK);
            commandArgsString.setTextFill(Color.BLACK);
        }
    }
}
