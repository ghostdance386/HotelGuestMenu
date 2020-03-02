package users;

import java.util.ArrayList;
import java.util.Collection;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import rooms.Room;

/**
 * Class used in {@link menu.UserInterface} class stores basic information
 * about the person using hotel's menu and a list of rooms booked in current
 * session.
 */
@Getter
@Setter
@NoArgsConstructor
@ToString
public class User {

  private String firstName;
  private String lastName;
  Collection<Room> bookedRooms = new ArrayList<>();
}
