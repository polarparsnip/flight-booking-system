package hi.verkefni.interfaces;

import java.sql.SQLException;
import java.util.List;

import hi.verkefni.classes.Booking;


/**
 * The BookingServiceLayerInterface interface provides methods for interacting with booking entries in the database.
 * Implementing classes should provide functionality to create, update, retrieve, and delete bookings.
 */
public interface BookingServiceLayerInterface {

  /**
   * Creates a booking entry in the database based on a {@link Booking} object.
   * 
   * @param booking The {@link Booking} to be inserted in the database.
   */
  public void createBooking(Booking booking) throws SQLException;


  /**
   * Updates a pre-existing booking entry in the database.
   * 
   * @param booking The {@link Booking} to be updated in the database.
   */
  public void updateBooking(Booking booking) throws SQLException;


  /**
   * Gets the booking entry with the specified booking id from the database.
   * and converts it into a {@link Booking} object that is then returned.
   * 
   * @return {@link Booking} object with the queried booking id.
   */
  public Booking getBookingById(String bookingId) throws SQLException;


  /**
   * Gets all bookings from the database made by the passenger with the specified kennitala.
   * and converts them into a list of {@link Booking} objects that is then returned.
   * 
   * @param kennitala The kennitala being queried.
   * @return List of all {@link Booking} belonging to the passenger with the specified kennitala.
   */
  public List<Booking> getBookingByKennitala(String kennitala) throws SQLException;


  /**
   * Gets all bookings from the database made by the passenger with the specified passenger id.
   * and converts them into a list of {@link Booking} objects that is then returned.
   * 
   * @param id The passenger id being queried
   * @return List of all {@link Booking} belonging to the passenger with the specified passenger id.
   */
  public List<Booking> getBookingByPurchaserId(String id) throws SQLException;


  /**
   * Gets all bookings from the database sorted by booking date.
   * and converts them into a list of {@link Booking} objects that is then returned.
   * 
   * @return List of all {@link Booking} entries sorted by booking date.
   */
  public List<Booking> getAllBookings() throws SQLException;


  /**
   * Deletes a pre-existing booking entry from the database.
   * 
   * @param booking The {@link Booking} to be deleted from the database.
   */
  public void deleteBooking(Booking booking) throws SQLException;
  
}
