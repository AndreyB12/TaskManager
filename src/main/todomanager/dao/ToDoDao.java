package todomanager.dao;

import todomanager.model.Status;
import todomanager.model.ToDoTask;

import java.util.List;

/**
 * Created by butkoav on 24.02.2017.
 */
public interface ToDoDao {
    public void addToDo(ToDoTask toDoTask);

    public void updateToDo(ToDoTask toDoTask);

    public void removeToDo(int id);

    public ToDoTask getToDoById(int id);

    public List<ToDoTask> listToDo(int[] statuses);

    public List<ToDoTask> listLastToDo(int count, int[] statuses);

    public List<ToDoTask> listToDoNext(int id, int count, int[] statuses);

    public List<ToDoTask> listToDoPrev(int id, int count, int[] statuses);

    public int countToDo();

    public int countToDo(int[] statuses);

    public int countToDoBeforeId(int id, int[] statuses);

    public List<Status> listStatus();


}
