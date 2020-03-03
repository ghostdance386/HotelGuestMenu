package rooms;

import static org.hamcrest.MatcherAssert.assertThat;
import static rooms.Properties.BALCONY;
import static rooms.Properties.JACUZZI;
import static rooms.Properties.MINIBAR;
import static rooms.Properties.REFRIGERATOR;
import static rooms.Properties.TV;

import java.util.Arrays;
import org.hamcrest.Matchers;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

public class RoomTest {

  private int roomNumber = 1;
  private int floor = 1;

  @DataProvider(name = "rooms")
  public Object[][] rooms() {
    return new Object[][]{
        {new OneBedroom(roomNumber, floor),
            new Properties[]{BALCONY, TV}, 180, 12},
        {new Standard(roomNumber, floor),
            new Properties[]{BALCONY, TV, REFRIGERATOR}, 280, 20},
        {new Penthouse(roomNumber, floor),
            new Properties[]{BALCONY, TV, REFRIGERATOR, MINIBAR, JACUZZI}, 550, 50}};
  }

  @Test(dataProvider = "rooms")
  public void checkIfCreatedRoomHasProperlySetFields(Room room, Properties[] properties,
                                                     int price, double size) {
    assertThat("Number of the room does not match the number provided to constructor",
        room.getNumber(), Matchers.equalTo(roomNumber));
    assertThat("Number of the floor does not match the number provided to constructor",
        room.getFloor(), Matchers.equalTo(floor));
    assertThat("Price is not correct",
        room.getPrice(), Matchers.equalTo(price));
    assertThat("Total size is not correct",
        room.getTotalSize(), Matchers.equalTo(size));
    assertThat("The room should be created as not booked",
        room.isBooked(), Matchers.equalTo(false));
    assertThat("The room should have a balcony",
        room.isWithBalcony(), Matchers.equalTo(true));
    assertThat("The room properties are not correct",
        room.getRoomProperties(), Matchers.equalTo(Arrays.asList(properties)));
  }
}
