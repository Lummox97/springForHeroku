package springForHeroku.domains;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonPOJOBuilder;
import java.util.List;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Embeddable;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class User {

  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private String firstName;
  private String lastName;
  private String username;
  private String eMail;
  private String password;
  private String subscription;
  private UserRole role;
  @OneToMany(mappedBy = "user", fetch = FetchType.EAGER, cascade = {CascadeType.ALL})
  private List<Task> taskList;

  public User(String username, String password) {
    this.username = username;
    this.password = password;
  }

  private User(int id, String firstName, String lastName, String username, String eMail,
      String password) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.eMail = eMail;
    this.password = password;
    this.subscription = "";
    this.role = UserRole.REGULAR_USER;
  }

  public void seteMail(String eMail) {
    this.eMail = eMail;
  }

  public User(int id, String firstName, String lastName, String username, String eMail,
      String password, String subscription, UserRole role) {
    this.id = id;
    this.firstName = firstName;
    this.lastName = lastName;
    this.username = username;
    this.eMail = eMail;
    this.password = password;
    this.subscription = subscription;
    this.role = role;
  }

  public void setId(int id) {
    this.id = id;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }

  public void setLastName(String lastName) {
    this.lastName = lastName;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public int getId() {
    return id;
  }

  public String getFirstName() {
    return firstName;
  }

  public String getLastName() {
    return lastName;
  }

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public String geteMail() {
    return eMail;
  }

  public String getSubscription() {
    return subscription;
  }

  public void setSubscription(String subscription) {
    this.subscription = subscription;
  }

  public UserRole getRole() {
    return role;
  }

  public void setRole(UserRole role) {
    this.role = role;
  }

  public boolean isAdmin() {
    return this.role == UserRole.ADMIN;
  }

  @Override
  public boolean equals(Object o) {
      if (this == o) {
          return true;
      }
      if (o == null || getClass() != o.getClass()) {
          return false;
      }
    User user = (User) o;
    return Objects.equals(firstName, user.firstName) &&
        Objects.equals(lastName, user.lastName) &&
        Objects.equals(username, user.username) &&
        Objects.equals(eMail, user.eMail) &&
        Objects.equals(password, user.password) &&
        Objects.equals(subscription, user.subscription);
  }

  @Override
  public int hashCode() {
    return Objects.hash(firstName, lastName, username, eMail, password, subscription);
  }

  @Override
  public String toString() {
    return "User{" +
        "id=" + id +
        ", firstName='" + firstName + '\'' +
        ", lastName='" + lastName + '\'' +
        ", userName='" + username + '\'' +
        ", eMail='" + eMail + '\'' +
        ", password='" + password + '\'' +
        ", subscription='" + subscription + '\'' +
        ", role=" + role +
        '}';
  }


}
