package rooms;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.equalTo;

import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

public class RoomTest {

  private int roomNumber = 1;
  private int floor = 1;
  private OneBedroom oneBedroom;
  private Standard standard;
  private Penthouse penthouse;

  @BeforeMethod
  public void setupRooms() {
    oneBedroom = new OneBedroom(roomNumber, floor);
    standard = new Standard(roomNumber, floor);
    penthouse = new Penthouse(roomNumber, floor);
  }

  @Test()
  public void checkIfOneBedroomsAreEqual() {
    //given when
    OneBedroom room = new OneBedroom(roomNumber, floor);
    //then
    assertThat("One bedroom rooms are not equal",
        room, equalTo(oneBedroom));
  }

  @Test()
  public void checkIfStandardRoomsAreEqual() {
    //given when
    Standard room = new Standard(roomNumber, floor);
    //then
    assertThat("One bedroom rooms are not equal",
        room, equalTo(standard));
  }

  @Test()
  public void checkIfPenthousesAreEqual() {
    //given when
    Penthouse room = new Penthouse(roomNumber, floor);
    //then
    assertThat("One bedroom rooms are not equal",
        room, equalTo(penthouse));
  }
}
