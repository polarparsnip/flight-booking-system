package hi.verkefni.vidmot;

import hi.verkefni.classes.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import hi.verkefni.interfaces.FlightControllerInterface;
import hi.verkefni.interfaces.FlightServiceLayerInterface;


public class FlightController implements FlightControllerInterface {

  private final FlightServiceLayerInterface FSL;


  public FlightController(FlightServiceLayerInterface FSL) {
    this.FSL = FSL;
  }


  public Flight searchFlightByFlightNr(String flightNr) {
    Flight flight = null;

    try {
      flight = FSL.searchFlightByFlightNr(flightNr);
    } catch(Exception e) {
      System.out.println("Error searching for flight using flight number: " + e);
    }
    
    return flight;
  }


  public List<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper) {
    
    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByPriceRange(priceLower, priceUpper);
    } catch(Exception e) {
      System.out.println("Error searching for flight using price range: " + e);
    }
    
    return found;
  }


  public List<Flight> searchFlightsByDepartureDate(LocalDate date) {
    
    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByDepartureDate(date);
    } catch(Exception e) {
      System.out.println("Error searching for flight using departure date: " + e);
    }
    
    return found;
  }


  public List<Flight> searchFlightsByArrivalDate(LocalDate date) {
    
    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByArrivalDate(date);
    } catch(Exception e) {
      System.out.println("Error searching for flight using arrival date: " + e);
    }
    
    return found;
  }


  public List<Flight> searchFlightsByDepArr(String depAddress, String arrAddress) {
    
    List<Flight> found = new ArrayList<>();;
    
    try {
      found = FSL.searchFlightsByDepArr(depAddress, arrAddress);
    } catch(Exception e) {
      System.out.println("Error searching for flights: " + e);
    }

    return found;
  }


  public List<Flight> searchFlightsByDepArr(String depAddress, String arrAddress, LocalDate depTime) {
    
    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByDepArr(depAddress, arrAddress, depTime);
    } catch(Exception e) {
      System.out.println("Error searching for flights: " + e);
    }

    return found;
  }


  public List<Flight> getSortedByPrice() {
    
    List<Flight> priceSorted = new ArrayList<>();;

    try {
      priceSorted = FSL.getSortedByPrice();
    } catch(Exception e) {
      System.out.println("Error getting sorted by price: " + e);
    }

    return priceSorted;
  }


  public List<Flight> getSortedByDepartureTime() {

    List<Flight> departureTimeSorted = new ArrayList<>();;

    try {
      departureTimeSorted = FSL.getSortedByDepartureTime();
    } catch(Exception e) {
      System.out.println("Error getting sorted by departure time: " + e);
    }

    return departureTimeSorted;
  }


  public List<Flight> getSortedByArrivalTime() {

    List<Flight> arrivalTimeSorted = new ArrayList<>();;

    try {
      arrivalTimeSorted = FSL.getSortedByArrivalTime();
    } catch(Exception e) {
      System.out.println("Error getting sorted by arrival time: " + e);
    }

    return arrivalTimeSorted;
  }


  public List<Flight> getSortedByDepartureAddress() {

    List<Flight> departureAddressSorted = new ArrayList<>();;

    try {
      departureAddressSorted = FSL.getSortedByDepartureAddress();
    } catch(Exception e) {
      System.out.println("Error getting sorted by departure: " + e);
    }

    return departureAddressSorted;
  }


  public List<Flight> getSortedByArrivalAddress() {

    List<Flight> arrivalAddressSorted = new ArrayList<>();;

    try {
      arrivalAddressSorted = FSL.getSortedByArrivalAddress();
    } catch(Exception e) {
      System.out.println("Error getting sorted by arrival: " + e);
    }

    return arrivalAddressSorted;
  }
}
