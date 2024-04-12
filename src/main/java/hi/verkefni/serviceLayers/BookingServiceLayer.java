package hi.verkefni.serviceLayers;

import java.lang.reflect.Array;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.time.LocalDate;

import hi.verkefni.classes.Booking;
import hi.verkefni.classes.Passenger;
import hi.verkefni.classes.Seat;
import hi.verkefni.classes.Flight;
import hi.verkefni.database.Database;
import hi.verkefni.interfaces.BookingServiceLayerInterface;

/**
 * The BookingServiceLayer provides methods for interacting with booking entries
 * in the database.
 */
public class BookingServiceLayer implements BookingServiceLayerInterface {

  private final String databasePath = "sql/flightBookingSystem.db";

  /**
   * Constructor for the booking service layer.
   */
  public BookingServiceLayer() {
  }

  /**
   * Creates a booking entry in the database based on a {@link Booking} object.
   * 
   * @param booking The {@link Booking} to be inserted in the database.
   */
  public void createBooking(Booking booking) {
    Database db = new Database(databasePath);
    db.open();

    String[] PassengerValues = { booking.getBookingPurchaser().getId(), booking.getBookingPurchaser().getName() };

    db.query("insert or ignore into Passengers (passengerId, name) values (?, ?)", PassengerValues,
        false);

    String[] BookingValues = { booking.getBookingPurchaser().getId(), booking.getBookingId(),
        booking.getFlight().getFlightNr(), booking.getBookingDate().toString(), booking.isInsured().toString() };

    db.query("insert into Bookings (passengerId, bookingId, flightNr, bookingDate, insured) values (?, ?, ?, ?, ?)",
        BookingValues, false);

    for (Seat seat : booking.getSeatsInBooking()) {
      String[] bookedSeatsValues = { booking.getBookingId(), booking.getBookingPurchaser().getId(), seat.getSeatNr() };
      String[] seatsValues = { booking.getFlight().getFlightNr(), seat.getSeatNr() };
      if (!seat.getReservationStatus()) {
        db.query("UPDATE Seats set reserved = True WHERE flightNr = ? AND seatNumber = ?", seatsValues, false);
        db.query("insert into BookedSeats (bookingId, passengerId, seatNumber) values (?, ?, ?)", bookedSeatsValues,
            false);
      }
    }
  };

  /**
   * Updates a pre-existing booking entry in the database.
   * 
   * @param booking The {@link Booking} to be updated in the database.
   */
  public void updateBooking(Booking booking) {
    Database db = new Database(databasePath);
    db.open();

    String[] PassengerValues = { booking.getBookingPurchaser().getId(), booking.getBookingPurchaser().getName() };

    db.query("UPDATE Passengers SET name = ? WHERE passengerId = ?", PassengerValues, false);
    String[] BookingValues = { booking.getBookingPurchaser().getId(), booking.getBookingId(),
        booking.getFlight().getFlightNr(), booking.getBookingDate().toString(), booking.isInsured().toString() };
    db.query("UPDATE Bookings SET passengerId = ?, flightNr = ?, bookingDate = ?, insured = ? WHERE bookingId = ?",
        BookingValues, false);
    for (Seat seat : booking.getSeatsInBooking()) {
      String[] bookedSeatsValues = { booking.getBookingId(), booking.getBookingPurchaser().getId(), seat.getSeatNr() };
      String[] seatsValues = { booking.getFlight().getFlightNr(), seat.getSeatNr() };
      if (!seat.getReservationStatus()) {
        db.query("UPDATE Seats set reserved = True WHERE flightNr = ? AND seatNumber = ?", seatsValues, false);
        db.query("UPDATE BookedSeats SET passengerId = ?, seatNumber = ? WHERE bookingId = ?", bookedSeatsValues,
            false);
      }
    }
    db.close();
  }

