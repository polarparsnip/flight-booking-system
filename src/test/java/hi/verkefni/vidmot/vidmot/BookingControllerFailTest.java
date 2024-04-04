package hi.verkefni.vidmot.vidmot;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hi.verkefni.classes.Booking;
import hi.verkefni.classes.Flight;
import hi.verkefni.classes.Passenger;
import hi.verkefni.classes.Seat;
import hi.verkefni.interfaces.BookingServiceLayerInterface;
import hi.verkefni.vidmot.BookingController;
import hi.verkefni.vidmot.mocks.BookingServiceLayerFail;

public class BookingControllerFailTest {

  private BookingController BC;

  private final String[] seatNumbers = {"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2"};

  private Passenger testPassenger;
  private Passenger testPassenger2;
  private Flight testFlight;
  private Booking oracleBooking;


  @Before
  public void setUp() {
    BookingServiceLayerInterface BSLF = new BookingServiceLayerFail();
    BC = new BookingController(BSLF);

    testPassenger = new Passenger(
      "TH-0203040506", 
      "Tryggvi Hjálmarsson", 
      "020290-5555"
    );

    testPassenger2 = new Passenger(
      "KT-0304050607", 
      "Klara Tryggvadóttir", 
      "020202-6666"
    );

    ArrayList<Seat> seats = new ArrayList<>();
    for (String seatNum : seatNumbers) {
      seats.add(new Seat(seatNum, "F-001", false));
    }

    testFlight = new Flight(
      "F-001", 
      seats, 
      "Reykjavík", 
      "Vestmannaeyjar", 
      LocalDate.of(2024, 4, 5), 
      LocalDate.of(2024, 4, 5), 
      12000
    );
    
    List<Passenger> testpassengerList = new ArrayList<>();
    testpassengerList.add(testPassenger);
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    oracleBooking = new Booking(
      testPassenger, 
      testFlight, 
      testpassengerList, 
      testSeatList, 
      false, 
      false
    );
  }


  @Test
  public void createBooking() {
    List<Passenger> testpassengerList = new ArrayList<>();
    testpassengerList.add(testPassenger);
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    Booking testBooking = BC.createBooking(
      testPassenger, 
      testFlight, 
      testpassengerList, 
      testSeatList, 
      false, 
      false
    );

    assertNotNull(testBooking);
  }


  @Test
  public void addPassengerToBooking() {
    BC.addPassengerToBooking(oracleBooking, testPassenger2, testFlight.getSeats().get(1));
    List<String> passengerIdsInBooking = oracleBooking.getPassengersInBooking();
    assertTrue(passengerIdsInBooking.contains(testPassenger2.getId()));
  }


  @Test
  public void removePassengerFromBooking() {
    BC.removePassengerFromBooking(oracleBooking, testPassenger2);
    List<String> passengerIdsInBooking = oracleBooking.getPassengersInBooking();
    assertFalse(passengerIdsInBooking.contains(testPassenger2.getId()));
  }


  @Test
  public void getBookingByKennitala() {
    List<Passenger> testpassengerList = new ArrayList<>();
    testpassengerList.add(testPassenger);
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    BC.createBooking(
      testPassenger, 
      testFlight, 
      testpassengerList, 
      testSeatList, 
      false, 
      false
    );

    List<Booking> ktBookings = BC.getBookingsByKennitala(testPassenger.getKennitala());
    assertTrue(ktBookings.isEmpty());
  }


  @Test
  public void getBookingByPurchaserId() {
    List<Passenger> testpassengerList = new ArrayList<>();
    testpassengerList.add(testPassenger);
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    BC.createBooking(
      testPassenger, 
      testFlight, 
      testpassengerList, 
      testSeatList, 
      false, 
      false
    );

    List<Booking> idBookings = BC.getBookingsByPurchaserId(testPassenger.getId());
    assertTrue(idBookings.isEmpty());
  }


  @Test
  public void getAllBookings() {
    List<Passenger> testpassengerList = new ArrayList<>();
    testpassengerList.add(testPassenger);
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    BC.createBooking(
      testPassenger, 
      testFlight, 
      testpassengerList, 
      testSeatList, 
      false, 
      false
    );

    List<Booking> allBookings = BC.getAllBookings();
    assertTrue(allBookings.isEmpty());
  }


  @Test
  public void deleteBooking() {
    BC.deleteBooking(oracleBooking);
    assertFalse(BC.getAllBookings().contains(oracleBooking));
  }


  @After
  public void tearDown() {
    BC = null;
  }
}