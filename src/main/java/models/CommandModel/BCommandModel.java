package models.CommandModel;

public class BCommandModel
{
    public static ICommandModel build(String _command)
    {
        return new CommandModel(_command);
    }
}
