package com.example.assembly_javafx;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.ScrollPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;

public class MainProgramController
{

    @FXML
    GridPane main;

    @FXML
    void initialize() throws Exception
    {
        // создаем контроллеры для всех компонентов, которые мы будем загружать
        ControlPanelController controlPanelController = new ControlPanelController();
        FXMLLoader controlPanelLoader = new FXMLLoader(app.class.getResource("ControlPanel.fxml"));

        CommandListController commandListController = new CommandListController();
        FXMLLoader commandListLoader = new FXMLLoader(app.class.getResource("CommandList.fxml"));

        MemoryStateController memoryStateController = new MemoryStateController();
        FXMLLoader memoryStateLoader = new FXMLLoader(app.class.getResource("MemoryState.fxml"));

        RegistersStateController registersStateController = new RegistersStateController();
        FXMLLoader registersStateLoader = new FXMLLoader(app.class.getResource("RegistersState.fxml"));

        MostPopularCommandsController mostPopularCommandsController = new MostPopularCommandsController();
        FXMLLoader mostPopularCommandsLoader = new FXMLLoader(app.class.getResource("MostPopularCommands.fxml"));

        try
        {
            Pane controlPanel = controlPanelLoader.load();
            main.add(controlPanel, 0, 0);

            ScrollPane commandList = commandListLoader.load();
            main.add(commandList, 0, 1, 1, 2);

            Pane memoryState = memoryStateLoader.load();
            main.add(memoryState, 1, 2);

            Pane registersState = registersStateLoader.load();
            main.add(registersState, 1, 0);

            Pane mostPopularCommands = mostPopularCommandsLoader.load();
            main.add(mostPopularCommands, 1, 1);

        }
        catch (Exception e)
        {
            throw new Exception(e);
        }
    }
}