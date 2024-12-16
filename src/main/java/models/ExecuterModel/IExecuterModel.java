package models.ExecuterModel;

import Observer.IObserver;

public interface IExecuterModel
{
    void next() throws Exception;

    void addObserver(IObserver e);

    int getIteration();

    void discard();

    ExecuterState getState();
}