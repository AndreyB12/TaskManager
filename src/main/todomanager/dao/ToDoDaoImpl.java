package todomanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Repository;
import todomanager.model.Status;
import todomanager.model.ToDoTask;

import java.util.List;

/**
 * Created by butkoav on 24.02.2017.
 */
@Repository
public class ToDoDaoImpl implements ToDoDao {
    private static final Logger logger = LoggerFactory.getLogger(ToDoDaoImpl.class);

    private SessionFactory sessionFactory;

    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    public void addToDo(ToDoTask toDoTask) {
        Session session = this.sessionFactory.getCurrentSession();
        session.persist(toDoTask);
        logger.info("ToDo added successfully. ToDoTask: " + toDoTask);
    }

    public void updateToDo(ToDoTask toDoTask) {
        Session session = this.sessionFactory.getCurrentSession();
        session.update(toDoTask);
        logger.info("ToDo updated successfully. ToDoTask: " + toDoTask);
    }

    public void removeToDo(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ToDoTask toDoTask = (ToDoTask) session.load(ToDoTask.class, new Integer(id));
        if (toDoTask != null) session.delete(toDoTask);

        logger.info("ToDoTask successfully deleted. ToDoTask: " + toDoTask);
    }

    public ToDoTask getToDoById(int id) {
        Session session = this.sessionFactory.getCurrentSession();
        ToDoTask toDoTask = (ToDoTask) session.load(ToDoTask.class, new Integer(id));
        logger.info("ToDoTask successfully loaded. ToDoTask: " + toDoTask);
        return toDoTask;
    }

    @SuppressWarnings("unchecked")
    public List<ToDoTask> listToDo(int[] statuses) {
        String query = "from ToDoTask";
        if (statuses != null && statuses.length > 0) {
            query = query + " where status = " + statuses[0];
            for (int i = 1; i < statuses.length; i++) {
                query = query + " or status = " + statuses[i];
            }
        }
        Session session = sessionFactory.getCurrentSession();
        List<ToDoTask> toDoList = session.createQuery(query).list();

        for (ToDoTask toDoTask : toDoList) {
            logger.info("ToDoTask successfully loaded. ToDoTask: " + toDoTask);
        }
        return toDoList;
    }

    @SuppressWarnings("unchecked")
    public List<Status> listStatus() {
        Session session = sessionFactory.getCurrentSession();
        List<Status> statuses = session.createQuery("from Status").list();
        return statuses;
    }
}
