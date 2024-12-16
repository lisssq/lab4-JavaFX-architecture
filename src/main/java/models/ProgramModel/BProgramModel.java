package models.ProgramModel;

public class BProgramModel
{
    static IProgramModel programModel = new ProgramModel();

    public static IProgramModel build()
    {
        return programModel;
    }
}
