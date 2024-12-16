package models.ExecuterModel;

public class BExecuterModel
{
    static IExecuterModel executerModel = new ExecuterModel();

    public static IExecuterModel build()
    {
        return executerModel;
    }
}
