package flight.vidmot.vidmot;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
// import static org.junit.jupiter.api.Assertions.assertEquals;
// import static org.junit.jupiter.api.Assertions.fail;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import flight.classes.Booking;
import flight.classes.Flight;
import flight.classes.User;
import flight.classes.Seat;
import flight.interfaces.BookingServiceLayerInterface;
import flight.vidmot.BookingController;
import flight.vidmot.mocks.BookingServiceLayerFail;

public class BookingControllerFailTest {

  private BookingController BC;

  private final String[] seatNumbers = {"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2"};

  private User testPassenger;
  // private User testPassenger2;
  private Flight testFlight;
  private Booking oracleBooking;
  private LocalDate currentDate = LocalDate.now();


  @Before
  public void setUp() {
    BookingServiceLayerInterface BSLF = new BookingServiceLayerFail();
    BC = new BookingController(BSLF);

    testPassenger = new User(
      "TH-0203040506", 
      "Tryggvi Hjálmarsson"
    );

    // testPassenger2 = new User(
    //   "KT-0304050607", 
    //   "Klara Tryggvadóttir"
    // );

    ArrayList<Seat> seats = new ArrayList<>();
    for (String seatNum : seatNumbers) {
      seats.add(new Seat(seatNum, "F-001", false));
    }

    LocalTime depTime = LocalTime.of(15, 30);
    LocalTime arrTime = LocalTime.of(16, 30);

    testFlight = new Flight(
      "F-001", 
      seats, 
      "Reykjavík", 
      "Vestmannaeyjar", 
      LocalDate.of(2024, 4, 5), 
      depTime,
      LocalDate.of(2024, 4, 5), 
      arrTime,
      12000
    );
    
    List<User> testpassengerList = new ArrayList<>();
    testpassengerList.add(testPassenger);
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    oracleBooking = BC.createBooking(
      testPassenger, 
      testFlight, 
      testSeatList, 
      currentDate,
      false
    );
  }


  @Test
  public void createBooking() {
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    Booking testBooking = BC.createBooking(
      testPassenger, 
      testFlight, 
      testSeatList, 
      currentDate,
      false
    );

    assertNotNull(testBooking);
  }


  @Test
  public void addSeatToBooking() {
    BC.addSeatToBooking(oracleBooking, testFlight.getSeats().get(1));
    assertTrue(oracleBooking.getSeatsInBooking().contains(testFlight.getSeats().get(1)));
  }


  @Test
  public void removeSeatFromBooking() {
    BC.removeSeatFromBooking(oracleBooking, testFlight.getSeats().get(0));
    assertFalse(oracleBooking.getSeatsInBooking().contains(testFlight.getSeats().get(0)));
  }


  @Test
  public void getBookingById() {
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    Booking idTestBooking = BC.createBooking(
      testPassenger, 
      testFlight, 
      testSeatList, 
      currentDate,
      false
    );

    Booking idBooking = BC.getBookingById(idTestBooking.getBookingId());
    assertNull(idBooking);
  }


  @Test
  public void getBookingByPurchaserId() {
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    BC.createBooking(
      testPassenger, 
      testFlight, 
      testSeatList, 
      currentDate,
      false
    );

    List<Booking> idBookings = BC.getBookingsByUserId(testPassenger.getId());
    assertTrue(idBookings.isEmpty());
  }


  @Test
  public void getAllBookings() {
    List<Seat> testSeatList = new ArrayList<>();
    testSeatList.add(testFlight.getSeats().get(0));
    
    BC.createBooking(
      testPassenger, 
      testFlight,  
      testSeatList, 
      currentDate,
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
