import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Getter
@Setter
public abstract class RoomType {

    String name;
    List<String> properties;
    int price;

}

@Getter
@Setter
class OneBedroom extends RoomType {

    String name = "One Bedroom";
    List<String> properties = new ArrayList<>();
    int price = 180;

    @Override
    public String toString() {
        return "OneBedroom{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                ", price=" + price +
                '}';
    }
}

@Getter
@Setter
class Standard extends RoomType {

    String name = "Standard Room";
    List<String> properties = new ArrayList<>();
    int price = 250;

    @Override
    public String toString() {
        return "Standard{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                ", price=" + price +
                '}';
    }
}

@Getter
@Setter
class Penthouse extends RoomType {

    String name = "Exclusive Penthouse";
    List<String> properties = new ArrayList<>();
    int price = 550;

    @Override
    public String toString() {
        return "Penthouse{" +
                "name='" + name + '\'' +
                ", properties=" + properties +
                ", price=" + price +
                '}';
    }
}

class RoomTypeSetter {

    public static RoomType setType() {
        int random = new Random().nextInt(3);
        if (random == 0) {
            return new OneBedroom();
        } else if (random == 1) {
            return new Standard();
        } else return new Penthouse();

    }
}
