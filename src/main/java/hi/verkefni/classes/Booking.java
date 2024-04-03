package hi.verkefni.classes;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Booking {
  private final Flight flight;
  private final String subBookingId;

  private Map<String, Seat> passengerSeats;

  public Booking(String bookingId, Flight flight, List<Passenger> passengers, List<Seat> seats) {
    this.subBookingId = bookingId;
    this.flight = flight;
    
    passengerSeats = new HashMap<>();

    for (int i = 0; i < passengers.size(); i++) {
      seats.get(i).updateReservation(true);
      passengerSeats.put(passengers.get(i).getId(), seats.get(i));
    }

  }

  public String getSubBookingId() {
    return subBookingId;
  }

  public Flight getFlight() {
    return flight;
  }

  public String getFlightNr() {
    return flight.getFlightNr();
  }

  public void addPassengerToBooking(Passenger passenger, Seat seat) {
    passengerSeats.put(passenger.getId(), seat);
  }

  public void removePassengerFromBooking(Passenger passenger) {
    passengerSeats.get(passenger.getId()).updateReservation(false);
    passengerSeats.remove(passenger.getId());
  }
  
  public int getBookingPrice() {
    return flight.getPrice() * passengerSeats.size();
  }

}
