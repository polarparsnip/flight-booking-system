package hi.verkefni.interfaces;

import java.time.LocalDate;
import java.util.ArrayList;

import hi.verkefni.classes.Flight;

public interface FlightServiceLayerInterface {


  public Flight searchFlightByFlightNr(String flightNr);


  public ArrayList<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper);


  public ArrayList<Flight> searchFlightsByDepartureDate(LocalDate date);

  
  public ArrayList<Flight> searchFlightsByArrivalDate(LocalDate date);


  public ArrayList<Flight> searchFlightsByDepArr(String depAddress, String arrAddrss);


  public ArrayList<Flight> searchFlightsByDepArr(String depAddress, String arrAddrss, LocalDate depTime);


  public ArrayList<Flight> getSortedByPrice();


  public ArrayList<Flight> getSortedByDepartureTime();


  public ArrayList<Flight> getSortedByArrivalTime();


  public ArrayList<Flight> getSortedByDepartureAddress();


  public ArrayList<Flight> getSortedByArrivalAddress();
  
}
