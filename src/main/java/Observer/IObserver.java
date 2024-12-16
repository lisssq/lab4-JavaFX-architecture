package Observer;

import models.CpuModel.ICpuModel;
import models.ExecuterModel.IExecuterModel;
import models.ProgramModel.IProgramModel;

public interface IObserver
{
    void event(IProgramModel m);

    void event(ICpuModel c);

    void event(IExecuterModel e);
}
