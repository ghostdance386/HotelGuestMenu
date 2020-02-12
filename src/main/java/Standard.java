import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Getter
@Setter
@ToString
@NoArgsConstructor
public class Standard extends Room {

    private int number;
    private int floor;
    private double totalSize;
    private boolean isBooked;
    private String type = "Standard Room";
    private boolean hasBalcony = true;
    private boolean hasJacuzzi = false;
    private boolean hasMiniBar = false;
    private boolean hasTV = true;
    private boolean hasRefrigerator = true;
    private int price;

}

