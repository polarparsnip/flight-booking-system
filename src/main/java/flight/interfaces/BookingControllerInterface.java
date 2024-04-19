package flight.interfaces;

import java.time.LocalDate;
import java.util.List;

import flight.classes.Booking;
import flight.classes.Flight;
import flight.classes.Seat;
import flight.classes.User;


/**
 * The BookingControllerInterface provides methods for managing flight bookings in the system.
 * Implementing classes should provide functionality to create, modify, retrieve, and delete bookings.
 */
public interface BookingControllerInterface {

  /**
   * Creates a new flight {@link Booking} in the system.
   * 
   * @param purchaser The {@link User} that booked the {@link Flight}.
   * @param flight The {@link Flight} being booked.
   * @param seats The list of {@link Seat} being booked in the {@link Flight}.
   * @param bookingDate The creation date of the booking.
   * @param insured Boolean value for whether the {@link Booking} is insured.
   * @param specialAssistance Boolean specifying whether this flight booking need special assistance
   * @return {@link Booking} object of the {@link Booking} that was just created.
   */
  public Booking createBooking(
    User purchaser, 
    Flight flight, 
    List<Seat> seats,
    LocalDate bookingDate,
    Boolean insured,
    Boolean specialAssistance
  );


  /**
   * Adds a seat to a pre-existing {@link Booking}.
   * 
   * @param booking The {@link Booking} that the {@link Seat} will be added to.
   * @param seat The {@link Seat} that will be reserved.
   * 
   */
  public void addSeatToBooking(Booking booking, Seat seat);


  /**
   * Removes a {@link Seat} from a pre-existing {@link Booking}.
   * 
   * @param booking The {@link Booking} that the {@link Seat} will be removed from.
   * @param seat The {@link Seat} to be removed from the {@link Booking}.
   */
  public void removeSeatFromBooking(Booking booking, Seat seat);


  /**
   * Gets the {@link Booking} entry with the specified booking id.
   * 
   * @return {@link Booking} entry with the queried booking id.
   */
  public Booking getBookingById(String bookingId);


  /**
   * Gets all {@link Booking} entries booked by the {@link User} with the specified user id.
   * 
   * @param id The user id of the {@link User} whose bookings are being queried.
   * @return List of all {@link Booking} objects of the {@link User} with the specified user id.
   */
  public List<Booking> getBookingsByUserId(String id);


  /**
   * Gets all {@link Booking} entries.
   * 
   * @return List of all {@link Booking} entries in the system.
   */
  public List<Booking> getAllBookings();


  /**
   * Deletes a pre-existing {@link Booking} entry.
   * 
   * @param booking The {@link Booking} to be deleted.
   */
  public void deleteBooking(Booking booking);

}
