package flight.vidmot.vidmot;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import flight.classes.Flight;
import flight.interfaces.FlightServiceLayerInterface;
import flight.vidmot.FlightController;
import flight.vidmot.mocks.FlightServiceLayerFail;

public class FlightControllerFailTest {

  private FlightController FC;

  @Before
  public void setUp() {
    FlightServiceLayerInterface FSLF = new FlightServiceLayerFail();
    FC = new FlightController(FSLF);

  }


  @Test
  public void SearchByFlightNrFail() {
    assertNull(FC.searchFlightByFlightNr("F-002"));
  }


  @Test
  public void SearchByPriceFail() {
    ArrayList<Flight> priceFiltered = new ArrayList<>(
      FC.searchFlightsByPriceRange(19000, 23000)
    );

    assertTrue(priceFiltered.isEmpty());
  }


  @Test
  public void SearchByDepartureDateFail() {
    LocalDate searchDate = LocalDate.of(2024, 4, 7);
    assertTrue(FC.searchFlightsByDepartureDate(searchDate).isEmpty());
  }


  @Test
  public void SearchByArrivalDateFail() {
    LocalDate searchDate = LocalDate.of(2024, 4, 9);
    assertTrue(FC.searchFlightsByArrivalDate(searchDate).isEmpty());
  }


  @Test
  public void SearchByDepArrFail() {
    ArrayList<Flight> depArrfiltered = new ArrayList<>(
      FC.searchFlightsByDepArr("Vestmannaeyjar", "Reykjavík")
    );
    
    assertTrue(depArrfiltered.isEmpty());
  }


  @Test
  public void SearchByDepArrWithDateFail() {
    ArrayList<Flight> depArrDateFiltered = new ArrayList<>(
      FC.searchFlightsByDepArr("Vestmannaeyjar", "Reykjavík", LocalDate.of(2024, 4, 8))
    );
    
    assertTrue(depArrDateFiltered.isEmpty());
  }


  @Test
  public void SortByPriceFail() {
    ArrayList<Flight> priceSorted = new ArrayList<Flight>(FC.getSortedByPrice());
    assertTrue(priceSorted.isEmpty());
  }


  @Test
  public void SortByDepartureDateFail() {
    ArrayList<Flight> departureDateSorted = new ArrayList<Flight>(FC.getSortedByDepartureDate());
    assertTrue(departureDateSorted.isEmpty());
  }


  @Test
  public void SortByArrivalDateFail() {
    ArrayList<Flight> arrivalDateSorted = new ArrayList<Flight>(FC.getSortedByArrivalDate());
    assertTrue(arrivalDateSorted.isEmpty());
  }


  @Test
  public void SortByDepartureAddressFail() {
    ArrayList<Flight> departureAddressSorted = new ArrayList<Flight>(FC.getSortedByDepartureAddress());
    assertTrue(departureAddressSorted.isEmpty());
  }


  @Test
  public void SortByArrivalAddressFail() {
    ArrayList<Flight> arrivalAddressSorted = new ArrayList<Flight>(FC.getSortedByArrivalAddress());
    assertTrue(arrivalAddressSorted.isEmpty());
  }


  @After
  public void tearDown() {
    FC = null;
  }
}
