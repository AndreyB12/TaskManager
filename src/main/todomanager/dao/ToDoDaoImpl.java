package todomanager.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
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
        return listToDoNext(0, 0, statuses);
    }

    @SuppressWarnings("unchecked")
    public List<ToDoTask> listLastToDo(int count,int[] statuses) {
        String query = "from ToDoTask ";
        if (statuses != null && statuses.length > 0) {
            query = query + " where ( status = " + statuses[0];
            for (int i = 1; i < statuses.length; i++) {
                query = query + " or status = " + statuses[i];
            }
            query = query + ") order by id desc";
        }

        Session session = this.sessionFactory.getCurrentSession();
        Query query1 = session.createQuery(query);

        if (count > 0) query1.setMaxResults(count);

        List<ToDoTask> toDoList = query1.list();

        for (ToDoTask toDoTask : toDoList) {
            logger.info("ToDoTask successfully loaded. ToDoTask: " + toDoTask);
        }
        return toDoList;
    }

    @SuppressWarnings("unchecked")
    public List<ToDoTask> listToDoNext(int id, int count, int[] statuses) {
        String query = "from ToDoTask where id>=:id";
        if (statuses != null && statuses.length > 0) {
            query = query + " and ( status = " + statuses[0];
            for (int i = 1; i < statuses.length; i++) {
                query = query + " or status = " + statuses[i];
            }
            query = query + ")";
        }

        Session session = this.sessionFactory.getCurrentSession();
        Query query1 = session.createQuery(query);
        query1.setParameter("id", id);

        if (count > 0) query1.setMaxResults(count);

        List<ToDoTask> toDoList = query1.list();

        for (ToDoTask toDoTask : toDoList) {
            logger.info("ToDoTask successfully loaded. ToDoTask: " + toDoTask);
        }
        return toDoList;
    }


    @SuppressWarnings("unchecked")
    public List<ToDoTask> listToDoPrev(int id, int count, int[] statuses) {
        String query = "from ToDoTask where id<:id";
        if (statuses != null && statuses.length > 0) {
            query = query + " and ( status = " + statuses[0];
            for (int i = 1; i < statuses.length; i++) {
                query = query + " or status = " + statuses[i];
            }
            query = query + ") order by id desc";
        }

        Session session = this.sessionFactory.getCurrentSession();
        Query query1 = session.createQuery(query);
        query1.setParameter("id", id);

        if (count > 0) query1.setMaxResults(count);

        List<ToDoTask> toDoList = query1.list();

        for (ToDoTask toDoTask : toDoList) {
            logger.info("ToDoTask successfully loaded. ToDoTask: " + toDoTask);
        }
        return toDoList;
    }
    @SuppressWarnings("unchecked")
    public int countToDo() {
        return countToDo(null);
    }

    @SuppressWarnings("unchecked")
    public int countToDo(int[] statuses) {
        Session session = this.sessionFactory.getCurrentSession();

        String query = "select count(*) from ToDoTask";
        if (statuses != null && statuses.length > 0) {
            query = query + " where status = " + statuses[0];
            for (int i = 1; i < statuses.length; i++) {
                query = query + " or status = " + statuses[i];
            }
        }
        int cnt = ((Number) session.createQuery(query).uniqueResult()).intValue();
        return cnt;
    }

    @SuppressWarnings("unchecked")
    public int countToDoBeforeId(int id, int[] statuses) {
        Session session = this.sessionFactory.getCurrentSession();

        String query = "select count(*) from ToDoTask where id<=:id ";
        if (statuses != null && statuses.length > 0) {
            query = query + " and( status = " + statuses[0];
            for (int i = 1; i < statuses.length; i++) {
                query = query + " or status = " + statuses[i];
            }
            query += ")";
        }
        Query query1 = session.createQuery(query);
        query1.setParameter("id", id);
        int cnt = ((Number) query1.uniqueResult()).intValue();
        return cnt;
    }

    @SuppressWarnings("unchecked")
    public List<Status> listStatus() {
        Session session = sessionFactory.getCurrentSession();
        List<Status> statuses = session.createQuery("from Status").list();
        return statuses;
    }
}
