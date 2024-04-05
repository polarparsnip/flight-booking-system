package hi.verkefni.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


/**
 * The Flight class represents a single flight registered in the system.
 * It stores information such as the flight number, seats on the flight, 
 * departure addresses and dates, arrival addresses and dates, as well as the price for the flight.
 */
public class Flight {

  private String flightNr;
  private final List<Seat> seats;
  private int seatsAvailable;
  private int seatsReserved;
  private int price;
  private LocalDate arrivalTime;
  private String arrivalAddress;
  private LocalDate departureTime;
  private String departureAddress;

  /**
   * Flight constructor
   * 
   * @param flightNr The flight number of this flight
   * @param departureAddress The departure address of this flight
   * @param arrivalAddress The arrival address of this flight
   * @param departureTime The departure time of this flight
   * @param arrivalTime The arrival time of this flight
   * @param price The price of this flight
   */
  public Flight(String flightNr, ArrayList<Seat> seats, String departureAddress, String arrivalAddress,
      LocalDate departureTime, LocalDate arrivalTime, int price) {
    
    this.flightNr = flightNr;
    this.seats = seats;
    seatsReserved = 0;
    
    for (int i = 0;i < seats.size();i++) {
      if (seats.get(i).getReservationStatus() == true) {
        seatsReserved++;
      }
    }

    seatsAvailable = seats.size() - seatsReserved;
    this.price = price;
    this.arrivalTime = arrivalTime;
    this.arrivalAddress = arrivalAddress;
    this.departureTime = departureTime;
    this.departureAddress = departureAddress;
  }


  /**
   * Gets the flight number of this flight
   * 
   * @return Flight number for this flight
   */
  public String getFlightNr() {
    return flightNr;
  }


  /**
   * Gets the total amount of seats available on this flight
   * 
   * @return Amount of seats available on this flight
   */
  public int getSeatsAvailable() {
    return seatsAvailable;
  }


  /**
   * Sets the amount of seats available on this flight
   * 
   * @param numSeatsAvailable The new amount of seats available on this flight
   */
  public void setSeatsAvailable(int numSeatsAvailable) {
    this.seatsAvailable = numSeatsAvailable;
  }


  /**
   * Gets the total amount of seats reserved on this flight
   * 
   * @return Amount of seats reserved on this flight
   */
  public int getSeatsReserved() {
    return seatsReserved;
  }


  /**
   * Sets the amount of seats reserved on this flight
   * 
   * @param numSeatsAvailable The new amount of seats reserved on this flight
   */
  public void setSeatsReserved(int numSeatsReserved) {
    this.seatsReserved = numSeatsReserved;
  }


  /**
   * Gets all the {@link Seat} objects on this flight
   * 
   * @return All {@link Seat} objects on this flight
   */
  public List<Seat> getSeats() {
    return seats;
  }


  /**
   * Gets the {@link Seat} on this flight with the specified seat number
   * 
   * @param seatNr The seat number of the queried seat
   * @return {@link Seat} with the specified seat number
   */
  public Seat getSeatBySeatNr(String seatNr) {

    for (Seat s : seats) {
      if (s.getSeatNr().equals(seatNr)) {
        return s;
      }
    }

    return null;
  }


  /**
   * Reserves the seat on this flight with the specified seat number
   * 
   * @param seatNr The seat number of the seat being reserved on this flight
   */
  public void reserveSeat(String seatNr) {
    for (int i = 0;i < seats.size();i++) {
      if (seats.get(i).getSeatNr().equals(seatNr) && !seats.get(i).getReservationStatus()) {
        seats.get(i).updateReservation(true);
        break;
      }
    }
  }


  /**
   * Unreserves the seat on this flight with the specified seat number
   * 
   * @param seatNr The seat number of the seat being unreserved on this flight
   */
  public void unreserveSeat(String seatNr) {
    for (int i = 0;i < seats.size();i++) {
      if (seats.get(i).getSeatNr().equals(seatNr) && seats.get(i).getReservationStatus()) {
        seats.get(i).updateReservation(false);
        break;
      }
    }
  }


  /**
   * Gets the departure time of this flight
   * 
   * @return The departure time of this flight
   */
  public LocalDate getDepartureTime() {
    return departureTime;
  }


  /**
   * Sets a new departure time for this flight
   * 
   * @param departureTime The new departure time for this flight
   */
  public void setDepartureTime(LocalDate departureTime) {
    this.departureTime = departureTime;
  }


  /**
   * Gets the arrival time of this flight
   * 
   * @return The arrival time of this flight
   */
  public LocalDate getArrivalTime() {
    return arrivalTime;
  }


  /**
   * Sets a new arrival time for this flight
   * 
   * @param arrivalTime The new arrival time for this flight
   */
  public void setArrivalTime(LocalDate arrivalTime) {
    this.arrivalTime = arrivalTime;
  }


  /**
   * Gets the departure address of this flight
   * 
   * @return The departure address of this flight
   */
  public String getDepartureAddress() {
    return departureAddress;
  }


  /**
   * Sets a new departure address for this flight
   * 
   * @param departureAddress The new departure address for this flight
   */
  public void setDepartureAddress(String departureAddress) {
    this.departureAddress = departureAddress;
  }


  /**
   * Gets the arrival address of this flight
   * 
   * @return The arrival address of this flight
   */
  public String getArrivalAddress() {
    return arrivalAddress;
  }


  /**
   * Sets a new arrival address for this flight
   * 
   * @param arrivalAddress The new arrival address for this flight
   */
  public void setArrivalAddress(String arrivalAddress) {
    this.arrivalAddress = arrivalAddress;
  }


  /**
   * Gets the price of this flight
   * 
   * @return Price of this flight
   */
  public int getPrice() {
    return price;
  }


  /**
   * Sets a price for this flight
   * 
   * @param price The new price for this flight
   */
  public void setPrice(int price) {
    this.price = price;
  }


  @Override
  public boolean equals(Object o) {
    
    if (o == this) {
      return true;
    }

    if (!(o instanceof Flight)) {
      return false;
    }

    Flight f = (Flight) o;

    return flightNr.equals(f.flightNr);
  }


  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();

    sb.append("Flugnúmer: ").append(flightNr).append("\n");
    sb.append("Brottfarastaður: ").append(departureAddress).append("\n");
    sb.append("Brottfaratími: ").append(departureTime).append("\n");
    sb.append("Áfangastaður: ").append(arrivalAddress).append("\n");
    sb.append("Komutími: ").append(arrivalTime).append("\n");
    sb.append("Verð: ").append(price).append("\n");
    sb.append("Laus sæti: ").append(seatsAvailable).append("\n");

    return sb.toString();
  }

}
