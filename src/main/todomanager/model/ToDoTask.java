package todomanager.model;

import java.util.Date;
import javax.persistence.*;
/**
 * Created by butkoav on 24.02.2017.
 */
@Entity
@Table(name = "todo_list")
public class ToDoTask {
    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "todo")
    private String text;

    @Column(name = "status")
    private int status;

    @Column(name = "create_date")
    private Date createDate;

    @Override
    public String toString() {
        return "ToDoTask{" +
                "id=" + id +
                ", text='" + text + '\'' +
                ", status=" + status +
                ", createDate=" + createDate +
                '}';
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = new Date();
    }
}
