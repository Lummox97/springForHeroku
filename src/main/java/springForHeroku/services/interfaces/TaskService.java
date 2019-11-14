package springForHeroku.services.interfaces;

import java.util.List;
import springForHeroku.domains.Task;
import springForHeroku.domains.TaskPrior;
import springForHeroku.domains.User;

public interface TaskService {

  Task findById(int id);

  Task createTask(Task task) throws Exception;

  List<Task> getTasks(User user);

  boolean removeTask(Task task);

  boolean setTaskPrior(Task task, TaskPrior priority);

  boolean markTaskComplete(Task task);

  boolean markTaskIncomplete(Task task);

  boolean attacheFile(int task, String fileName);

  List<Task> getTasksSorted(User user);
}
