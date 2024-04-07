package hi.verkefni.serviceLayers;

import java.sql.SQLException;
import java.util.List;

import hi.verkefni.classes.Booking;
import hi.verkefni.classes.Seat;
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

    String[] PassengerValues = { booking.getBookingPurchaser().getId(), booking.getBookingPurchaser().getName(),
        booking.getBookingPurchaser().getKennitala() };

    db.query("insert or ignore into Passengers (passengerId, name, kennitala) values (?, ?, ?)", PassengerValues,
        false);

    String[] BookingValues = { booking.getBookingPurchaser().getId(), booking.getBookingId(),
        booking.getFlight().getFlightNr(), booking.getBookingDate().toString(), booking.isInsured().toString() };

    db.query("insert into Bookings (passengerId, bookingId, flightNr, bookingDate, insured) values (?, ?, ?, ?, ?)",
        BookingValues, false);

    for (Seat seat : booking.getSeatsInBooking()) {
      String[] SeatValues = { booking.getBookingId(), booking.getBookingPurchaser().getId(), seat.getSeatNr() };
      if (!seat.getReservationStatus()) {
        db.query("UPDATE Seats set bookingId = ?, passengerId = ? and seatNumber = ?", SeatValues, false);
      }
    }

    db.close();
  };

  /**
   * Updates a pre-existing booking entry in the database.
   * 
   * @param booking The {@link Booking} to be updated in the database.
   */
  public void updateBooking(Booking booking) {
  };

  /**
   * Gets the booking entry with the specified booking id from the database.
   * and converts it into a {@link Booking} object that is then returned.
   * 
   * @return {@link Booking} object with the queried booking id.
   */
  public Booking getBookingById(String bookingId) {
  };

  /**
   * Gets all bookings from the database made by the passenger with the specified
   * kennitala.
   * and converts them into a list of {@link Booking} objects that is then
   * returned.
   * 
   * @param kennitala The kennitala being queried.
   * @return List of all {@link Booking} belonging to the passenger with the
   *         specified kennitala.
   */
  public List<Booking> getBookingByKennitala(String kennitala) {
  };

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
  };

  /**
   * Gets all bookings from the database sorted by booking date.
   * and converts them into a list of {@link Booking} objects that is then
   * returned.
   * 
   * @return List of all {@link Booking} entries sorted by booking date.
   */
  public List<Booking> getAllBookings() {
  };

  /**
   * Deletes a pre-existing booking entry from the database.
   * 
   * @param booking The {@link Booking} to be deleted from the database.
   */
  public void deleteBooking(Booking booking) {
  };

}
