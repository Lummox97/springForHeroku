package springForHeroku.Controllers;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import springForHeroku.domains.Task;
import springForHeroku.domains.TaskPrior;
import springForHeroku.domains.User;
import springForHeroku.services.interfaces.TaskService;

@RestController
public class TaskController {

  private final TaskService taskService;

  @Autowired
  public TaskController(TaskService taskService) {
    this.taskService = taskService;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/createTask")
  public Task createTask(@RequestBody Task task) throws Exception {
    return taskService.createTask(task);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/getAllTasks/{userId}")
  public List<Task> getTasks(@PathVariable Integer userId) {
    return taskService.getTasks(User.builder().id(userId).build());
  }

  @ResponseStatus(HttpStatus.OK)
  @DeleteMapping("/deleteTask")
  public void removeTask(@RequestBody Task task) {
    taskService.removeTask(task);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/changeTaskPriority")
  public boolean setTaskPriority(@RequestBody Task task, TaskPrior priority) {
    return taskService.setTaskPrior(task, priority);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/markTaskAsComplete")
  public boolean markTaskComplete(@RequestBody Task task) {
    return taskService.markTaskComplete(task);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/markTaskAsIncomplete")
  public boolean markTaskIncomplete(@RequestBody Task task) {
    return taskService.markTaskIncomplete(task);
  }

  @ResponseStatus(HttpStatus.ACCEPTED)
  @PostMapping("/task{id}/uploadfile")
  public boolean fileUpload(@PathVariable int id, @RequestParam("file") MultipartFile file) {
    return taskService.attacheFile(id, file.getOriginalFilename());
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/getAllTasksSorted/{userId}")
  public List<Task> getTasksSorted(@PathVariable Integer userId) {
    return taskService.getTasksSorted(User.builder().id(userId).build());
  }
}
