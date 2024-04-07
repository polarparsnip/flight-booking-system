package hi.verkefni.serviceLayers;

import java.time.LocalDate;
import java.util.ArrayList;
import java.sql.ResultSet;
import java.sql.SQLException;

import hi.verkefni.classes.Flight;
import hi.verkefni.classes.Seat;
import hi.verkefni.database.Database;
import hi.verkefni.interfaces.FlightServiceLayerInterface;


/**
 * The FlightServiceLayer provides methods for searching and retrieving flight information from the database.
 */
public class FlightServiceLayer implements FlightServiceLayerInterface {

  private final String databasePath = "sql/flightBookingSystem.db"; 


  /**
   * Constructor for the booking service layer.
   */
  public FlightServiceLayer() {
  }

  public ArrayList<Flight> search(String flightsQuery) {

    Flight returnFlight = null;
    ArrayList<Flight> flightList = new ArrayList<Flight>();
    Database db = new Database(databasePath);
    db.open();

    try {
      ResultSet flightRS = db.query(flightsQuery, null, true);

      while(flightRS.next()) {
        

        String returnFlightNr = flightRS.getString("flightNr");
        String returnDepartureAddress = flightRS.getString("departureAddress");
        String returnArrivalAddress = flightRS.getString("arrivalAddress");
        LocalDate returnDepartureTime = flightRS.getObject("departureTime", LocalDate.class);
        LocalDate returnArrivalTime = flightRS.getObject("arrivalTime", LocalDate.class);
        Integer returnPrice = flightRS.getInt("price");

        String seatsQuery = "SELECT * FROM Seats WHERE flightNr = '" + returnFlightNr + "';";
        ArrayList<Seat> seatList = new ArrayList<Seat>();
        ResultSet seatRS = db.query(seatsQuery, null, true);

        while(seatRS.next()) {
          String returnSeatNumber = seatRS.getString("seatNumber");
          Boolean returnReserved = seatRS.getBoolean("reserved");

          Seat returnSeat = new Seat(returnFlightNr, returnSeatNumber, returnReserved);

          seatList.add(returnSeat);
        }

        returnFlight = new Flight(returnFlightNr, seatList, returnDepartureAddress, returnArrivalAddress, 
        returnDepartureTime, returnArrivalTime, returnPrice);
        flightList.add(returnFlight);

        seatRS.close();
      }

      flightRS.close();

    } catch(SQLException e) {
      System.err.println("Error searching for flights: " + e);
      System.err.println(e.getErrorCode());
    }

    db.close();

    return flightList;

  }

  /**
   * Searches for a flight in the database based on flight number.
   * 
   * @param flightNr The flight number of the queried flight.
   * @return The {@link Flight} with the specified flight number.
   */
  public Flight searchFlightByFlightNr(String flightNr) {

    Flight returnFlight = null;

    String flightsQuery = "SELECT * from Flights WHERE flightNr = '" + flightNr + "';";
    String seatsQuery = "SELECT * FROM Seats WHERE flightNr = '" + flightNr + "';";
    
    Database db = new Database(databasePath);
    db.open();

      

    try {
      ResultSet flightRS = db.query(flightsQuery, null, true);

      String returnDepartureAddress = flightRS.getString("departureAddress");
      String returnArrivalAddress = flightRS.getString("arrivalAddress");
      LocalDate returnDepartureTime = flightRS.getObject("departureTime", LocalDate.class);
      LocalDate returnArrivalTime = flightRS.getObject("arrivalTime", LocalDate.class);
      Integer returnPrice = flightRS.getInt("price");

      ArrayList<Seat> seatList = new ArrayList<Seat>();
      ResultSet seatRS = db.query(seatsQuery, null, true);

      while(seatRS.next()) {
        String returnSeatNumber = seatRS.getString("seatNumber");
        Boolean returnReserved = seatRS.getBoolean("reserved");

        Seat returnSeat = new Seat(flightNr, returnSeatNumber, returnReserved);

        seatList.add(returnSeat);
      }

      returnFlight = new Flight(flightNr, seatList, returnDepartureAddress, returnArrivalAddress, 
        returnDepartureTime, returnArrivalTime, returnPrice);
      
    } catch(Exception e) {
      System.err.println("Error searching for flights: " + e);
    }
    db.close();
    return returnFlight;

  };


