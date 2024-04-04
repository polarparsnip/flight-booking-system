package hi.verkefni.vidmot.mocks;

import java.util.List;

import hi.verkefni.classes.Booking;
import hi.verkefni.classes.Passenger;
import hi.verkefni.classes.Seat;
import hi.verkefni.interfaces.BookingServiceLayerInterface;


public class BookingServiceLayerFail implements BookingServiceLayerInterface {

  /**
   * Mock booking service layer that imitates having no database connection
   * All methods throw a connection issue error
   */
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


  public List<Booking> getBookingByKennitala(String kennitala) {
    throw new RuntimeException("No database connection");
  }


  public List<Booking> getBookingByPurchaserId(String id) {
    throw new RuntimeException("No database connection");
  }

}
