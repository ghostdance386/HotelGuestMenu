import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
public abstract class Room {

  int number;
  int floor;
  int price;
  double totalSize;
  boolean isBooked;
  boolean withBalcony;

  public Room(int number, int floor) {
    this.number = number;
    this.floor = floor;
    this.isBooked = false;
    this.withBalcony = true;
  }

}