  /**
   * Gets the booking entry with the specified booking id from the database.
   * and converts it into a {@link Booking} object that is then returned.
   * 
   * @return {@link Booking} object with the queried booking id.
   */
  public Booking getBookingById(String bookingId) {
    Database db = new Database(databasePath);
    db.open();

    String[] bookingIdValue = { bookingId };
    ResultSet rs = db.query("SELECT * FROM Bookings WHERE bookingId = ?", bookingIdValue, true);

    Booking booking = null;

    try {
      while (rs.next()) {
        Boolean insured = rs.getBoolean("insured");

        ResultSet SeatsRs = db.query("SELECT * FROM BookedSeats WHERE bookingId = ?", bookingIdValue, true);

        List<Seat> seats = new ArrayList<Seat>();
        while (SeatsRs.next()) {
          String flightNrId = SeatsRs.getString("flightNr");
          String bookedSeatNr = SeatsRs.getString("seatNumber");
          Boolean reserved = SeatsRs.getBoolean("reserved");
          Seat tempSeat = new Seat(flightNrId, bookedSeatNr, reserved);
          seats.add(tempSeat);
        }

        ResultSet PassengerRs = db.query("SELECT * FROM Passengers WHERE passengerId = ?", bookingIdValue, true);

        Passenger passenger = new Passenger(PassengerRs.getString("passengerId"), PassengerRs.getString("name"));

        ResultSet FlightRs = db.query(
            "SELECT * FROM Flights WHERE flightNr = (SELECT FlightNr FROM Bookings WHERE bookingId = ?)",
            bookingIdValue, true);

        String flightNrId = FlightRs.getString("flightNr");
        String departureAddressId = FlightRs.getString("departureAddress");
        String arrivalAddressId = FlightRs.getString("arrivalAddress");
        LocalDate departureTimeId = FlightRs.getObject("departureTime", LocalDate.class);
        LocalDate arrivalTimeId = FlightRs.getObject("arrivalTime", LocalDate.class);
        Integer priceId = FlightRs.getInt("price");

        Flight tempFlight = new Flight(flightNrId, (ArrayList<Seat>) seats, departureAddressId, arrivalAddressId,
            departureTimeId, arrivalTimeId, priceId);

        booking = new Booking(passenger, tempFlight, null, seats, insured);
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      db.close();
    }
    return booking;
  }

  /**
   * Gets all bookings from the database made by the passenger with the specified
   * passenger id.
   * and converts them into a list of {@link Booking} objects that is then
   * returned.
   * 
   * @param id The passenger id being queried
   * @return List of all {@link Booking} belonging to the passenger with the
   *         specified passenger id.
   */
  public List<Booking> getBookingByPurchaserId(String id) {
    Database db = new Database(databasePath);
    db.open();
    List<Booking> bookings = new ArrayList<Booking>();
    String[] purchaserRequest = { id };
    ResultSet rs = db.query("SELECT * FROM Bookings WHERE purchaserId = ?", purchaserRequest, true);

    try {
      while (rs.next()) {
        String bookingId = rs.getString("bookingId");
        Boolean insured = rs.getBoolean("insured");
        String[] purchaserId = { rs.getString("purchaserId") };

        String[] bookingIdValue = { bookingId };
        ResultSet SeatsRs = db.query("SELECT * FROM BookedSeats WHERE bookingId = ?", bookingIdValue, true);

        List<Seat> seats = new ArrayList<Seat>();
        while (SeatsRs.next()) {
          String flightNrId = SeatsRs.getString("flightNr");
          String bookedSeatNr = SeatsRs.getString("seatNumber");
          Boolean reserved = SeatsRs.getBoolean("reserved");
          Seat tempSeat = new Seat(flightNrId, bookedSeatNr, reserved);
          seats.add(tempSeat);
        }

        ResultSet PassengerRs = db.query("SELECT * FROM Passengers WHERE passengerId = ?", purchaserId, true);

        Passenger passenger = new Passenger(PassengerRs.getString("passengerId"), PassengerRs.getString("name"));

        ResultSet FlightRs = db.query(
            "SELECT * FROM Flights WHERE flightNr = (SELECT FlightNr FROM Bookings WHERE bookingId = ?)",
            bookingIdValue, true);

        String flightNrId = FlightRs.getString("flightNr");
        String departureAddressId = FlightRs.getString("departureAddress");
        String arrivalAddressId = FlightRs.getString("arrivalAddress");
        LocalDate departureTimeId = FlightRs.getObject("departureTime", LocalDate.class);
        LocalDate arrivalTimeId = FlightRs.getObject("arrivalTime", LocalDate.class);
        Integer priceId = FlightRs.getInt("price");

        Flight tempFlight = new Flight(flightNrId, (ArrayList<Seat>) seats, departureAddressId, arrivalAddressId,
            departureTimeId, arrivalTimeId, priceId);

        bookings.add(new Booking(passenger, tempFlight, null, seats, insured));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      db.close();
    }
    return bookings;
  };

  /**
   * Gets all bookings from the database sorted by booking date.
   * and converts them into a list of {@link Booking} objects that is then
   * returned.
   * 
   * @return List of all {@link Booking} entries sorted by booking date.
   */
  public List<Booking> getAllBookings() {
    List<Booking> bookings = new ArrayList<Booking>();
    Database db = new Database(databasePath);
    db.open();

    ResultSet rs = db.query("SELECT * FROM Bookings", null, true);

    try {
      while (rs.next()) {
        String bookingId = rs.getString("bookingId");
        Boolean insured = rs.getBoolean("insured");

        String[] bookingIdValue = { bookingId };
        ResultSet SeatsRs = db.query("SELECT * FROM BookedSeats WHERE bookingId = ?", bookingIdValue, true);

        List<Seat> seats = new ArrayList<Seat>();
        while (SeatsRs.next()) {
          String flightNrId = SeatsRs.getString("flightNr");
          String bookedSeatNr = SeatsRs.getString("seatNumber");
          Boolean reserved = SeatsRs.getBoolean("reserved");
          Seat tempSeat = new Seat(flightNrId, bookedSeatNr, reserved);
          seats.add(tempSeat);
        }

        ResultSet PassengerRs = db.query("SELECT * FROM Passengers WHERE passengerId = ?", null, true);

        Passenger passenger = new Passenger(PassengerRs.getString("passengerId"), PassengerRs.getString("name"));

        ResultSet FlightRs = db.query(
            "SELECT * FROM Flights WHERE flightNr = (SELECT FlightNr FROM Bookings WHERE bookingId = ?)",
            bookingIdValue, true);

        String flightNrId = FlightRs.getString("flightNr");
        String departureAddressId = FlightRs.getString("departureAddress");
        String arrivalAddressId = FlightRs.getString("arrivalAddress");
        LocalDate departureTimeId = FlightRs.getObject("departureTime", LocalDate.class);
        LocalDate arrivalTimeId = FlightRs.getObject("arrivalTime", LocalDate.class);
        Integer priceId = FlightRs.getInt("price");

        Flight tempFlight = new Flight(flightNrId, (ArrayList<Seat>) seats, departureAddressId, arrivalAddressId,
            departureTimeId, arrivalTimeId, priceId);

        bookings.add(new Booking(passenger, tempFlight, null, seats, insured));
      }
    } catch (SQLException e) {
      e.printStackTrace();
    } finally {
      db.close();
    }
    return bookings;

  };

  /**
   * Deletes a pre-existing booking entry from the database.
   * 
   * @param booking The {@link Booking} to be deleted from the database.
   */
  public void deleteBooking(Booking booking) {
    Database db = new Database(databasePath);
    db.open();

    String[] bookingId = { booking.getBookingId() };

    for (Seat seat : booking.getSeatsInBooking()) {
      String[] bookedSeatsValues = { booking.getBookingId(), booking.getBookingPurchaser().getId(), seat.getSeatNr() };

      if (seat.getReservationStatus()) {
        db.query("UPDATE Seats set reserved = False WHERE flightNr = ? AND seatNumber = ?", bookedSeatsValues, false);
        db.query("DELETE FROM BookedSeats where bookingId = ?", bookingId, false);
      }

      db.query("DELETE FROM Bookings WHERE bookingId = ?", bookingId, false);

      db.close();
    }
    ;

  }
}
