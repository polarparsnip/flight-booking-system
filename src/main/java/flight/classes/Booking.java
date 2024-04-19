package flight.classes;

import java.util.List;
import java.util.Random;
import java.time.LocalDate;

public class Booking {
  private final User purchaser;
  private final Flight flight;
  private final String bookingId;
  private Boolean insured;
  private Boolean specialAssistance;
  private LocalDate bookingDate;
  private List<Seat> seats;

  /**
   * Booking constructor
   * 
   * @param purchaser The {@link User} that booked this booking
   * @param flight The {@link Flight} this booking is for
   * @param seats The list of {@link Seat} to be reserved for  this booking
   * @param insured Boolean specifying whether this flight booking is insured
   * @param specialAssistance Boolean specifying whether this flight booking need special assistance
   */
  public Booking(
      User purchaser,
      Flight flight,
      List<Seat> seats,
      LocalDate bookingDate,
      Boolean insured,
      Boolean specialAssistance) {

    this.purchaser = purchaser;
    this.flight = flight;
    this.insured = insured;
    this.specialAssistance = specialAssistance;
    this.bookingDate = bookingDate;
    this.seats = seats;

    Random rand = new Random();
    int randomNr = rand.nextInt(100000000);

    this.bookingId = String.format("BKNG%s-%s-%08d", flight.getFlightNr(), purchaser.getId(), randomNr);

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
   * Adds a {@link Seat} to this booking
   * The {@link Seat} reservation status is updated alongside the booking
   * 
   * @param seat The {@link Seat} to be added to this booking
   */
  public void addSeatToBooking(Seat seat) {
    seat.updateReservation(true);
    seats.add(seat);
  }

  /**
   * Removes a {@link Seat} from this booking
   * The {@link Seat} reservation status is updated alongside the booking
   * 
   * @param seat The {@link Seat} to be removed from this booking
   */
  public void removeSeatFromBooking(Seat seat) {
    seat.updateReservation(false);
    seats.remove(seat);
  }

  /**
   * Returns the price of this booking
   * 
   * @return Price of this booking
   */
  public int getBookingPrice() {
    return flight.getPrice() * seats.size();
  }

  /**
   * Returns the {@link User} that booked this booking
   * 
   * @return {@link User} that booked this booking
   */
  public User getBookingPurchaser() {
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
   * Returns whether this booking needs special assistance
   * 
   * @return Boolean specifying whether this booking needs special assistance
   */
  public Boolean hasSpecialAssistance() {
    return specialAssistance;
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
   * Gets all the {@link Seat} reserved in this booking
   * 
   * @return List of {@link Seat} reserved in this booking
   */
  public List<Seat> getSeatsInBooking() {
    return seats;
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

    // sb.append("Bókunarnúmer: ").append(bookingId).append("\n");
    sb.append(bookingId).append("\n");

    // sb.append("Kaupandi: ").append(purchaser.getName()).append("\n");
    // sb.append("Flugnúmer: ").append(flight.getFlightNr()).append("\n");
    sb.append("Dagsetning bókunar: ").append(bookingDate).append("\n");
    sb.append("Fjöldi sæta í bókun: ").append(seats.size()).append("\n");

    // String insuredStatusString = insured ? "Já" : "nei";
    // sb.append("Flugtrygging: ").append(insuredStatusString).append("\n");

    return sb.toString();
  }

}
