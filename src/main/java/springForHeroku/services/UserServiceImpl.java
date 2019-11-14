package springForHeroku.services;

import Legacy.Inspector;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;
import springForHeroku.Repository.UserRepository;
import springForHeroku.domains.User;
import springForHeroku.domains.UserRole;
import springForHeroku.services.interfaces.UserService;

@Service
public class UserServiceImpl implements UserService {

  private UserRepository userRepository;
  private Inspector inspector;

  @Autowired
  public UserServiceImpl(UserRepository userRepository, Inspector inspector) {
    this.userRepository = userRepository;
    this.inspector = inspector;
  }

  @Override
  public User signUp(User user) {
    try {
      User userDB = userRepository.save(user);
        if (!userDB.equals(user)) {
            throw new Exception("DataBase problem");
        }
      return userDB;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public User signIn(User user) {
    try {
      return userRepository.findByUsernameAndPassword(user.getUsername(), user.getPassword());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return null;
  }

  @Override
  public boolean buySubscription(User user) {
    try {
      user.setSubscription(DigestUtils.md5DigestAsHex("secret".getBytes()));
      userRepository.save(user);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
    }
    return false;
  }

  @Override
  public void setUserRole(User admin, int userId, UserRole role) {
    User toBeSet = findUserById(userId);
    toBeSet.setRole(role);
    try {
      userRepository.save(toBeSet);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  @Override
  public User findUserById(int id) {
    User read = null;
    try {
      read = userRepository.getOne(id);
        if (read == null) {
            throw new Exception("Database problem");
        }
    } catch (Exception e) {
      e.printStackTrace();
    }
    return read;
  }

  public void deleteUser(User user) {
    try {
      userRepository.delete(user);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
