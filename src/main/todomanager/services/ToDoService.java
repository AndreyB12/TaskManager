package todomanager.services;

import todomanager.model.Status;
import todomanager.model.ToDoTask;

import java.util.List;

/**
 * Created by butkoav on 24.02.2017.
 */
public interface ToDoService {
    public void addToDo(ToDoTask toDoTask);

    public void updateToDo(ToDoTask toDoTask);

    public void removeToDo(int id);

    public ToDoTask getToDoById(int id);

    public List<ToDoTask> listToDo(int[] statuses);
    public List<ToDoTask> listLastToDo(int count,int[] statuses);

    public List<ToDoTask> listToDoNext(int startId, int count, int[] statuses);
    public List<ToDoTask> listToDoPrev(int startId, int count, int[] statuses);

    public int countRows(int[] statuses);

    public int countRowsBefore(int id, int[] statuses);

    public List<Status> listStatus();
}