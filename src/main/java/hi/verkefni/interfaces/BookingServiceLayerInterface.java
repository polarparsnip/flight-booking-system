package hi.verkefni.interfaces;

import java.sql.SQLException;
import java.util.List;

import hi.verkefni.classes.Booking;
import hi.verkefni.classes.Passenger;
import hi.verkefni.classes.Seat;

public interface BookingServiceLayerInterface {

  public void createBooking(Booking booking) throws SQLException;


  public void addPassengerToBooking(Booking booking, Seat seat) throws SQLException;


  public void removePassengerFromBooking(Booking booking, Passenger passenger, Seat seat) throws SQLException;


  public void deleteBooking(Booking booking) throws SQLException;


  public List<Booking> getAllBookings();


  public List<Booking> getBookingByKennitala(String kennitala);


  public List<Booking> getBookingByPurchaserId(String id);

}
