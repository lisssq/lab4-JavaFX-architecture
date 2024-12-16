package com.example.assembly_javafx;

import Observer.IObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.CpuModel.ICpuModel;
import models.CpuModel.InstuctionNames;
import models.ExecuterModel.IExecuterModel;
import models.ProgramModel.BProgramModel;
import models.ProgramModel.IProgramModel;

import java.util.ArrayList;
import java.util.Map;

public class MostPopularCommandsController implements IObserver {
    IProgramModel programModel = BProgramModel.build();

    @FXML
    GridPane mostPopularCommands;

    @FXML
    void initialize() {
        programModel.addObserver(this);
    }

    @Override
    public void event(IProgramModel m) {
        ArrayList<Map.Entry<InstuctionNames, Integer>> sortedCommandCountList = m.getSortedCommandsCount();
        mostPopularCommands.getChildren().clear();

        for (int i = 0; i < sortedCommandCountList.size(); i++) {
            Map.Entry<InstuctionNames, Integer> command = sortedCommandCountList.get(i);

            Label commandName = new Label();
            Label commandCount = new Label();

            commandName.setText(String.valueOf(command.getKey()));
            commandCount.setText(String.valueOf(command.getValue()));

            mostPopularCommands.add(commandName, 0, i);
            mostPopularCommands.add(commandCount, 1, i);
        }
    }

    @Override
    public void event(ICpuModel c) {
    }

    @Override
    public void event(IExecuterModel e) {
    }
}
