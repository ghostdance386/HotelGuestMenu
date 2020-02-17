public class Main {

  public static void main(String[] args) {
    final Hotel myHotel = new HotelBuilder()
        .withOneBedrooms(3, 1)
        .withStandardRooms(3, 1)
        .withPenthouses(3, 1)
        .build();
  }
}
