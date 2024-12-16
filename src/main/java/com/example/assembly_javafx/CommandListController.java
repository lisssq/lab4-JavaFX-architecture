package com.example.assembly_javafx;

import Observer.IObserver;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import models.CpuModel.ICpuModel;
import models.ExecuterModel.IExecuterModel;
import models.ProgramModel.BProgramModel;
import models.ProgramModel.IProgramModel;

import java.util.Arrays;

public class CommandListController implements IObserver {
    @FXML
    GridPane commandList;
    IProgramModel programModel = BProgramModel.build();

    @FXML
    void initialize() {
        programModel.addObserver(this);
    }

    @Override
    public void event(IProgramModel m) {
        commandList.getChildren().clear();

        for (int i = 0; i < programModel.size(); i++) {
            try {
                CommandItemController commandItemController = new CommandItemController();

                FXMLLoader commandItemLoader = new FXMLLoader(app.class.getResource("CommandItem.fxml"));
                commandItemLoader.setController(commandItemController);
                Pane commandItem = commandItemLoader.load();

                commandItemController.setIndex(i);
                commandItemController.setCommandNameString(String.valueOf(programModel.get(i).getName()));
                commandItemController.setCommandArgsString(Arrays.toString(programModel.get(i).getArgs()));

                commandList.addColumn(programModel.size(), commandItem);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    public void event(ICpuModel c) {
    }

    @Override
    public void event(IExecuterModel e) {
    }
}
