import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Getter
@Setter
@ToString(exclude = "userInput")
@NoArgsConstructor
public class Hotel {

    private List<OneBedroom> oneBedroomList = new ArrayList<>();
    private List<Standard> standardList = new ArrayList<>();
    private List<Penthouse> penthouseList = new ArrayList<>();
    private Scanner userInput = new Scanner(System.in);

    public Hotel(List<OneBedroom> oneBedroomList, List<Standard> standardList, List<Penthouse> penthouseList) {
        this.oneBedroomList = oneBedroomList;
        this.standardList = standardList;
        this.penthouseList = penthouseList;
    }

}

