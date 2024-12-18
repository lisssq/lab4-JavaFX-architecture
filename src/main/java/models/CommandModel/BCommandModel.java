package models.CommandModel;

public class BCommandModel
{   // вызывая метод build для какой-то инструкции, например
    // BCommandModel.build("MOV r1, r2"), мы получим CommandModel который
    // будет содержать name = MOV и args = r1, r2
    public static ICommandModel build(String _command)
    {
        return new CommandModel(_command);
    }
}
