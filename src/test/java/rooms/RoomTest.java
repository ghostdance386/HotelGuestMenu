package rooms;

import static org.assertj.core.api.Assertions.assertThat;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RoomTest {

  private int roomNumber = 1;
  private int floor = 1;
  private OneBedroom oneBedroom;
  private Standard standard;
  private Penthouse penthouse;

  /**
   * Sets up the objects of different type of rooms to use during tests.
   */
  @BeforeMethod(description = "Set up a single room of all available types", alwaysRun = true)
  public void setupRooms() {
    oneBedroom = new OneBedroom(roomNumber, floor);
    standard = new Standard(roomNumber, floor);
    penthouse = new Penthouse(roomNumber, floor);
  }

  @Test(description = "Check if default one bedrooms are equal (have the same properties)")
  public void checkIfOneBedroomsAreEqual() {
    //given when
    OneBedroom room = new OneBedroom(roomNumber, floor);
    //then
    assertThat(room)
        .as("check if %s is equal to $s", room.toString(), oneBedroom.toString())
        .isEqualTo(oneBedroom);
  }

  @Test(description = "Check if default standard rooms are equal (have the same properties)")
  public void checkIfStandardRoomsAreEqual() {
    //given when
    Standard room = new Standard(roomNumber, floor);
    //then
    assertThat(room)
        .as("check if %s is equal to $s", room.toString(), standard.toString())
        .isEqualTo(standard);
  }

  @Test(description = "Check if default penthouses are equal (have the same properties)")
  public void checkIfPenthousesAreEqual() {
    //given when
    Penthouse room = new Penthouse(roomNumber, floor);
    //then
    assertThat(room)
        .as("check if %s is equal to $s", room.toString(), penthouse.toString())
        .isEqualTo(penthouse);
  }
}
