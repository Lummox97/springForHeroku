package springForHeroku.services.interfaces;

import springForHeroku.domains.User;
import springForHeroku.domains.UserRole;

public interface UserService {

  User signUp(User user);

  User signIn(User user);

  User findUserById(int id);

  boolean buySubscription(User user);

  void setUserRole(User admin, int toBeSet, UserRole role);
}
