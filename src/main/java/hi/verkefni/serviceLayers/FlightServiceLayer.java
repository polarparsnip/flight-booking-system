package hi.verkefni.serviceLayers;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import hi.verkefni.classes.Flight;
import hi.verkefni.interfaces.FlightServiceLayerInterface;


/**
 * The FlightServiceLayer provides methods for searching and retrieving flight information from the database.
 */
public class FlightServiceLayer implements FlightServiceLayerInterface {

  private final String databasePath = "sql/flightBookingSystem.db"; 


  /**
   * Constructor for the booking service layer.
   */
  public FlightServiceLayer() {}

  /**
   * Searches for a flight in the database based on flight number.
   * 
   * @param flightNr The flight number of the queried flight.
   * @return The {@link Flight} with the specified flight number.
   */
  public Flight searchFlightByFlightNr(String flightNr) {
    return null;
  };


  /**
   * Searches for flights in the database based on a specified price range.
   * 
   * @param priceLower The lower bound of the price range being queried.
   * @param priceUpper The upper bound of the price range being queried.
   * @return List of {@link Flight} within the specified price range.
   */
  public ArrayList<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper) {
    return null;
  };


  /**
   * Searches for flights in the database based on a specified departure date.
   * 
   * @param date The departure date which is being queried.
   * @return List of {@link Flight} departing on the specified date.
   */
  public ArrayList<Flight> searchFlightsByDepartureDate(LocalDate date) {
    return null;
  };

  
  /**
   * Searches for flights in the database based on a specified arrival date.
   * 
   * @param date The arrival date which is being queried.
   * @return List of {@link Flight} arriving on the specified date.
   */
  public ArrayList<Flight> searchFlightsByArrivalDate(LocalDate date) {
    return null;
  };


  /**
   * Searches for flights in the database based on a specified leg.
   * 
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @return List of {@link Flight} flying the specified leg.
   */
  public ArrayList<Flight> searchFlightsByDepArr(String depAddress, String arrAddrss) {
    return null;
  };


  /**
   * Searches for flights in the database based on a specified leg and departure date.
   * 
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @param depDate The departure date of the queried leg.
   * @return List of {@link Flight} flying the specified leg on the specified date.
   */
  public ArrayList<Flight> searchFlightsByDepArr(String depAddress, String arrAddrss, LocalDate depDate) {
    return null;
  };


  /**
   * Gets all flights from the database sorted by price.
   * 
   * @return List of {@link Flight} sorted by price.
   */
  public ArrayList<Flight> getSortedByPrice() {
    return null;
  };


  /**
   * Gets all flights from the database sorted by departure time.
   * 
   * @return List of {@link Flight} sorted by departure time.
   */
  public ArrayList<Flight> getSortedByDepartureTime() {
    return null;
  };


  /**
   * Gets all flights from the database sorted by arrival time.
   * 
   * @return List of {@link Flight} sorted by arrival time.
   */
  public ArrayList<Flight> getSortedByArrivalTime() {
    return null;
  };


  /**
   * Gets all flights from the database sorted by departure address.
   * 
   * @return List of {@link Flight} sorted by departure address.
   */
  public ArrayList<Flight> getSortedByDepartureAddress() {
    return null;
  };


  /**
   * Gets all flights from the database sorted by arrival address.
   * 
   * @return List of {@link Flight} sorted by arrival address.
   */
  public ArrayList<Flight> getSortedByArrivalAddress() {
    return null;
  };
  

}
