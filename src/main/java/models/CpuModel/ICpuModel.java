package models.CpuModel;

import Observer.IObserver;
import models.CommandModel.ICommandModel;

import java.util.Map;

public interface ICpuModel {
    void execute(ICommandModel c);

    void setDefaultCpuState();

    void addObserver(IObserver e);

    int[] getMemo();

    Map<String, Integer> getRegisters();
}

