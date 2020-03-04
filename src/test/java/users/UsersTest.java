package users;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.in;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.sameInstance;

import org.testng.annotations.Test;

public class UsersTest {

  @Test
  public void checkIfUsersAreAddedToAllUsers() {
    //given
    String firstName = "K";
    String lastName = "D";
    //when
    User user = Users.getUser(firstName, lastName);
    //then
    assertThat(Users.allUsers, hasSize(1));
    assertThat(user, is(in(Users.allUsers)));
  }

  @Test(dependsOnMethods = "checkIfUsersAreAddedToAllUsers")
  public void checkIfOnlyNewUsersAreCreated() {
    //given
    String firstName = "K";
    String lastName = "D";
    //when
    User user1 = Users.getUser(firstName, lastName);
    User user2 = Users.getUser(firstName, lastName);
    //then
    assertThat(user1, sameInstance(user2));
  }
}
