package hi.verkefni.vidmot;

import java.util.ArrayList;
import java.util.List;
import hi.verkefni.classes.Booking;
import hi.verkefni.classes.Flight;
import hi.verkefni.classes.Seat;
import hi.verkefni.classes.Passenger;
import hi.verkefni.interfaces.BookingControllerInterface;
import hi.verkefni.interfaces.BookingServiceLayerInterface;

public class BookingController implements BookingControllerInterface {

  private final BookingServiceLayerInterface BSL;


  public BookingController(BookingServiceLayerInterface BSL) {
    this.BSL = BSL;
  }


  public Booking createBooking(Passenger purchaser, Flight flight, List<Passenger> passengers, List<Seat> seats, Boolean extraLuggage, Boolean insured) {

    Booking booking = new Booking(purchaser, flight, passengers, seats, extraLuggage, insured);

    try {
      BSL.createBooking(booking);
    } catch (Exception e) {
      System.err.println("Error in creating flight booking: " + e);
    }

    return booking;
  }


  public void addPassengerToBooking(Booking booking, Passenger passenger, Seat seat) {
    try {
      booking.addPassengerToBooking(passenger, seat);
      BSL.updateBooking(booking);
    } catch (Exception e) {
      System.err.println("Error in adding passenger to booking: " + e);
    }
  }


  public void removePassengerFromBooking(Booking booking, Passenger passenger) {
    try {
      booking.removePassengerFromBooking(passenger);
      BSL.updateBooking(booking);
    } catch (Exception e) {
      System.err.println("Error in removing passenger from booking: " + e);
    }
  }


  public void deleteBooking(Booking booking) {
    try {
      BSL.deleteBooking(booking);
    } catch (Exception e) {
      System.err.println("Error in deleting booking: " + e);
    }
  }


  public List<Booking> getAllBookings() {

    List<Booking> allBookings = new ArrayList<>();

    try {
      allBookings = BSL.getAllBookings();
    } catch (Exception e) {
      System.err.println("Error in getting all bookings: " + e);
    }

    return allBookings;
  }


  public List<Booking> getBookingsByKennitala(String kennitala) {

    List<Booking> ktBookings = new ArrayList<>();;

    try {
      ktBookings = BSL.getBookingByKennitala(kennitala);
    } catch (Exception e) {
      System.err.println("Error in getting booking by kennitala: " + e);
    }

    return ktBookings;
  }


  public List<Booking> getBookingsByPurchaserId(String id) {

    List<Booking> idBookings = new ArrayList<>();;

    try {
      idBookings = BSL.getBookingByPurchaserId(id);
    } catch (Exception e) {
      System.err.println("Error in getting booking by id: " + e);
    }

    return idBookings;
  }

}
