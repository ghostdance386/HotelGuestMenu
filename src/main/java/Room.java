import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class Room {

    int number;
    int floor;
    boolean isBooked;
    RoomType type;
    static int roomNumberCounter = 10;

    Room(RoomType type) {
        this.number = ++roomNumberCounter;
        this.floor = this.number / 10;
        this.isBooked = false;
        this.type = type;
    }

    @Override
    public String toString() {
        return "Room{" +
                "number=" + number +
                ", floor=" + floor +
                ", isBooked=" + isBooked +
                ", type=" + type +
                '}';
    }
}

