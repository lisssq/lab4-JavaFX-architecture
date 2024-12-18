package models.ProgramModel;

import Observer.IObserver;
import models.CommandModel.ICommandModel;
import models.CpuModel.InstuctionNames;

import java.util.ArrayList;
import java.util.Map;

public interface IProgramModel
{
    void add(ICommandModel command);

    void remove(int i) throws Exception;        // удаление команды по индексу

    int size();         // кол-во команд

    ICommandModel get(int i) throws Exception;      // получение команд по индексу

    InstuctionNames getPopularInstructionName();

    Integer[] getRangeOfMemory();

    ArrayList<Map.Entry<InstuctionNames, Integer>> getSortedCommandsCount();

    void swap(int i, int j);

    void addObserver(IObserver e);
}
