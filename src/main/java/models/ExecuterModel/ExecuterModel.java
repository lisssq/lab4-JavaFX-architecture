package models.ExecuterModel;

import Observer.IObserver;
import models.CpuModel.BCpuModel;
import models.CpuModel.ICpuModel;
import models.ProgramModel.BProgramModel;
import models.ProgramModel.IProgramModel;

import java.util.ArrayList;

public class ExecuterModel implements IExecuterModel
{
    private final ICpuModel cpuModel = BCpuModel.build();
    private final IProgramModel programModel = BProgramModel.build();
    ArrayList<IObserver> observers = new ArrayList<>();
    private int iteration = -1;
    private ExecuterState state = ExecuterState.IDE;

    void notifyObservers()
    {
        observers.forEach(action -> action.event(this));
    }

    public void addObserver(IObserver e)
    {
        observers.add(e);

        notifyObservers();
    }

    public void next() throws Exception
    {
        iteration++;
        setState(ExecuterState.RUNNING);

        if (iteration + 1 <= programModel.size())
        {
            cpuModel.execute(programModel.get(iteration));
        } else {
            setState(ExecuterState.ENDED);
        }

        notifyObservers();
    }

    public void discard()
    {
        setState(ExecuterState.IDE);
        setIteration(-1);

        cpuModel.setDefaultCpuState();

        notifyObservers();
    }

    public int getIteration()
    {
        return iteration;
    }

    public void setIteration(int _iteration)
    {
        iteration = _iteration;
    }

    public ExecuterState getState()
    {
        return state;
    }

    public void setState(ExecuterState _state)
    {
        state = _state;
    }
}
