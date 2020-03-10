package users;

import static org.assertj.core.api.Assertions.assertThat;

import jdk.jfr.Description;
import org.testng.annotations.Test;

public class UsersTest {

  @Test
  @Description("Check if new user is added to users list")
  public void checkIfUsersAreAddedToAllUsers() {
    //given
    String firstName = "K";
    String lastName = "D";
    //when
    User user = Users.getUser(firstName, lastName);
    //then
    assertThat(Users.allUsers)
        .as("check if %s %s is on users list", user.getFirstName(), user.getLastName())
        .containsOnlyOnce(user);

  }

  @Test(dependsOnMethods = "checkIfUsersAreAddedToAllUsers")
  @Description("Check if only new users are added to the users list")
  public void checkIfOnlyNewUsersAreCreated() {
    //given
    String firstName = "K";
    String lastName = "D";
    //when
    User user1 = Users.getUser(firstName, lastName);
    User user2 = Users.getUser(firstName, lastName);
    //then
    assertThat(user1)
        .as("check if %s is the same instance of User as %s", user1.toString(), user2.toString())
        .isSameAs(user2);
  }
}
