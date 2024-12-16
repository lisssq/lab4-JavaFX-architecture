package com.example.assembly_javafx;

import Observer.IObserver;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import models.CpuModel.BCpuModel;
import models.CpuModel.ICpuModel;
import models.ExecuterModel.IExecuterModel;
import models.ProgramModel.IProgramModel;

public class RegistersStateController implements IObserver
{
    ICpuModel cpuModel = BCpuModel.build();

    @FXML
    Label register1;

    @FXML
    Label register2;

    @FXML
    Label register3;

    @FXML
    Label register4;

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
    public void event(ICpuModel c)
    {
        register1.setText(String.valueOf(c.getRegisters().get("r1")));
        register2.setText(String.valueOf(c.getRegisters().get("r2")));
        register3.setText(String.valueOf(c.getRegisters().get("r3")));
        register4.setText(String.valueOf(c.getRegisters().get("r4")));
    }

    @Override
    public void event(IExecuterModel e)
    {
    }
}
