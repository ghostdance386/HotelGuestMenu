package users;

import static org.hamcrest.MatcherAssert.assertThat;

import org.hamcrest.Matchers;
import org.testng.annotations.Test;

public class UsersTest {

  @Test
  public void checkIfUsersAreAddedToAllUsers() {
    //given
    String firstName = "K";
    String lastName = "D";
    //when
    Users.getUser(firstName, lastName);
    //then
    assertThat(Users.allUsers, Matchers.hasSize(1));
    assertThat(firstName, Matchers.equalTo(Users.allUsers.iterator().next().getFirstName()));
    assertThat(lastName, Matchers.equalTo(Users.allUsers.iterator().next().getLastName()));
  }

  @Test(dependsOnMethods = "checkIfUsersAreAddedToAllUsers")
  public void checkIfOnlyNewUsersAreCreated() {
    //given
    String firstName = "K";
    String lastName = "D";
    //when
    Users.getUser(firstName, lastName);
    //then
    assertThat(Users.allUsers, Matchers.hasSize(1));
  }
}
