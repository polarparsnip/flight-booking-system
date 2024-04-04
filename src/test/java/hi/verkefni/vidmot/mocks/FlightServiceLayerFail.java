package hi.verkefni.vidmot.mocks;

import java.time.LocalDate;
import java.util.ArrayList;
import hi.verkefni.classes.Flight;
import hi.verkefni.interfaces.FlightServiceLayerInterface;

public class FlightServiceLayerFail implements FlightServiceLayerInterface {

  /**
   * Mock flight service layer that imitates having no database connection
   * All methods throw a connection issue error
   */
  public FlightServiceLayerFail() {}


  public Flight searchFlightByFlightNr(String flightNr) {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper) {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> searchFlightsByDepartureDate(LocalDate date) {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> searchFlightsByArrivalDate(LocalDate date) {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> searchFlightsByDepArr(String departureAddress, String arrivalAddress) {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> searchFlightsByDepArr(String departureAddress, String arrivalAddress, LocalDate departureTime) {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> getSortedByPrice() {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> getSortedByDepartureTime() {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> getSortedByArrivalTime() {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> getSortedByDepartureAddress() {
    throw new RuntimeException("No database connection");
  }


  public ArrayList<Flight> getSortedByArrivalAddress() {
    throw new RuntimeException("No database connection");
  }

}
