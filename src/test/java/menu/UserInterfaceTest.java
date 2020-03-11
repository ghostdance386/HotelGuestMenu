package menu;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.Scanner;
import org.testng.annotations.Test;
import users.Users;

public class UserInterfaceTest {

  private UserInterface ui;

  public synchronized void setUserInput(String input) {
    ui = new UserInterface();
    ui.setUserInput(new Scanner(input).useDelimiter("\\s"));
  }

  @Test(description = "Check if welcome menu text is loaded to file object from txt file")
  public void checkIfWelcomeMenuTextIsLoadedToFileObject() {
    //given
    setUserInput("FirstName LastName");
    //when
    ui.showWelcomeMenu();
    //then
    assertThat(ui.getText())
        .as("Check that welcome menu text is loaded")
        .isNotEmpty();
  }

  @Test(description = "Check if main menu text is loaded to file object from txt file")
  public void checkIfMainMenuTextIsLoadedToFileObject() {
    //given
    setUserInput("");
    ui.setCurrentUser(Users.getUser("FirstName", "LastName"));
    //when
    ui.showMainMenu();
    //then
    assertThat(ui.getText())
        .as("Check that main menu text is loaded")
        .isNotEmpty();
  }

  @Test(description = "Check if all rooms menu text is loaded to file object from txt file")
  public void checkIfAllRoomsMenuTextIsLoadedToFileObject() {
    //given
    setUserInput("");
    //when
    ui.showAllApartmentsMenu();
    //then
    assertThat(ui.getText())
        .as("Check that all rooms menu text is loaded")
        .isNotEmpty();
  }
}
