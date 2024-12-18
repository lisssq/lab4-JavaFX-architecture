package models.CommandModel;

import models.CpuModel.InstuctionNames;

public class CommandModel implements ICommandModel
{
    private final InstuctionNames name;
    private final String[] arguments;

    public CommandModel(String c)       // конструктор, разбивающий строку с инструкцией
    {                                   // (например: mv r1, r2) на части, таким образом отделяя имя команды от аргументов
        String[] sections = c.split(" ");

        name = InstuctionNames.valueOf(sections[0]);
        arguments = new String[sections.length - 1];

        System.arraycopy(sections, 1, arguments, 0, arguments.length);
    }

    public InstuctionNames getName()
    {
        return name;
    }

    public String[] getArgs()
    {
        return arguments;
    }
}
