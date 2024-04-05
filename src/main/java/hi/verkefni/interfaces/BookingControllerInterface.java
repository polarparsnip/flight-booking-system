package hi.verkefni.interfaces;

import java.util.List;

import hi.verkefni.classes.Booking;
import hi.verkefni.classes.Flight;
import hi.verkefni.classes.Seat;
import hi.verkefni.classes.Passenger;


/**
 * The BookingControllerInterface provides methods for managing flight bookings in the system.
 * Implementing classes should provide functionality to create, modify, retrieve, and delete bookings.
 */
public interface BookingControllerInterface {

  /**
   * Creates a new flight {@link Booking} in the system.
   * 
   * @param purchaser The {@link Passenger} that booked the {@link Flight}.
   * @param flight The {@link Flight} being booked.
   * @param passengers The list of {@link Passenger} in this {@link Booking}.
   * @param seats The list of {@link Seat} being booked in the {@link Flight}.
   * @param insured Boolean value for whether the {@link Booking} is insured.
   * @return {@link Booking} object of the {@link Booking} that was just created.
   */
  public Booking createBooking(
    Passenger purchaser, 
    Flight flight, 
    List<Passenger> passengers, 
    List<Seat> seats, 
    Boolean insured
  );


  /**
   * Adds a passenger to a pre-existing {@link Booking}.
   * 
   * @param booking The {@link Booking} that the {@link Passenger} will be added to.
   * @param passenger The {@link Passenger} that will be added to the {@link Booking}.
   * @param seat The {@link Seat} that will be reserved for the {@link Passenger} being added.
   * 
   */
  public void addPassengerToBooking(Booking booking, Passenger passenger, Seat seat);


  /**
   * Removes a {@link Passenger} from a pre-existing {@link Booking}.
   * 
   * @param booking The {@link Booking} that the {@link Passenger} will be removed from.
   * @param passenger The {@link Passenger} to be removed from the {@link Booking}.
   */
  public void removePassengerFromBooking(Booking booking, Passenger passenger);


  /**
   * Gets the {@link Booking} entry with the specified booking id.
   * 
   * @return {@link Booking} entry with the queried booking id.
   */
  public Booking getBookingById(String bookingId);


  /**
   * Gets all {@link Booking} entries booked by the {@link Passenger} with the specified kennitala.
   * 
   * @param kennitala The kennitala of the {@link Passenger} for the bookings being queried.
   * @return List of all {@link Booking} objects of the {@link Passenger} with the specified kennitala.
   */
  public List<Booking> getBookingsByKennitala(String kennitala);


  /**
   * Gets all {@link Booking} entries booked by the {@link Passenger} with the specified passenger id.
   * 
   * @param id The passenger id of the {@link Passenger} whose bookings are being queried.
   * @return List of all {@link Booking} objects of the {@link Passenger} with the specified passenger id.
   */
  public List<Booking> getBookingsByPurchaserId(String id);


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
