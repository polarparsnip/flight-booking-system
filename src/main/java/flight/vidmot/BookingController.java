package flight.vidmot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import flight.classes.Booking;
import flight.classes.Flight;
import flight.classes.Seat;
import flight.classes.User;
import flight.interfaces.BookingControllerInterface;
import flight.interfaces.BookingServiceLayerInterface;
import flight.serviceLayers.BookingServiceLayer;



/**
 * The BookingController provides methods for managing flight bookings in the system.
 */
public class BookingController implements BookingControllerInterface {

  private final BookingServiceLayerInterface BSL;

  /**
   * Constructor for the booking controller
   * 
   * @param BSL The {@link BookingServiceLayer} that the booking controller will use 
   * to manage bookings in the system.
   */
  public BookingController(BookingServiceLayerInterface BSL) {
    this.BSL = BSL;
  }


  /**
   * Creates a new flight {@link Booking} in the system.
   * 
   * @param purchaser The {@link User} that booked the {@link Flight}.
   * @param flight The {@link Flight} being booked.
   * @param seats The list of {@link Seat} being booked in the {@link Flight}.
   * @param insured Boolean value for whether the {@link Booking} is insured.
   * @param specialAssistance Boolean specifying whether this flight booking need special assistance
   * @return {@link Booking} object of the {@link Booking} that was just created.
   */
  public Booking createBooking(User purchaser, Flight flight, List<Seat> seats, LocalDate bookingDate, Boolean insured, Boolean specialAssistance) {

    Booking booking = new Booking(purchaser, flight, seats, bookingDate, insured, specialAssistance);

    try {
      BSL.createBooking(booking);
    } catch (Exception e) {
      System.err.println("Error in creating flight booking: " + e);
    }

    return booking;
  }


  /**
   * Adds a seat to a pre-existing {@link Booking}.
   * 
   * @param booking The {@link Booking} that the {@link Seat} will be added to.
   * @param seat The {@link Seat} that will be reserved.
   * 
   */
  public void addSeatToBooking(Booking booking, Seat seat) {
    try {
      booking.addSeatToBooking(seat);
      BSL.updateBooking(booking);
    } catch (Exception e) {
      System.err.println("Error in adding seat to booking: " + e);
    }
  }


  /**
   * Removes a {@link Seat} from a pre-existing {@link Booking}.
   * 
   * @param booking The {@link Booking} that the {@link Seat} will be removed from.
   * @param seat The {@link Seat} to be removed from the {@link Booking}.
   */
  public void removeSeatFromBooking(Booking booking, Seat seat) {
    try {
      booking.removeSeatFromBooking(seat);
      BSL.updateBooking(booking);
    } catch (Exception e) {
      System.err.println("Error in removing seat from booking: " + e);
    }
  }


  /**
   * Gets the {@link Booking} entry with the specified booking id.
   * 
   * @return {@link Booking} entry with the queried booking id.
   */
  public Booking getBookingById(String bookingId) {

    Booking idBooking = null;

    try {
      idBooking = BSL.getBookingById(bookingId);
    } catch (Exception e) {
      System.err.println("Error in getting booking using booking id: " + e);
    }

    return idBooking;
  }


  /**
   * Gets all {@link Booking} entries booked by the {@link User} with the specified user id.
   * 
   * @param id The user id of the {@link User} whose bookings are being queried.
   * @return List of all {@link Booking} objects of the {@link User} with the specified user id.
   */
  public List<Booking> getBookingsByUserId(String id) {

    List<Booking> idBookings = new ArrayList<>();;

    try {
      idBookings = BSL.getBookingsByUserId(id);
    } catch (Exception e) {
      System.err.println("Error in getting booking by user id: " + e);
    }

    return idBookings;
  }


  /**
   * Gets all {@link Booking} entries.
   * 
   * @return List of all {@link Booking} entries in the system.
   */
  public List<Booking> getAllBookings() {

    List<Booking> allBookings = new ArrayList<>();

    try {
      allBookings = BSL.getAllBookings();
    } catch (Exception e) {
      System.err.println("Error in getting all bookings: " + e);
    }

    return allBookings;
  }


  /**
   * Deletes a pre-existing {@link Booking} entry.
   * 
   * @param booking The {@link Booking} to be deleted.
   */
  public void deleteBooking(Booking booking) {
    try {
      BSL.deleteBooking(booking);
    } catch (Exception e) {
      System.err.println("Error in deleting booking: " + e);
    }
  }

}
