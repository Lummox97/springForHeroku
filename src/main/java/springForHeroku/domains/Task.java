package springForHeroku.domains;

import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
public class Task {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String name;
  private boolean status;
  @ManyToOne
  private User user;
  private TaskPrior priority;
  private String fileName;


  private Task(int id, String name, boolean status, User user, TaskPrior priority,
      String fileName) {
    this.id = id;
    this.name = name;
    this.status = status;
    this.user = user;
    this.priority = priority;
    this.fileName = fileName;
  }

  public int getId() {
    return id;
  }

  public void setName(String name) {
    if (name == null && this.name == null) {
      this.name = "Default";
      return;
    } else if (this.name != null && name == null) {
      return;
    }
    this.name = name;
  }

  public void setStatus(boolean status) {
    this.status = status;
  }

  public void setPriority(TaskPrior priority) {
    this.priority = priority;
  }

  public void setFileName(String fileName) {
    this.fileName = fileName;
  }

  public String getName() {
    return name;
  }

  public boolean getStatus() {
    return status;
  }

  public User getUser() {
    return user;
  }

  public TaskPrior getPriority() {
    return priority;
  }

  public String getFileName() {
    return fileName;
  }

  @Override
  public String toString() {
    return "Tasks{" +
        "id=" + id +
        ", name='" + name + '\'' +
        ", status=" + status +
        ", user=" + user.toString() +
        ", priority=" + priority +
        ", fileName=" + fileName +
        '}';
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    Task task = (Task) o;
    return status == task.status &&
        user.equals(task.user) &&
        Objects.equals(name, task.name) &&
        priority == task.priority &&
        Objects.equals(fileName, task.fileName);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id, name, status, user, priority);
  }

}
