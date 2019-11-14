package springForHeroku.Controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import springForHeroku.domains.User;
import springForHeroku.domains.UserRole;
import springForHeroku.services.interfaces.UserService;

@RestController
public class UserController {

  private final UserService userService;

  @Autowired
  public UserController(UserService userService) {
    this.userService = userService;
  }

  @ResponseStatus(HttpStatus.CREATED)
  @PostMapping("/signUp")
  public User signUp(@RequestBody User user) {
    return userService.signUp(user);
  }

  @ResponseStatus(HttpStatus.OK)
  @GetMapping("/signIn/{username}&{password}")
  public User signIn(@PathVariable String username, @PathVariable String password) {
    return userService.signIn(User.builder()
        .username(username)
        .password(password).build());
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/buy")
  public boolean buySubscription(@RequestBody User user) {
    return userService.buySubscription(user);
  }

  @ResponseStatus(HttpStatus.OK)
  @PutMapping("/setRole{userId}")
  public void setUserRole(@RequestBody User admin, @PathVariable int userId, UserRole role) {
    userService.setUserRole(admin, userId, role);
  }
}
