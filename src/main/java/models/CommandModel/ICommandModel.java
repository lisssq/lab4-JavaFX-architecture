package models.CommandModel;

import models.CpuModel.InstuctionNames;

public interface ICommandModel
{
    InstuctionNames getName();

    String[] getArgs();
}
