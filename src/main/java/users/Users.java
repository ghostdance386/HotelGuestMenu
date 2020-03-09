package users;

import java.util.Collection;
import java.util.HashSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Utility class to store all the {@link users.User} objects and return an
 * object of {@link users.User} class during logging in to {@link menu.UserInterface}.
 */
@Getter
@Setter
@ToString
public class Users {

  static Collection<User> allUsers = new HashSet<>();

  /**
   * Factory method that returns an {@link users.User} object from the set,
   * if one with provided first name and last name already exists or creates
   * a new one.
   *
   * @param firstName specifies the first name of the {@link users.User}
   * @param lastName  specifies the last name of the {@link users.User}
   * @return an {@link users.User} object
   */
  public static User getUser(String firstName, String lastName) {
    for (User user : allUsers) {
      if (user.getFirstName().equals(firstName) && user.getLastName().equals(lastName)) {
        return user;
      }
    }
    User newUser = new User();
    newUser.setFirstName(firstName);
    newUser.setLastName(lastName);
    allUsers.add(newUser);
    return newUser;
  }
}

