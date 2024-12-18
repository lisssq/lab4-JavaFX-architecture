package models.ExecuterModel;

import Observer.IObserver;

public interface IExecuterModel
{
    void next() throws Exception;

    void addObserver(IObserver e);

    int getIteration();     // получение текущего индекса инструкции

    void discard();         // отмена текущего состояния и сброс

    ExecuterState getState();   // получение текущего состояния исполнителя
}