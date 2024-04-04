package hi.verkefni.classes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.time.LocalDate;


public class Booking {
  private final Passenger purchaser;
  private final Flight flight;
  private final String bookingId;
  private Boolean extraLuggage;
  private Boolean insured;
  private LocalDate bookingDate;
  private Map<String, Seat> passengerSeats;

  
  public Booking(
    Passenger purchaser, 
    Flight flight, 
    List<Passenger> passengers, 
    List<Seat> seats, 
    Boolean extraLuggage, 
    Boolean insured
  ) {

    this.purchaser = purchaser;
    this.flight = flight;
    this.extraLuggage = extraLuggage;
    this.insured = insured;
    bookingDate = LocalDate.now();

    this.bookingId = String.format("BKNG%s-%s-%s", flight.getFlightNr(), purchaser.getId(), bookingDate.toString());
    
    passengerSeats = new HashMap<>();

    for (int i = 0; i < passengers.size(); i++) {
      seats.get(i).updateReservation(true);
      passengerSeats.put(passengers.get(i).getId(), seats.get(i));
    }

  }


  public String getSubBookingId() {
    return bookingId;
  }


  public Flight getFlight() {
    return flight;
  }


  public String getFlightNr() {
    return flight.getFlightNr();
  }


  public void addPassengerToBooking(Passenger passenger, Seat seat) {
    seat.updateReservation(true);
    passengerSeats.put(passenger.getId(), seat);
  }


  public Seat removePassengerFromBooking(Passenger passenger) {
    Seat seatToBeRemoved = passengerSeats.get(passenger.getId());
    passengerSeats.get(passenger.getId()).updateReservation(false);
    passengerSeats.remove(passenger.getId());
    return seatToBeRemoved;
  }
  

  public int getBookingPrice() {
    return flight.getPrice() * passengerSeats.size();
  }


  public Passenger getBookingPurchaser() {
    return purchaser;
  }


  public Boolean isInsured() {
    return insured;
  }


  public Boolean hasExtraLuggage() {
    return extraLuggage;
  }


  public void updateBookingExtraLuggage(Boolean extraLuggage) {
    this.extraLuggage = extraLuggage;
  }


  public void updateBookingInsurance(Boolean insured) {
    this.insured = insured;
  }


  public LocalDate getBookingDate() {
    return bookingDate;
  }


  public List<String> getPassengersInBooking() {
    List<String> passengersInBooking = new ArrayList<>(passengerSeats.keySet());
    return passengersInBooking;
  }


  public List<Seat> getSeatsInBooking() {
    Collection<Seat> seatValues = passengerSeats.values();
    List<Seat> passengerSeats = new ArrayList<>(seatValues);
    return passengerSeats;
  }


  @Override
  public boolean equals(Object o) {
    
    if (o == this) {
      return true;
    }

    if (!(o instanceof Booking)) {
      return false;
    }

    Booking b = (Booking) o;

    return bookingId.equals(b.bookingId);
  }

}
