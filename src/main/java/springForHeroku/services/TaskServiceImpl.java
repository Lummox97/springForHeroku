package springForHeroku.services;


import java.util.Comparator;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import springForHeroku.Repository.TaskRepository;
import springForHeroku.domains.Task;
import springForHeroku.domains.TaskPrior;
import springForHeroku.domains.User;
import springForHeroku.services.interfaces.TaskService;

@Service
public class TaskServiceImpl implements TaskService {

  private TaskRepository taskRepository;

  @Autowired
  public TaskServiceImpl(TaskRepository taskRepository) {
    this.taskRepository = taskRepository;
  }

  @Override
  public Task createTask(Task task) {
    try {
      Task task2 = taskRepository.save(task);
        if (!task2.equals(task)) {
            throw new Exception("DataBase problem");
        }
      return task2;
    } catch (Exception e) {
      e.printStackTrace();
    }

    return null;
  }

  @Override
  public List<Task> getTasks(User user) {
    try {
      return taskRepository.findAllByUser(user);
    } catch (Exception e) {
      System.out.println("No tasks for this user");
    }
    return null;
  }

  @Override
  public boolean removeTask(Task task) {
    try {
      taskRepository.deleteById(task.getId());
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public boolean setTaskPrior(Task task, TaskPrior priority) {

    task.setPriority(priority);
    return checkUpdates(task);
  }

  @Override
  public boolean markTaskComplete(Task task) {

    task.setStatus(true);
    return checkUpdates(task);
  }

  @Override
  public boolean markTaskIncomplete(Task task) {

    task.setStatus(false);
    return checkUpdates(task);
  }

  @Override
  public Task findById(int id) {
    return taskRepository.getOne(id);
  }

  @Override
  public boolean attacheFile(int id, String fileName) {
    Task task = findById(id);
    task.setFileName(fileName);
    return checkUpdates(task);
  }

  @Override
  public List<Task> getTasksSorted(User user) {
    try {
      List<Task> tasks = taskRepository.findAllByUser(user);
      tasks.sort(Comparator.comparingInt(task -> task.getPriority().getValue()));
      return tasks;
    } catch (Exception e) {
      System.out.println("No tasks for this user");
    }
    return null;
  }

  private boolean checkUpdates(Task task) {
    try {
        if (!taskRepository.save(task).equals(task)) {
            throw new Exception("DataBase Problem during update");
        }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }
}
