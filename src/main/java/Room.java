import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Room {

    private int number;
    private int floor;
    private double totalSize;
    private boolean isBooked;
    private String type;
    private boolean hasBalcony;
    private boolean hasJacuzzi;
    private boolean hasMiniBar;
    private boolean hasTV;
    private boolean hasRefrigerator;
    private int price;

}

