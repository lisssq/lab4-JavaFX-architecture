package models.ProgramModel;

import Observer.IObserver;
import models.CommandModel.BCommandModel;
import models.CommandModel.ICommandModel;
import models.CpuModel.InstuctionNames;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Iterator;
import java.util.Map;
import java.util.stream.Collectors;

public class ProgramModel implements Iterable<ICommandModel>, IProgramModel
{
    private final ArrayList<ICommandModel> commands;
    ArrayList<IObserver> observers = new ArrayList<>();
    private int minMem = 1025;
    private int maxMem = -1;

    public ProgramModel()
    {
        commands = new ArrayList<ICommandModel>();

        commands.add(BCommandModel.build("init 10 20"));
        commands.add(BCommandModel.build("init 11 25"));
        commands.add(BCommandModel.build("init 12 5"));
        commands.add(BCommandModel.build("ld r1 10"));
        commands.add(BCommandModel.build("ld r2 11"));
        commands.add(BCommandModel.build("ld r3 12"));
        commands.add(BCommandModel.build("add"));
        commands.add(BCommandModel.build("print")); // 20 25 5 45
        commands.add(BCommandModel.build("mv r1 r4"));
        commands.add(BCommandModel.build("mv r2 r3"));
        commands.add(BCommandModel.build("div"));
        commands.add(BCommandModel.build("print"));  // 45 5 5 9

        notifyObservers();
    }

    public ProgramModel(ArrayList<ICommandModel> _commands)
    {
        commands = _commands;

        notifyObservers();
    }

    void notifyObservers()
    {
        observers.forEach(action -> action.event(this));
    }

    public void addObserver(IObserver e)
    {
        observers.add(e);

        notifyObservers();
    }

    public void add(ICommandModel command)
    {
        commands.add(command);

        if (command.getName().equals(InstuctionNames.init))
        {
            int memory = Integer.parseInt(command.getArgs()[0]);

            minMem = Math.min(minMem, memory);
            maxMem = Math.max(maxMem, memory);
        }

        notifyObservers();
    }

    public ICommandModel get(int i) throws Exception
    {
        if (i < 0 || i > commands.size()) {
            throw new Exception("index is out of range");
        }

        return commands.get(i);
    }

    public void remove(int i) throws Exception
    {
        if (i < 0 || i > commands.size()) {
            throw new Exception("index is out of range");
        }

        commands.remove(i);

        notifyObservers();
    }

    public int size()
    {
        return commands.size();
    }

    public ArrayList<Map.Entry<InstuctionNames, Integer>> getSortedCommandsCount()
    {
        return commands.stream().collect(Collectors.groupingBy(
                        ICommandModel::getName,
                        Collectors.summingInt(_ -> 1)))
                .entrySet().stream()
                .sorted(Comparator.comparingInt(Map.Entry::getValue))
                .collect(Collectors.toCollection(ArrayList::new));
    }

    public InstuctionNames getPopularInstructionName()
    {
        return getSortedCommandsCount().getLast().getKey();
    }

    public Integer[] getRangeOfMemory()
    {
        return new Integer[]{minMem, maxMem};
    }

    public void swap(int i, int j)
    {
        ICommandModel temp = commands.get(i);

        commands.set(i, commands.get(j));
        commands.set(j, temp);

        notifyObservers();
    }

    @Override
    public Iterator<ICommandModel> iterator()
    {
        return commands.iterator();
    }
}
