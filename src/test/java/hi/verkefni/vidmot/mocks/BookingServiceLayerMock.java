package hi.verkefni.vidmot.mocks;

import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;

import hi.verkefni.classes.Booking;
import hi.verkefni.classes.Passenger;
import hi.verkefni.classes.Seat;
import hi.verkefni.interfaces.BookingServiceLayerInterface;


public class BookingServiceLayerMock implements BookingServiceLayerInterface {

  private final ArrayList<Booking> bookings;
  

  /**
   * Constructor for the mock booking service layer
   */
  public BookingServiceLayerMock() {
    bookings = new ArrayList<>();
  }


  public void createBooking(Booking booking) {
    bookings.add(booking);
  }


  public void addPassengerToBooking(Booking booking, Seat seat) {
    return;
  }


  public void removePassengerFromBooking(Booking booking, Passenger passenger, Seat seat) {
    booking.removePassengerFromBooking(passenger);
  }


  public void deleteBooking(Booking booking) {
    bookings.remove(booking);
  }


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


  public List<Booking> getBookingByPurchaserId(String id) {

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


  public List<Booking> getBookingByKennitala(String kennitala) {

    ArrayList<Booking> ktFilteredBookings = new ArrayList<>();

    for (Booking b : bookings) {
      if (b.getBookingPurchaser().getKennitala().equals(kennitala)) {
        ktFilteredBookings.add(b);
      }
    }

    ktFilteredBookings.sort(new Comparator<Booking>() {
      public int compare(Booking Booking1, Booking Booking2) {
        return Booking1.getBookingDate().compareTo(Booking2.getBookingDate());
      }
    });

    return ktFilteredBookings;
  }

}
