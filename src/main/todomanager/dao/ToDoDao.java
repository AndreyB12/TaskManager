package todomanager.dao;

import todomanager.model.Status;
import todomanager.model.ToDoTask;

import java.util.List;
import java.util.Map;

/**
 * Created by butkoav on 24.02.2017.
 */
public interface ToDoDao {
    public void addToDo(ToDoTask toDoTask);

    public void updateToDo(ToDoTask toDoTask);

    public void removeToDo(int id);

    public ToDoTask getToDoById(int id);

    public List<ToDoTask> listToDo(int[] statuses);

    public List<Status> listStatus();


}
