package hi.verkefni.classes;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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


  public String getFlightNr() {
    return flightNr;
  }


  public void setFlightNr(String flightNr) {
    this.flightNr = flightNr;
  }


  public int getSeatsAvailable() {
    return seatsAvailable;
  }


  public void setSeatsAvailable(int numSeatsAvailable) {
    this.seatsAvailable = numSeatsAvailable;
  }


  public int getSeatsReserved() {
    return seatsReserved;
  }


  public void setSeatsReserved(int numSeatsReserved) {
    this.seatsReserved = numSeatsReserved;
  }


  public List<Seat> getSeats() {
    return seats;
  }


  public Seat getSeatBySeatNr(String seatNr) {

    for (Seat s : seats) {
      if (s.getSeatNr().equals(seatNr)) {
        return s;
      }
    }

    return null;
  }


  public void reserveSeat(String seatNr) {
    for (int i = 0;i < seats.size();i++) {
      if (seats.get(i).getSeatNr().equals(seatNr) && !seats.get(i).getReservationStatus()) {
        seats.get(i).updateReservation(true);
        break;
      }
    }
  }


  public LocalDate getDepartureTime() {
    return departureTime;
  }


  public void setDepartureTime(LocalDate departureTime) {
    this.departureTime = departureTime;
  }


  public LocalDate getArrivalTime() {
    return arrivalTime;
  }


  public void setArrivalTime(LocalDate arrivalTime) {
    this.arrivalTime = arrivalTime;
  }


  public String getDepartureAddress() {
    return departureAddress;
  }


  public void setDepartureAddress(String departureAddress) {
    this.departureAddress = departureAddress;
  }


  public String getArrivalAddress() {
    return arrivalAddress;
  }


  public void setArrivalAddress(String arrivalAddress) {
    this.arrivalAddress = arrivalAddress;
  }


  public int getPrice() {
    return price;
  }


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

}
