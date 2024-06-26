package flight.vidmot;

import flight.classes.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import flight.interfaces.FlightControllerInterface;
import flight.interfaces.FlightServiceLayerInterface;
import flight.serviceLayers.FlightServiceLayer;


/**
 * The FlightServiceLayer provides methods for searching and retrieving 
 * flight information from the database.
 */
public class FlightController implements FlightControllerInterface {

  private final FlightServiceLayerInterface FSL;

  /**
   * Constructor for the flight controller
   *
   * @param BSL The {@link FlightServiceLayer} that the flight controller
   * will use to manage flights in the system.
   */
  public FlightController(FlightServiceLayerInterface FSL) {
    this.FSL = FSL;
    
  }


  /**
   * Searches for a flight in the database based on flight number.
   *
   * @param flightNr The flight number of the queried flight.
   * @return The {@link Flight} with the specified flight number.
   */
  public Flight searchFlightByFlightNr(String flightNr) {
    Flight flight = null;

    try {
      flight = FSL.searchFlightByFlightNr(flightNr);
    } catch(Exception e) {
      System.err.println("Error searching for flight using flight number: " + e);
    }

    return flight;
  }


  /**
   * Searches for flights in the database based on a specified price range.
   *
   * @param priceLower The lower bound of the price range being queried.
   * @param priceUpper The upper bound of the price range being queried.
   * @return List of {@link Flight} within the specified price range.
   */
  public List<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper) {

    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByPriceRange(priceLower, priceUpper);
    } catch(Exception e) {
      System.err.println("Error searching for flight using price range: " + e);
    }

    return found;
  }


  /**
   * Searches for flights in the database based on a specified departure date.
   *
   * @param date The departure date which is being queried.
   * @return List of {@link Flight} departing on the specified date.
   */
  public List<Flight> searchFlightsByDepartureDate(LocalDate date) {

    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByDepartureDate(date);
    } catch(Exception e) {
      System.err.println("Error searching for flight using departure date: " + e);
    }

    return found;
  }


  /**
   * Searches for flights in the database based on a specified arrival date.
   *
   * @param date The arrival date which is being queried.
   * @return List of {@link Flight} arriving on the specified date.
   */
  public List<Flight> searchFlightsByArrivalDate(LocalDate date) {

    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByArrivalDate(date);
    } catch(Exception e) {
      System.err.println("Error searching for flight using arrival date: " + e);
    }

    return found;
  }


  /**
   * Searches for flights in the database based on a specified leg.
   *
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @return List of {@link Flight} flying the specified leg.
   */
  public List<Flight> searchFlightsByDepArr(String depAddress, String arrAddress) {

    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByDepArr(depAddress, arrAddress);
    } catch(Exception e) {
      System.err.println("Error searching for flights: " + e);
    }

    return found;
  }


  /**
   * Searches for flights in the database based on a specified leg and departure date.
   *
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @param depDate The departure date of the queried leg.
   * @return List of {@link Flight} flying the specified leg on the specified date.
   */
  public List<Flight> searchFlightsByDepArr(String depAddress, String arrAddress, LocalDate depDate) {

    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByDepArr(depAddress, arrAddress, depDate);
    } catch(Exception e) {
      System.err.println("Error searching for flights: " + e);
    }

    return found;
  }


  /**
   * Searches for flights in the database based on a specified leg and departure date.
   *
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @param depDate The departure date of the queried leg.
   * @param priceLower The lower bound of the price range being queried.
   * @param priceUpper The upper bound of the price range being queried.
   * @return List of {@link Flight} flying the specified leg on the specified 
   * date withing the specified price range.
   */
  public List<Flight> searchFlightsByDepArrPriceRange(String depAddress, String arrAddress, LocalDate depDate, int priceLower, int priceUpper) {

    List<Flight> found = new ArrayList<>();;

    try {
      found = FSL.searchFlightsByDepArrPriceRange(depAddress, arrAddress, depDate, priceLower, priceUpper);
    } catch(Exception e) {
      System.err.println("Error searching for flights: " + e);
    }

    return found;
  }


  /**
   * Gets all flights from the database sorted by price.
   *
   * @return List of {@link Flight} sorted by price.
   */
  public List<Flight> getSortedByPrice() {

    List<Flight> priceSorted = new ArrayList<>();;

    try {
      priceSorted = FSL.getSortedByPrice();
    } catch(Exception e) {
      System.err.println("Error getting sorted by price: " + e);
    }

    return priceSorted;
  }


  /**
   * Gets all flights from the database sorted by departure date.
   *
   * @return List of {@link Flight} sorted by departure date.
   */
  public List<Flight> getSortedByDepartureDate() {

    List<Flight> departureDateSorted = new ArrayList<>();;

    try {
      departureDateSorted = FSL.getSortedByDepartureDate();
    } catch(Exception e) {
      System.err.println("Error getting sorted by departure date: " + e);
    }

    return departureDateSorted;
  }


  /**
   * Gets all flights from the database sorted by arrival date.
   *
   * @return List of {@link Flight} sorted by arrival date.
   */
  public List<Flight> getSortedByArrivalDate() {

    List<Flight> arrivalDateSorted = new ArrayList<>();;

    try {
      arrivalDateSorted = FSL.getSortedByArrivalDate();
    } catch(Exception e) {
      System.err.println("Error getting sorted by arrival date: " + e);
    }

    return arrivalDateSorted;
  }


  /**
   * Gets all flights from the database sorted by departure address.
   *
   * @return List of {@link Flight} sorted by departure address.
   */
  public List<Flight> getSortedByDepartureAddress() {

    List<Flight> departureAddressSorted = new ArrayList<>();;

    try {
      departureAddressSorted = FSL.getSortedByDepartureAddress();
    } catch(Exception e) {
      System.err.println("Error getting sorted by departure: " + e);
    }

    return departureAddressSorted;
  }


  /**
   * Gets all flights from the database sorted by arrival address.
   *
   * @return List of {@link Flight} sorted by arrival address.
   */
  public List<Flight> getSortedByArrivalAddress() {

    List<Flight> arrivalAddressSorted = new ArrayList<>();;

    try {
      arrivalAddressSorted = FSL.getSortedByArrivalAddress();
    } catch(Exception e) {
      System.err.println("Error getting sorted by arrival: " + e);
    }

    return arrivalAddressSorted;
  }
}
