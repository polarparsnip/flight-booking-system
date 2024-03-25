package hi.verkefni.classes;

public class Seat {
  
  private final String seatNr;
  private String flightNr;
  private boolean reserved;

  
  public Seat(String seatNr, String flightNr, Boolean reserved) {
    this.seatNr = seatNr;
    this.flightNr = flightNr;
    this.reserved = reserved;
  }


  public String getSeatNr() {
    return seatNr;
  }


  public String getFlightNr() {
    return flightNr;
  }


  public void updateReservation(boolean reserved) {
    this.reserved = reserved;
  }

  
  public boolean getReservationStatus() {
    return reserved;
  }

}
