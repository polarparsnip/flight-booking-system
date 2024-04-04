package hi.verkefni.classes;

public class Seat {
  
  private final String seatNr;
  private String flightNr;
  private boolean reserved;

  /**
   * Seat constructor
   * 
   * @param seatNr The number identifier for this seat
   * @param flightNr The flight number of this seat
   * @param reserved Boolean that specifies whether this seat is reserved for a {@link Passenger}
   */
  public Seat(String seatNr, String flightNr, Boolean reserved) {
    this.seatNr = seatNr;
    this.flightNr = flightNr;
    this.reserved = reserved;
  }

  
  /**
   * Gets seat number for this seat
   * 
   * @return The seat number for this seat
   */
  public String getSeatNr() {
    return seatNr;
  }


  /**
   * Gets the flight number for the flight to which this seat belongs to
   * 
   * @return The flight number for the flight containing this seat
   */
  public String getFlightNr() {
    return flightNr;
  }


  /**
   * Updates the reservation status of this seat
   * 
   * @param reserved Boolean that specifies the new reservation status for this seat
   */
  public void updateReservation(boolean reserved) {
    this.reserved = reserved;
  }

  
  /**
   * Gets the reservation status for this seat
   * 
   * @return The reservation status for this seat
   */
  public boolean getReservationStatus() {
    return reserved;
  }

}
