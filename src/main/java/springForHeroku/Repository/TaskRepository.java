package springForHeroku.Repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import springForHeroku.domains.Task;
import springForHeroku.domains.User;

public interface TaskRepository extends JpaRepository<Task, Integer> {

  public List<Task> findAllByUser(User user);
}
