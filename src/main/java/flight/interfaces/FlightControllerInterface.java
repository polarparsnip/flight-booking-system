package flight.interfaces;

import java.time.LocalDate;
import java.util.List;

import flight.classes.Flight;


/**
 * The FlightControllerInterface provides methods for searching for flights in the system.
 * Implementing classes should provide functionality to retrieving flights in various forms.
 */
public interface FlightControllerInterface {

  /**
   * Searches a flight in the service layer based on flight number.
   * 
   * @param flightNr The flight number of the queried flight.
   * @return The {@link Flight} with the specified flight number.
   */
  public Flight searchFlightByFlightNr(String flightNr);


  /**
   * Searches for flights in the service layer based on a specified price range.
   * 
   * @param priceLower The lower bound of the price range being queried.
   * @param priceUpper The upper bound of the price range being queried.
   * @return List of {@link Flight} within the specified price range.
   */
  public List<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper);


  /**
   * Searches for flights in the service layer based on a specified departure date.
   * 
   * @param date The departure date which is being queried.
   * @return List of {@link Flight} departing on the specified date.
   */
  public List<Flight> searchFlightsByDepartureDate(LocalDate date);


  /**
   * Searches for flights in the service layer based on a specified arrival date.
   * 
   * @param date The arrival date which is being queried.
   * @return List of {@link Flight} arriving on the specified date.
   */
  public List<Flight> searchFlightsByArrivalDate(LocalDate date);


  /**
   * Searches for flights in the service layer based on a specified leg.
   * 
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @return List of {@link Flight} flying the specified leg.
   */
  public List<Flight> searchFlightsByDepArr(String depAddress, String arrAddress);


  /**
   * Searches for flights in the service layer based on a specified leg and departure date.
   * 
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @param depDate The departure date of the queried leg.
   * @return List of {@link Flight} flying the specified leg on the specified date.
   */
  public List<Flight> searchFlightsByDepArr(String depAddress, String arrAddress, LocalDate depDate);


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
  public List<Flight> searchFlightsByDepArrPriceRange(String depAddress, String arrAddress, LocalDate depDate, int priceLower, int priceUpper);


  /**
   * Gets all flights from the service layer sorted by price.
   * 
   * @return List of {@link Flight} sorted by price.
   */
  public List<Flight> getSortedByPrice();


  /**
   * Gets all flights from the service layer sorted by departure date.
   * 
   * @return List of {@link Flight} sorted by departure date.
   */
  public List<Flight> getSortedByDepartureDate();


  /**
   * Gets all flights from the service layer sorted by arrival date.
   * 
   * @return List of {@link Flight} sorted by arrival date.
   */
  public List<Flight> getSortedByArrivalDate();


  /**
   * Gets all flights from the service layer sorted by departure address.
   * 
   * @return List of {@link Flight} sorted by departure address.
   */
  public List<Flight> getSortedByDepartureAddress();


  /**
   * Gets all flights from the service layer sorted by arrival address.
   * 
   * @return List of {@link Flight} sorted by arrival address.
   */
  public List<Flight> getSortedByArrivalAddress();
  
}
