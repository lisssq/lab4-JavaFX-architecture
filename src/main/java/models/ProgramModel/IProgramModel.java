package models.ProgramModel;

import Observer.IObserver;
import models.CommandModel.ICommandModel;
import models.CpuModel.InstuctionNames;

import java.util.ArrayList;
import java.util.Map;

public interface IProgramModel {
    void add(ICommandModel command);

    void remove(int i) throws Exception;

    int size();

    ICommandModel get(int i) throws Exception;

    InstuctionNames getPopularInstructionName();

    Integer[] getRangeOfMemory();

    ArrayList<Map.Entry<InstuctionNames, Integer>> getSortedCommandsCount();

    void swap(int i, int j);

    void addObserver(IObserver e);
}
