package flight.vidmot.mocks;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import flight.classes.Booking;
import flight.classes.User;
import flight.interfaces.BookingServiceLayerInterface;


public class BookingServiceLayerMock implements BookingServiceLayerInterface {

  private final ArrayList<Booking> bookings;
  

  /**
   * Constructor for the mock booking service layer.
   */
  public BookingServiceLayerMock() {
    bookings = new ArrayList<>();
  }

  /**
   * Mock method that saves the specified {@link Booking} in the system.
   * 
   * @param booking The {@link Booking} object to be saved.
   */
  public void createBooking(Booking booking) {
    bookings.add(booking);
  }


  /**
   * Mock method that updates the specified {@link Booking} in the system.
   * Since {@link Booking} the object was already modified in the controller, 
   * this method does not do anything here in the mock service layer.
   * 
   * @param booking The {@link Booking} object to be updated.
   */
  public void updateBooking(Booking booking) {
    return;
  }


  /**
   * Mock method that deletes the specified {@link Booking} from the system.
   * 
   * @param booking The {@link Booking} object to be updated.
   */
  public void deleteBooking(Booking booking) {
    bookings.remove(booking);
  }


  /**
   * Mock method that gets the booking entry with the specified booking id from the system.
   * 
   * @return {@link Booking} entry with the queried booking id.
   */
  public Booking getBookingById(String bookingId) {

    Booking idBooking = null;

    for (Booking b : bookings) {
      if (b.getBookingId().equals(bookingId)) {
        idBooking = b;
      }
    }

    return idBooking;
  }


  /**
   * Mock method that gets all {@link Booking} entries in the system booked by 
   * the {@link User} with the specified user id.
   * 
   * @param id The user id of the {@link User} whose bookings are being queried.
   * @return List of all {@link Booking} objects of the {@link User} with the specified user id.
   */
  public List<Booking> getBookingsByUserId(String id) {

    ArrayList<Booking> idFilteredBookings = new ArrayList<>();

    for (Booking b : bookings) {
      if (b.getBookingPurchaser().getId().equals(id)) {
        idFilteredBookings.add(b);
      }
    }

    idFilteredBookings.sort(new Comparator<Booking>() {
      public int compare(Booking Booking1, Booking Booking2) {
        return Booking1.getBookingDate().compareTo(Booking2.getBookingDate());
      }
    });

    return idFilteredBookings;
  }


  /**
   * Mock method that gets all saved {@link Booking} entries in the system.
   * 
   * @return List of all {@link Booking} entries saved in the system sorted by booking date.
   */
  public List<Booking> getAllBookings() {

    List<Booking> sortedBookings = bookings;

    if (sortedBookings.size() > 1) {
      sortedBookings.sort(new Comparator<Booking>() {
        public int compare(Booking Booking1, Booking Booking2) {
          return Booking1.getBookingDate().compareTo(Booking2.getBookingDate());
        }
      });
    }

    return sortedBookings;
  }

}
