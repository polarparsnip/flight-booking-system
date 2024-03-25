package hi.verkefni.vidmot.mocks;

import java.time.LocalDate;
import java.util.Comparator;
import java.util.ArrayList;

import hi.verkefni.classes.Flight;
import hi.verkefni.classes.Seat;
import hi.verkefni.interfaces.FlightServiceLayerInterface;

public class FlightServiceLayerMock implements FlightServiceLayerInterface {

  private final ArrayList<Flight> flights;
  
  private final String[] seatNumbers = {"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2"};
  private final String[] departureAddresses = {"Reykjavík", "Reykjavík", "Reykjavík", "Akureyri", "Vestmannaeyjar", "Ísafjörður"};
  private final String[] arrivalAddresses = {"Akureyri", "Vestmannaeyjar", "Ísafjörður", "Reykjavík", "Reykjavík", "Reykjavík"};
  private int day = 4;

  /**
   * Constructor for the mock flight service layer
   */
  public FlightServiceLayerMock() {

    flights = new ArrayList<>();

    for (int i = 0; i < departureAddresses.length; i++) {
      String flightNr = "F-" + String.format("%03d", i);
      ArrayList<Seat> seats = new ArrayList<>();

      for (String seatNum : seatNumbers) {
        seats.add(new Seat(seatNum, flightNr, false));
      }

      LocalDate date = LocalDate.of(2024, 4, day + i);
      flights.add(new Flight(flightNr, seats, departureAddresses[i], arrivalAddresses[i], date, date, (20000 + 1* 2000)));
    }

  }


  /**
   * Mock method that searches flights based on their flight number
   * @param flightNr flight number of the queried flight
   * @return the flight with the specified flight number
   */
  public Flight searchFlightByFlightNr(String flightNr) {

    for (Flight flight : flights) {
      if (flight.getFlightNr().equals(flightNr)) {
        return flight;
      }
    }

    return null;
  }


  /**
   * Mock method that searches flights based on a specified price range
   * @param priceLower lower bounds for price range
   * @param priceUpper upper bounds for price range
   * @return list of flights within the specified price range
   */
  public ArrayList<Flight> searchFlightsByPriceRange(int priceLower, int priceUpper) {

    ArrayList<Flight> priceFilteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      if (priceLower < flight.getPrice() && flight.getPrice() < priceUpper) {
        priceFilteredFlights.add(flight);
      }
    }

    priceFilteredFlights.sort(new Comparator<Flight>() {
      public int compare(Flight flight1, Flight flight2) {
        return flight1.getDepartureTime().compareTo(flight2.getDepartureTime());
      }
    });

    return priceFilteredFlights;
  }


    /**
   * Mock method that searches flights based on departure date
   * @param date departure date of flight
   * @return list of flights departing  on the specified date
   */
  public ArrayList<Flight> searchFlightsByDepartureDate(LocalDate date) {

    ArrayList<Flight> departureDateFilteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      if (flight.getDepartureTime().equals(date)) {
        departureDateFilteredFlights.add(flight);
      }
    }

    return departureDateFilteredFlights;
  }


  /**
   * Mock method that searches flights based on arrival date
   * @param date arrival date of flight
   * @return list of flights arriving on the specified date
   */
  public ArrayList<Flight> searchFlightsByArrivalDate(LocalDate date) {

    ArrayList<Flight> arrivalDateFilteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      if (flight.getArrivalTime().equals(date)) {
        arrivalDateFilteredFlights.add(flight);
      }
    }

    return arrivalDateFilteredFlights;
  }


  /**
   * Mock method that searches flights based on departure address, arrival address
   * @param departureAddress departure address of flight
   * @param arrivalAddress arrival address of flight
   * @return list of flights fitting the search criteria
   */
  public ArrayList<Flight> searchFlightsByDepArr(String departureAddress, String arrivalAddress) {

    ArrayList<Flight> filteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      boolean departureAddressMatch = flight.getDepartureAddress().equals(departureAddress);
      boolean arrivalAddressMatch = flight.getArrivalAddress().equals(arrivalAddress);

      if (departureAddressMatch && arrivalAddressMatch) {
        filteredFlights.add(flight);
      }
    }

    return filteredFlights;
  }


  /**
   * Mock method that searches flights based on departure address, arrival address, and departure time
   * @param departureAddress departure address of flight
   * @param arrivalAddress arrival address of flight
   * @param departureTime departure time of flight
   * @return list of flights fitting the search criteria
   */
  public ArrayList<Flight> searchFlightsByDepArr(String departureAddress, String arrivalAddress, LocalDate departureTime) {

    ArrayList<Flight> filteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      boolean departureAddressMatch = flight.getDepartureAddress().equals(departureAddress);
      boolean arrivalAddressMatch = flight.getArrivalAddress().equals(arrivalAddress);
      boolean departureTimeMatch = flight.getDepartureTime().equals(departureTime);

      if (departureAddressMatch && arrivalAddressMatch && departureTimeMatch) {
        filteredFlights.add(flight);
      }
    }

    return filteredFlights;
  }


  /**
   * Mock method that gets flights sorted by price in ascending order
   * @return list of flights sorted by price
   */
  public ArrayList<Flight> getSortedByPrice() {

    ArrayList<Flight> priceSorted = flights;

    priceSorted.sort(new Comparator<Flight>() {
      public int compare(Flight flight1, Flight flight2) {
        return Integer.compare(flight1.getPrice(), flight2.getPrice());
      }
    });
    
    return priceSorted;
  }


  /**
   * Mock method that gets flights sorted by departure time in ascending order
   * @return list of flights sorted by departure time
   */
  public ArrayList<Flight> getSortedByDepartureTime() {

    ArrayList<Flight> departureTimeSorted = flights;

    departureTimeSorted.sort(new Comparator<Flight>() {
      public int compare(Flight flight1, Flight flight2) {
        return flight1.getDepartureTime().compareTo(flight2.getDepartureTime());
      }
    });

    return departureTimeSorted;
  }


  /**
   * Mock method that gets flights sorted by arrival time in ascending order
   * @return list of flights sorted by arrival time
   */
  public ArrayList<Flight> getSortedByArrivalTime() {

    ArrayList<Flight> arrivalTimeSorted = flights;

    arrivalTimeSorted.sort(new Comparator<Flight>() {
      public int compare(Flight flight1, Flight flight2) {
        return flight1.getArrivalTime().compareTo(flight2.getArrivalTime());
      }
    });

    return arrivalTimeSorted;
  }


  /**
   * Mock method that gets flights sorted by departure address in alphabetical order
   * @return list of flights sorted by departure address
   */
  public ArrayList<Flight> getSortedByDepartureAddress() {

    ArrayList<Flight> departureAddressSorted = flights;

    departureAddressSorted.sort(new Comparator<Flight>() {
      public int compare(Flight flight1, Flight flight2) {
        return flight1.getDepartureAddress().compareTo(flight2.getDepartureAddress());
      }
    });

    return departureAddressSorted;
  }


  /**
   * Mock method that gets flights sorted by arrival address in alphabetical order
   * @return list of flights sorted by arrival address
   */
  public ArrayList<Flight> getSortedByArrivalAddress() {

    ArrayList<Flight> arrivalAddressSorted = flights;

    arrivalAddressSorted.sort(new Comparator<Flight>() {
      public int compare(Flight flight1, Flight flight2) {
        return flight1.getArrivalAddress().compareTo(flight2.getArrivalAddress());
      }
    });

    return arrivalAddressSorted;
  }

}
