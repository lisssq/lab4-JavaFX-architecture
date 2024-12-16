package models.CpuModel;

import Observer.IObserver;
import models.CommandModel.ICommandModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class CpuModel implements ICpuModel
{
    private final Map<String, Integer> registers = new HashMap<>();
    ArrayList<IObserver> observers = new ArrayList<>();
    private int[] memo = new int[1024];

    public CpuModel()
    {
        setDefaultRegistersState();
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

    private void setDefaultRegistersState()
    {
        registers.put("r1", 0);
        registers.put("r2", 0);
        registers.put("r3", 0);
        registers.put("r4", 0);

        notifyObservers();
    }

    public Map<String, Integer> getRegisters()
    {
        return registers;
    }

    public void setDefaultCpuState()
    {
        setDefaultRegistersState();
        memo = new int[1024];

        notifyObservers();
    }

    public int[] getMemo()
    {
        return memo;
    }

    public void execute(ICommandModel c)
    {
        InstuctionNames name = c.getName();
        String[] arguments = c.getArgs();

        switch (name)
        {
            case InstuctionNames.init: // инициализация памяти по адресу значением
                memo[Integer.parseInt(arguments[0])] = Integer.parseInt(arguments[1]);
                break;
            case InstuctionNames.mv: // копирование из регистра в регистр
                registers.put(arguments[0], registers.get(arguments[1]));
                break;
            case InstuctionNames.ld: // загрузка данных из памяти в регистр
                registers.put(arguments[0], memo[Integer.parseInt(arguments[1])]);
                break;
            case InstuctionNames.st: // загрузка данных из регистра в память
                memo[Integer.parseInt(arguments[1])] = registers.get(arguments[0]);
                break;
            case InstuctionNames.mult: // берет значение в регистре "r1" умножает с значением из регистра "r2" и записывает в "r4"
                registers.put("r4", registers.get("r1") * registers.get("r2"));
                break;
            case InstuctionNames.div: // берет значение в регистре "r1" делит на значение из регистра "r2" и записывает в "r4"
                registers.put("r4", registers.get("r1") / registers.get("r3"));
                break;
            case InstuctionNames.add: // берет значение в регистре "r1" складывают с значением из регистра "r2" и записывает в "r4"
                registers.put("r4", registers.get("r1") + registers.get("r2"));
                break;
            case InstuctionNames.sub: // берет значение в регистре "r1" вычитает из него значение из регистра "r2" и записывает в "r4"
                registers.put("r4", registers.get("r1") - registers.get("r2"));
                break;
            case InstuctionNames.print:
                System.out.println(registers);
                break;
            default:
                System.out.println("Неизвестная команда");
        }
        notifyObservers();
    }
}

