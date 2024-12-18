package models.CommandModel;
import models.CpuModel.InstuctionNames;
// интерфейс: любая команда, которая использует
// интерфейс, должна предоставлять два содержащихся
// тут метода - getName() и getArgs()

public interface ICommandModel
{
    InstuctionNames getName();

    String[] getArgs();
}
