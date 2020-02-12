import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
public class Hotel {

    List<Room> rooms = new ArrayList<>();
    Scanner userInput = new Scanner(System.in);

    Hotel() {
        for (int i = 0; i < 40; i++) {
            Room room = new Room(RoomTypeSetter.setType());
            rooms.add(room);
        }
    }
}
