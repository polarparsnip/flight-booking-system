package hi.verkefni.interfaces;

import java.util.List;

import hi.verkefni.classes.Booking;
import hi.verkefni.classes.Flight;
import hi.verkefni.classes.Seat;
import hi.verkefni.classes.Passenger;

public interface BookingControllerInterface {

  public Booking createBooking(
    Passenger purchaser, 
    Flight flight, 
    List<Passenger> passengers, 
    List<Seat> seats, 
    Boolean extraLuggage, 
    Boolean insured
  );


  public void addPassengerToBooking(Booking booking, Passenger passenger, Seat seat);


  public void removePassengerFromBooking(Booking booking, Passenger passenger);


  public void deleteBooking(Booking booking);


  public List<Booking> getAllBookings();


  public List<Booking> getBookingsByKennitala(String kennitala);


  public List<Booking> getBookingsByPurchaserId(String id);

}
