package todomanager.services;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import todomanager.dao.ToDoDao;
import todomanager.model.Status;
import todomanager.model.ToDoTask;

import java.util.Date;
import java.util.List;

/**
 * Created by butkoav on 24.02.2017.
 */
@Service
public class ToDoServiceImpl implements ToDoService {
    private ToDoDao toDoDao;

    public void setToDoDao(ToDoDao toDoDao) {
        this.toDoDao = toDoDao;
    }

    @Transactional
    public void addToDo(ToDoTask toDoTask) {
        if (toDoTask.getStatus() == 0) toDoTask.setStatus(1);
        if (toDoTask.getCreateDate() == null) toDoTask.setCreateDate(new Date());
        toDoDao.addToDo(toDoTask);
    }

    @Transactional
    public void updateToDo(ToDoTask toDoTask) {
        toDoDao.updateToDo(toDoTask);
    }

    @Transactional
    public void removeToDo(int id) {
        this.toDoDao.removeToDo(id);
    }

    @Transactional
    public ToDoTask getToDoById(int id) {
        return this.toDoDao.getToDoById(id);
    }

    @Transactional
    public List<ToDoTask> listToDo() {
        return this.toDoDao.listToDo(null);
    }

    @Transactional
    public List<ToDoTask> listToDo(int[] statuses) {
        return this.toDoDao.listToDo(statuses);
    }

    @Transactional
    public List<ToDoTask> listLastToDo(int count, int[] statuses) {
        return this.toDoDao.listLastToDo(count,statuses);
    }

    @Transactional
    public List<ToDoTask> listToDoNext(int startId, int count, int[] statuses) {
        return this.toDoDao.listToDoNext(startId, count, statuses);
    }

    @Transactional
    public List<ToDoTask> listToDoPrev(int startId, int count, int[] statuses) {
        return this.toDoDao.listToDoPrev(startId, count, statuses);
    }


    @Transactional
    public int countRows(int[] statuses) {
        return this.toDoDao.countToDo(statuses);
    }

    @Transactional
    public int countRowsBefore(int id, int[] statuses) {
        return this.toDoDao.countToDoBeforeId(id, statuses);
    }

    @Transactional
    public List<Status> listStatus() {
        return this.toDoDao.listStatus();
    }
}
