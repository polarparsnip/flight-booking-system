package hi.verkefni.interfaces;

import java.sql.SQLException;
import java.util.List;

import hi.verkefni.classes.Booking;


public interface BookingServiceLayerInterface {

  /**
   * Creates a booking entry in the database based on a {@link Booking} object
   * 
   * @param booking The {@link Booking} to be inserted in the database
   */
  public void createBooking(Booking booking) throws SQLException;


  /**
   * Updates a pre-existing booking entry in the database 
   * 
   * @param booking The {@link Booking} to be updated in the database
   */
  public void updateBooking(Booking booking) throws SQLException;


  /**
   * Gets the booking entry with the specified booking id from the database
   * 
   * @return {@link Booking} entry with the queried booking id
   */
  public Booking getBookingById(String bookingId);


  /**
   * Gets all bookings from the database made by the passenger with the specified kennitala
   * 
   * @param kennitala The kennitala being queried
   * @return List of all {@link Booking} in the database belonging to the passenger 
   * with the specified kennitala
   */
  public List<Booking> getBookingByKennitala(String kennitala);


  /**
   * Gets all bookings from the database made by the passenger with the specified passenger id
   * 
   * @param id The passenger id being queried
   * @return List of all {@link Booking} in the database belonging to the passenger 
   * with the specified passenger id
   */
  public List<Booking> getBookingByPurchaserId(String id);


  /**
   * Gets all bookings from the database sorted by booking date
   * 
   * @return List of all {@link Booking} in the database sorted by booking date
   */
  public List<Booking> getAllBookings();


  /**
   * Deletes a pre-existing booking entry from the database
   * 
   * @param booking The {@link Booking} to be deleted from the database
   */
  public void deleteBooking(Booking booking) throws SQLException;
  
}
