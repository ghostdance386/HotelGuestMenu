

public class Main {

    public static void main(String[] args) {

        Hotel myHotel = new HotelBuilder().withOneBedrooms(3, 1).withStandardRooms(3, 1).withPenthouses(3, 1).build();
        Hotel myHotel2 = new HotelBuilder().withOneBedrooms(1, 1).withStandardRooms(1, 1).withStandardRooms(myHotel.getStandardList()).build();

        System.out.println(myHotel2.toString());


    }
}
