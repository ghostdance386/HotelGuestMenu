import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.apache.log4j.Logger;

@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class Room {

  static Logger logger = Logger.getLogger(Room.class);
  int number;
  int floor;
  int price;
  double totalSize;
  boolean isBooked;
  boolean withBalcony;
  List<String> roomProperties = new ArrayList<>();

  public Room(int number, int floor) {
    this.number = number;
    this.floor = floor;
    this.isBooked = false;
    this.withBalcony = true;
    roomProperties.add("Balcony");
  }
}