  /**
   * Searches for flights in the database based on a specified price range.
   * 
   * @param priceLower The lower bound of the price range being queried.
   * @param priceUpper The upper bound of the price range being queried.
   * @return List of {@link Flight} within the specified price range.
   */
  public ArrayList<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper) {
    String flightsQuery = "SELECT * from Flights WHERE price >= " + String.valueOf(priceLower) + " AND price <= " + 
      String.valueOf(priceUpper) + ";";

    ArrayList<Flight> flightReturn = search(flightsQuery);

    return flightReturn;
  };


  /**
   * Searches for flights in the database based on a specified departure date.
   * 
   * @param date The departure date which is being queried.
   * @return List of {@link Flight} departing on the specified date.
   */
  public ArrayList<Flight> searchFlightsByDepartureDate(LocalDate date) {
    String flightsQuery = "SELECT * from Flights WHERE departureTime = '" + date.toString() + "';";

    ArrayList<Flight> flightReturn = search(flightsQuery);

    return flightReturn;
  };

  
  /**
   * Searches for flights in the database based on a specified arrival date.
   * 
   * @param date The arrival date which is being queried.
   * @return List of {@link Flight} arriving on the specified date.
   */
  public ArrayList<Flight> searchFlightsByArrivalDate(LocalDate date) {
    String flightsQuery = "SELECT * from Flights WHERE arrivalTime = '" + date.toString() + "';";

    ArrayList<Flight> flightReturn = search(flightsQuery);

    return flightReturn;
  };


  /**
   * Searches for flights in the database based on a specified leg.
   * 
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @return List of {@link Flight} flying the specified leg.
   */
  public ArrayList<Flight> searchFlightsByDepArr(String depAddress, String arrAddress) {
    String flightsQuery = "SELECT * from Flights WHERE departureAddress = '" + depAddress + "' AND arrivalAddress = '" + arrAddress + "';";

    ArrayList<Flight> flightReturn = search(flightsQuery);

    return flightReturn;
  };


  /**
   * Searches for flights in the database based on a specified leg and departure date.
   * 
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @param depDate The departure date of the queried leg.
   * @return List of {@link Flight} flying the specified leg on the specified date.
   */
  public ArrayList<Flight> searchFlightsByDepArr(String depAddress, String arrAddress, LocalDate depDate) {
    String flightsQuery = "SELECT * from Flights WHERE departureAddress = '" + depAddress + "' AND arrivalAddress = '" + arrAddress + 
      "' AND departureTime = '" + depDate + "';";

      ArrayList<Flight> flightReturn = search(flightsQuery);

      return flightReturn;
  };


  /**
   * Gets all flights from the database sorted by price.
   * 
   * @return List of {@link Flight} sorted by price.
   */
  public ArrayList<Flight> getSortedByPrice() {
    String flightsQuery = "SELECT * from Flights ORDER BY price;";

    ArrayList<Flight> flightReturn = search(flightsQuery);

    return flightReturn;
  };


  /**
   * Gets all flights from the database sorted by departure time.
   * 
   * @return List of {@link Flight} sorted by departure time.
   */
  public ArrayList<Flight> getSortedByDepartureTime() {
    String flightsQuery = "SELECT * from Flights ORDER BY departureTime;";

    ArrayList<Flight> flightReturn = search(flightsQuery);

    return flightReturn;
  };


  /**
   * Gets all flights from the database sorted by arrival time.
   * 
   * @return List of {@link Flight} sorted by arrival time.
   */
  public ArrayList<Flight> getSortedByArrivalTime() {
    String flightsQuery = "SELECT * from Flights ORDER BY arrivalTime;";

    ArrayList<Flight> flightReturn = search(flightsQuery);

    return flightReturn;
  };


  /**
   * Gets all flights from the database sorted by departure address.
   * 
   * @return List of {@link Flight} sorted by departure address.
   */
  public ArrayList<Flight> getSortedByDepartureAddress() {
    String flightsQuery = "SELECT * from Flights ORDER BY departureAddress;";

    ArrayList<Flight> flightReturn = search(flightsQuery);

    return flightReturn;
  };


  /**
   * Gets all flights from the database sorted by arrival address.
   * 
   * @return List of {@link Flight} sorted by arrival address.
   */
  public ArrayList<Flight> getSortedByArrivalAddress() {
    String flightsQuery = "SELECT * from Flights ORDER BY arrivalAddress;";

    ArrayList<Flight> flightReturn = search(flightsQuery);

    return flightReturn;
  };
  

}
