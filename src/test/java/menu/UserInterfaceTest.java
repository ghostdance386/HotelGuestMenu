package menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Scanner;
import jdk.jfr.Description;
import org.testng.annotations.Test;
import users.Users;

public class UserInterfaceTest {

  private UserInterface ui = new UserInterface();

  @Test
  @Description("Check if welcome menu text is loaded to file object from txt file")
  public synchronized void checkIfWelcomeMenuTextIsLoadedToFileObject() {
    //given
    ui.setUserInput(new Scanner("FirstName LastName").useDelimiter("\\s"));
    //when
    ui.showWelcomeMenu();
    //then
    assertThat(ui.getText())
        .as("Check that welcome menu text is loaded")
        .isNotEmpty();
  }

  @Test
  @Description("Check if main menu text is loaded to file object from txt file")
  public synchronized void checkIfMainMenuTextIsLoadedToFileObject() {
    //given
    ui.setUserInput(new Scanner("").useDelimiter("\\s"));
    ui.setCurrentUser(Users.getUser("FirstName", "LastName"));
    //when
    ui.showMainMenu();
    //then
    assertThat(ui.getText())
        .as("Check that main menu text is loaded")
        .isNotEmpty();
  }

  @Test
  @Description("Check if all rooms menu text is loaded to file object from txt file")
  public synchronized void checkIfAllRoomsMenuTextIsLoadedToFileObject() {
    //given
    ui.setUserInput(new Scanner("").useDelimiter("\\s"));
    //when
    ui.showAllApartmentsMenu();
    //then
    assertThat(ui.getText())
        .as("Check that all rooms menu text is loaded")
        .isNotEmpty();
  }
}
