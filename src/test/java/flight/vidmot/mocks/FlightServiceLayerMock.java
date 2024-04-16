package flight.vidmot.mocks;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Comparator;
import java.util.ArrayList;

import flight.classes.Flight;
import flight.classes.Seat;
import flight.interfaces.FlightServiceLayerInterface;

public class FlightServiceLayerMock implements FlightServiceLayerInterface {

  private final ArrayList<Flight> flights;
  
  private final String[] seatNumbers = {"1A", "1B", "1C", "1D", "2A", "2B", "2C", "2D"};
  private final String[] departureAddresses = {"Reykjavík", "Reykjavík", "Reykjavík", "Akureyri", "Vestmannaeyjar", "Ísafjörður"};
  private final String[] arrivalAddresses = {"Akureyri", "Vestmannaeyjar", "Ísafjörður", "Reykjavík", "Reykjavík", "Reykjavík"};
  private int day = 4;

  /**
   * Constructor for the mock flight service layer.
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

      LocalTime depTime = LocalTime.of(15, 30);
      LocalTime arrTime = LocalTime.of(16, 30);

      flights.add(
        new Flight(
          flightNr, 
          seats, 
          departureAddresses[i], 
          arrivalAddresses[i], 
          date, 
          depTime,
          date, 
          arrTime,
          (20000 + i * 2000)
        )
      );
    }

  }


  /**
   * Mock method that searches flights based on their flight number.
   * 
   * @param flightNr flight number of the queried flight.
   * 
   * @return the flight with the specified flight number.
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
   * Mock method that searches flights based on a specified price range.
   * 
   * @param priceLower lower bounds for price range.
   * @param priceUpper upper bounds for price range.
   * 
   * @return list of flights within the specified price range.
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
        return flight1.getDepartureDate().compareTo(flight2.getDepartureDate());
      }
    });

    return priceFilteredFlights;
  }


  /**
   * Mock method that searches flights based on departure date.
   * 
   * @param date departure date of flight.
   * 
   * @return list of flights departing  on the specified date.
   */
  public ArrayList<Flight> searchFlightsByDepartureDate(LocalDate date) {

    ArrayList<Flight> departureDateFilteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      if (flight.getDepartureDate().equals(date)) {
        departureDateFilteredFlights.add(flight);
      }
    }

    return departureDateFilteredFlights;
  }


  /**
   * Mock method that searches flights based on arrival date.
   * 
   * @param date arrival date of flight.
   * 
   * @return list of flights arriving on the specified date.
   */
  public ArrayList<Flight> searchFlightsByArrivalDate(LocalDate date) {

    ArrayList<Flight> arrivalDateFilteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      if (flight.getArrivalDate().equals(date)) {
        arrivalDateFilteredFlights.add(flight);
      }
    }

    return arrivalDateFilteredFlights;
  }


  /**
   * Mock method that searches flights based on departure address, arrival address.
   * 
   * @param departureAddress departure address of flight.
   * @param arrivalAddress arrival address of flight.
   * 
   * @return list of flights fitting the search criteria.
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
   * Mock method that searches flights based on departure address, arrival address, and departure date.
   * 
   * @param departureAddress departure address of flight.
   * @param arrivalAddress arrival address of flight.
   * @param departureDate departure date of flight.
   * 
   * @return list of flights fitting the search criteria.
   */
  public ArrayList<Flight> searchFlightsByDepArr(String departureAddress, String arrivalAddress, LocalDate departureDate) {

    ArrayList<Flight> filteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      boolean departureAddressMatch = flight.getDepartureAddress().equals(departureAddress);
      boolean arrivalAddressMatch = flight.getArrivalAddress().equals(arrivalAddress);
      boolean departureDateMatch = flight.getDepartureDate().equals(departureDate);

      if (departureAddressMatch && arrivalAddressMatch && departureDateMatch) {
        filteredFlights.add(flight);
      }
    }

    return filteredFlights;
  }


  /**
   * Mock method that searches for flights in the database based on a specified leg and departure date.
   * 
   * @param depAddress The departure address of the leg.
   * @param arrAddress The arrival address of the leg.
   * @param depDate The departure date of the queried leg.
   * @param priceLower The lower bound of the price range being queried.
   * @param priceUpper The upper bound of the price range being queried.
   * 
   * @return List of {@link Flight} flying the specified leg on the specified date 
   * within the specified price range.
   */
  public ArrayList<Flight> searchFlightsByDepArrPriceRange(
    String departureAddress, 
    String arrivalAddress, 
    LocalDate departureDate, 
    int priceLower, 
    int priceUpper
  ) {
    ArrayList<Flight> filteredFlights = new ArrayList<>();

    for (Flight flight : flights) {
      boolean departureAddressMatch = flight.getDepartureAddress().equals(departureAddress);
      boolean arrivalAddressMatch = flight.getArrivalAddress().equals(arrivalAddress);
      boolean departureDateMatch = flight.getDepartureDate().equals(departureDate);
      boolean abovePriceLower = priceLower < flight.getPrice();
      boolean belowPriceUpper = flight.getPrice() < priceUpper;

      if (departureAddressMatch && arrivalAddressMatch && departureDateMatch && abovePriceLower && belowPriceUpper) {
        filteredFlights.add(flight);
      }
    }

    return filteredFlights;
  }


  /**
   * Mock method that gets flights sorted by price in ascending order.
   * 
   * @return list of flights sorted by price.
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
   * Mock method that gets flights sorted by departure date in ascending order.
   * 
   * @return list of flights sorted by departure date.
   */
  public ArrayList<Flight> getSortedByDepartureDate() {

    ArrayList<Flight> departureDateSorted = flights;

    departureDateSorted.sort(new Comparator<Flight>() {
      public int compare(Flight flight1, Flight flight2) {
        return flight1.getDepartureDate().compareTo(flight2.getDepartureDate());
      }
    });

    return departureDateSorted;
  }


  /**
   * Mock method that gets flights sorted by arrival date in ascending order.
   * 
   * @return list of flights sorted by arrival date.
   */
  public ArrayList<Flight> getSortedByArrivalDate() {

    ArrayList<Flight> arrivalDateSorted = flights;

    arrivalDateSorted.sort(new Comparator<Flight>() {
      public int compare(Flight flight1, Flight flight2) {
        return flight1.getArrivalDate().compareTo(flight2.getArrivalDate());
      }
    });

    return arrivalDateSorted;
  }


  /**
   * Mock method that gets flights sorted by departure address in alphabetical order.
   * 
   * @return list of flights sorted by departure address.
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
   * Mock method that gets flights sorted by arrival address in alphabetical order.
   * 
   * @return list of flights sorted by arrival address.
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
