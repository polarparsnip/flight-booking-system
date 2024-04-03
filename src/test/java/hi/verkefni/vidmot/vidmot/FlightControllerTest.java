package hi.verkefni.vidmot.vidmot;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hi.verkefni.classes.Flight;
import hi.verkefni.classes.Seat;
import hi.verkefni.interfaces.FlightServiceLayerInterface;
import hi.verkefni.vidmot.FlightController;
import hi.verkefni.vidmot.mocks.FlightServiceLayerMock;

public class FlightControllerTest {

  private ArrayList<Flight> flights;
  private final String[] seatNumbers = {"A1", "A2", "B1", "B2", "C1", "C2", "D1", "D2"};
  private final String[] departureAddresses = {"Reykjavík", "Reykjavík", "Reykjavík", "Akureyri", "Vestmannaeyjar",
      "Ísafjörður"};
  private final String[] arrivalAddresses = {"Akureyri", "Vestmannaeyjar", "Ísafjörður", "Reykjavík", "Reykjavík",
      "Reykjavík"};
  private FlightController FC;


  @Before
  public void setUp() {

    int day = 4;
    FlightServiceLayerInterface FSL = new FlightServiceLayerMock();
    FC = new FlightController(FSL);
    flights = new ArrayList<>();

    for (int i = 0; i < departureAddresses.length; i++) {
      String flightNr = "F-" + String.format("%03d", i);
      ArrayList<Seat> seats = new ArrayList<>();

      for (String seatNum : seatNumbers) {
        seats.add(new Seat(seatNum, flightNr, false));
      }

      LocalDate date = LocalDate.of(2024, 4, day + i);

      flights.add(new Flight(flightNr, seats, departureAddresses[i], arrivalAddresses[i], date, date, (20000 + i * 2000)));
    }
  }


  @Test
  public void SearchByFlightNr() {
    Flight oracle = flights.get(2);
    assertEquals(oracle, FC.searchFlightByFlightNr("F-002"));
  }


  @Test
  public void SearchByPrice() {
    ArrayList<Flight> oracle = new ArrayList<>();
    oracle.add(flights.get(0));
    oracle.add(flights.get(1));

    ArrayList<Flight> priceFiltered = new ArrayList<>(
      FC.searchFlightsByPriceRange(19000, 23000)
    );

    try {
      for (int i = 0; i < priceFiltered.size(); i++) {
          assertEquals(oracle.get(i).getFlightNr(), priceFiltered.get(i).getFlightNr());
      }
    } catch (IndexOutOfBoundsException e) {
      fail("Index out of range");
    }
  }


  @Test
  public void SearchByDepartureDate() {
    Flight oracle = flights.get(3);
    LocalDate searchDate = LocalDate.of(2024, 4, 7);

    assertEquals(oracle, FC.searchFlightsByDepartureDate(searchDate).get(0));
  }


  @Test
  public void SearchByArrivalDate() {
    Flight oracle = flights.get(5);
    LocalDate searchDate = LocalDate.of(2024, 4, 9);

    assertEquals(oracle, FC.searchFlightsByArrivalDate(searchDate).get(0));
  }


  @Test
  public void SearchByDepArr() {
    ArrayList<Flight> depArrFiltered = new ArrayList<>(
      FC.searchFlightsByDepArr("Vestmannaeyjar", "Reykjavík")
    );
    
    assertEquals(flights.get(4).getFlightNr(), depArrFiltered.get(0).getFlightNr());
  }


  @Test
  public void SearchByDepArrWithDate() {
    ArrayList<Flight> depArrTimeFiltered = new ArrayList<>(
      FC.searchFlightsByDepArr("Vestmannaeyjar", "Reykjavík", LocalDate.of(2024, 4, 8))
    );
    
    assertEquals(flights.get(4).getFlightNr(), depArrTimeFiltered.get(0).getFlightNr());
  }


  @Test
  public void SortByPrice() {
    ArrayList<Flight> priceSorted = new ArrayList<Flight>(FC.getSortedByPrice());
    assertEquals(flights, priceSorted);
  }


  @Test
  public void SortByDepartureTime() {
    ArrayList<Flight> departureTimeSorted = new ArrayList<Flight>(FC.getSortedByDepartureTime());
    assertEquals(flights, departureTimeSorted);
  }


  @Test
  public void SortByArrivalTime() {
    ArrayList<Flight> arrivalTimeSorted = new ArrayList<Flight>(FC.getSortedByArrivalTime());
    assertEquals(flights, arrivalTimeSorted);
  }


  @Test
  public void SortByDepartureAddress() {
    ArrayList<Flight> departureAddressSorted = new ArrayList<Flight>(FC.getSortedByDepartureAddress());
    ArrayList<Flight> oracle = new ArrayList<>();

    oracle.add(flights.get(3));
    oracle.add(flights.get(0));
    oracle.add(flights.get(1));
    oracle.add(flights.get(2));
    oracle.add(flights.get(4));
    oracle.add(flights.get(5));

    try {
      for (int i = 0; i < departureAddressSorted.size(); i++) {
        assertEquals(oracle.get(i).getFlightNr(), departureAddressSorted.get(i).getFlightNr());
      }
    } catch (IndexOutOfBoundsException e) {
      fail("Index out of range");
    }
  }


  @Test
  public void SortByArrivalAddress() {
    ArrayList<Flight> arrivalAddressSorted = new ArrayList<Flight>(FC.getSortedByArrivalAddress());
    ArrayList<Flight> oracle = new ArrayList<>();

    oracle.add(flights.get(0));
    oracle.add(flights.get(3));
    oracle.add(flights.get(4));
    oracle.add(flights.get(5));
    oracle.add(flights.get(1));
    oracle.add(flights.get(2));

    try {
      for (int i = 0; i < arrivalAddressSorted.size(); i++) {
        assertEquals(oracle.get(i).getFlightNr(), arrivalAddressSorted.get(i).getFlightNr());
      }
    } catch (IndexOutOfBoundsException e) {
      fail("Index out of range");
    }
  }


  @After
  public void tearDown() {
    flights = null;
    FC = null;
  }
}
