package users;

import java.util.Collection;
import java.util.HashSet;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class Users {

  private static Collection<User> allUsers = new HashSet<>();

  public static User getUser(String firstName, String lastName) {
    for (User user : allUsers
    ) {
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

