package com.example.assembly_javafx;

import Observer.IObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import models.CpuModel.BCpuModel;
import models.CpuModel.ICpuModel;
import models.ExecuterModel.IExecuterModel;
import models.ProgramModel.IProgramModel;

public class MemoryStateController implements IObserver
{
    ICpuModel cpuModel = BCpuModel.build();         // модель процессора для взаимодействия с контроллером

    @FXML
    GridPane memoryList;

    @FXML
    public void initialize()
    {
        cpuModel.addObserver(this);
    }

    @Override
    public void event(IProgramModel m)
    {
    }

    @Override
    public void event(ICpuModel c)          // вызываем когда процессор ICpuModel обновляется
    {
        memoryList.getChildren().clear();
        int[] memory = cpuModel.getMemo();      // возвращаем массив чисел которые представляют состояние памяти

        for (int i = 0; i < memory.length; i = i + 4)       // каждая строка содержит по 4 значения памяти
        {
            for (int j = 0; j < 4; j++)
            {
                Label memoryCellLabel = new Label();
                memoryCellLabel.setText(i + j + ":" + memory[i + j]);

                memoryList.add(memoryCellLabel, j, i);
            }
        }
    }

    @Override
    public void event(IExecuterModel e)
    {
    }
}
