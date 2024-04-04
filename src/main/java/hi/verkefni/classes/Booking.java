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
  private Boolean insured;
  private LocalDate bookingDate;
  private Map<String, Seat> bookedSeats;

  /**
   * Booking constructor
   * 
   * @param purchaser The {@link Passenger} that booked this booking
   * @param flight The {@link Flight} this booking is for
   * @param passengers The list of {@link Passenger} this booking is for
   * @param seats The list of {@link Seat} to be reserved for all the {@link Passenger} of this booking
   * @param insured Boolean specifying whether this flight booking is insured
   */
  public Booking(
    Passenger purchaser, 
    Flight flight, 
    List<Passenger> passengers, 
    List<Seat> seats, 
    Boolean insured
  ) {

    this.purchaser = purchaser;
    this.flight = flight;
    this.insured = insured;
    bookingDate = LocalDate.now();

    this.bookingId = String.format("BKNG%s-%s-%s", flight.getFlightNr(), purchaser.getId(), bookingDate.toString());
    
    bookedSeats = new HashMap<>();

    for (int i = 0; i < passengers.size(); i++) {
      seats.get(i).updateReservation(true);
      bookedSeats.put(passengers.get(i).getId(), seats.get(i));
    }

  }


  /**
   * Returns the booking id for this booking
   * 
   * @return The id for this booking
   */
  public String getBookingId() {
    return bookingId;
  }


  /**
   * Returns the flight this booking is for
   * 
   * @return The flight this booking is for
   */
  public Flight getFlight() {
    return flight;
  }


  /**
   * Returns the flight number of the flight this booking is for
   * 
   * @return The flight number of the flight this booking is for
   */
  public String getFlightNr() {
    return flight.getFlightNr();
  }


  /**
   * Adds a {@link Passenger} to this booking
   * The {@link Seat} reservation status is updated alongside the booking
   * 
   * @param passenger The {@link Passenger} to be added to this booking
   */
  public void addPassengerToBooking(Passenger passenger, Seat seat) {
    seat.updateReservation(true);
    bookedSeats.put(passenger.getId(), seat);
  }


  /**
   * Removes a {@link Passenger} from this booking
   * The {@link Seat} reservation status is updated alongside the booking
   * 
   * @param passenger The {@link Passenger} to be removed from this booking
   */
  public void removePassengerFromBooking(Passenger passenger) {
    bookedSeats.get(passenger.getId()).updateReservation(false);
    bookedSeats.remove(passenger.getId());
  }
  

  /**
   * Returns the price of this booking 
   * 
   * @return Price of this booking
   */
  public int getBookingPrice() {
    return flight.getPrice() * bookedSeats.size();
  }


  /**
   * Returns the {@link Passenger} that booked this booking 
   * 
   * @return {@link Passenger} that booked this booking 
   */
  public Passenger getBookingPurchaser() {
    return purchaser;
  }


  /**
   * Returns whether this booking has baught booking insurance 
   * 
   * @return Boolean specifying whether this booking has insurance attached to it
   */
  public Boolean isInsured() {
    return insured;
  }


  /**
   * Updates the insurance status of this booking
   * 
   * @param insured Boolean specifying the new insurance status of this booking
   */
  public void updateBookingInsurance(Boolean insured) {
    this.insured = insured;
  }


  /**
   * Gets the booking date of this booking
   * 
   * @return Booking date of this booking
   */
  public LocalDate getBookingDate() {
    return bookingDate;
  }


  /**
   * Gets the id of all the passengers reserved in this booking
   * 
   * @return List of all ids for the passengers reserved in this booking
   */
  public List<String> getPassengersInBooking() {
    List<String> passengersInBooking = new ArrayList<>(bookedSeats.keySet());
    return passengersInBooking;
  }


  /**
   * Gets all the {@link Seat} reserved in this booking
   * 
   * @return List of {@link Seat} reserved in this booking
   */
  public List<Seat> getSeatsInBooking() {
    Collection<Seat> seatValues = bookedSeats.values();
    List<Seat> bookedSeats = new ArrayList<>(seatValues);
    return bookedSeats;
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


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    
    sb.append("Bókunar Auðkenni: ").append(bookingId).append("\n");
    sb.append("Kaupandi: ").append(purchaser.getName()).append("\n");
    sb.append("Flugnúmer: ").append(flight.getFlightNr()).append("\n");
    sb.append("Dagsetning bókunar: ").append(bookingDate).append("\n");
    sb.append("Fjöldi farþega í bókun: ").append(bookedSeats.size()).append("\n");

    String insuredStatusString = insured ? "Já" : "nei";
    sb.append("Flugtrygging: ").append(insuredStatusString).append("\n");

    for (Map.Entry<String, Seat> entry : bookedSeats.entrySet()) {
      sb.append("Farþegar í bókun: ").append(entry.getKey()).append(", Seat: ").append(entry.getValue()).append("\n");
    }

    return sb.toString();
  }

}
