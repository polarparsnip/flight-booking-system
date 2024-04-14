package flight.vidmot.mocks;

import java.util.List;

import flight.classes.Booking;
import flight.interfaces.BookingServiceLayerInterface;


/**
 * Mock booking service layer that imitates having no database connection.
 * All methods throw a connection issue error.
 */
public class BookingServiceLayerFail implements BookingServiceLayerInterface {


  public BookingServiceLayerFail() {}


  public void createBooking(Booking booking) {
    throw new RuntimeException("No database connection");
  }


  public void updateBooking(Booking booking) {
    throw new RuntimeException("No database connection");
  }


  public void deleteBooking(Booking booking) {
    throw new RuntimeException("No database connection");
  }


  public List<Booking> getAllBookings() {
    throw new RuntimeException("No database connection");
  }


  public Booking getBookingById(String bookingId) {
    throw new RuntimeException("No database connection");
  }


  public List<Booking> getBookingsByUserId(String id) {
    throw new RuntimeException("No database connection");
  }

}
