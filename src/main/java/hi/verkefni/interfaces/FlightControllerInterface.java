package hi.verkefni.interfaces;

import java.time.LocalDate;
import java.util.List;

import hi.verkefni.classes.Flight;

public interface FlightControllerInterface {


  public Flight searchFlightByFlightNr(String flightNr);


  public List<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper);


  public List<Flight> searchFlightsByDepartureDate(LocalDate date);


  public List<Flight> searchFlightsByArrivalDate(LocalDate date);


  public List<Flight> searchFlightsByDepArr(String depAddress, String arrAddress);


  public List<Flight> searchFlightsByDepArr(String depAddress, String arrAddress, LocalDate depTime);


  public List<Flight> getSortedByPrice();


  public List<Flight> getSortedByDepartureTime();


  public List<Flight> getSortedByArrivalTime();


  public List<Flight> getSortedByDepartureAddress();


  public List<Flight> getSortedByArrivalAddress();
  
}
