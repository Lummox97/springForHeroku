package springForHeroku.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springForHeroku.domains.User;

public interface UserRepository extends JpaRepository<User, Integer> {

  public User findByUsernameAndPassword(String username, String password);
}
