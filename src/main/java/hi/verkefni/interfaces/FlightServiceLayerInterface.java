package hi.verkefni.interfaces;

import java.sql.SQLException;
import java.time.LocalDate;
import java.util.ArrayList;

import hi.verkefni.classes.Flight;


/**
 * The FlightServiceLayerInterface provides methods for searching and retrieving 
 * flight information from the database.
 * Implementing classes should provide functionality to search for flights based 
 * on various criteria and retrieve flights sorted by different attributes.
 */
public interface FlightServiceLayerInterface {

  /**
   * Searches for a flight in the database based on flight number.
   * 
   * @param flightNr The flight number of the queried flight.
   * @return The {@link Flight} with the specified flight number.
   */
  public Flight searchFlightByFlightNr(String flightNr) throws SQLException;


  /**
   * Searches for flights in the database based on a specified price range.
   * 
   * @param priceLower The lower bound of the price range being queried.
   * @param priceUpper The upper bound of the price range being queried.
   * @return List of {@link Flight} within the specified price range.
   */
  public ArrayList<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper) throws SQLException;


  /**
   * Searches for flights in the database based on a specified departure date.
   * 
   * @param date The departure date which is being queried.
   * @return List of {@link Flight} departing on the specified date.
   */
  public ArrayList<Flight> searchFlightsByDepartureDate(LocalDate date) throws SQLException;

  
  /**
   * Searches for flights in the database based on a specified arrival date.
   * 
   * @param date The arrival date which is being queried.
   * @return List of {@link Flight} arriving on the specified date.
   */
  public ArrayList<Flight> searchFlightsByArrivalDate(LocalDate date) throws SQLException;


  /**
   * Searches for flights in the database based on a specified leg.
   * 
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @return List of {@link Flight} flying the specified leg.
   */
  public ArrayList<Flight> searchFlightsByDepArr(String depAddress, String arrAddrss) throws SQLException;


  /**
   * Searches for flights in the database based on a specified leg and departure date.
   * 
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @param depDate The departure date of the queried leg.
   * @return List of {@link Flight} flying the specified leg on the specified date.
   */
  public ArrayList<Flight> searchFlightsByDepArr(String depAddress, String arrAddrss, LocalDate depDate) throws SQLException;


  /**
   * Gets all flights from the database sorted by price.
   * 
   * @return List of {@link Flight} sorted by price.
   */
  public ArrayList<Flight> getSortedByPrice() throws SQLException;


  /**
   * Gets all flights from the database sorted by departure time.
   * 
   * @return List of {@link Flight} sorted by departure time.
   */
  public ArrayList<Flight> getSortedByDepartureTime() throws SQLException;


  /**
   * Gets all flights from the database sorted by arrival time.
   * 
   * @return List of {@link Flight} sorted by arrival time.
   */
  public ArrayList<Flight> getSortedByArrivalTime() throws SQLException;


  /**
   * Gets all flights from the database sorted by departure address.
   * 
   * @return List of {@link Flight} sorted by departure address.
   */
  public ArrayList<Flight> getSortedByDepartureAddress() throws SQLException;


  /**
   * Gets all flights from the database sorted by arrival address.
   * 
   * @return List of {@link Flight} sorted by arrival address.
   */
  public ArrayList<Flight> getSortedByArrivalAddress() throws SQLException;
  
}
