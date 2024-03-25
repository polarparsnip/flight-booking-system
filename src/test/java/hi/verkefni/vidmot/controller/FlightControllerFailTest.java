package hi.verkefni.vidmot.controller;

import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import java.time.LocalDate;
import java.util.ArrayList;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import hi.verkefni.classes.Flight;
import hi.verkefni.interfaces.FlightServiceLayerInterface;
import hi.verkefni.vidmot.FlightController;
import hi.verkefni.vidmot.mocks.FlightServiceLayerFail;

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
    ArrayList<Flight> depArrTimeFiltered = new ArrayList<>(
      FC.searchFlightsByDepArr("Vestmannaeyjar", "Reykjavík", LocalDate.of(2024, 4, 8))
    );
    
    assertTrue(depArrTimeFiltered.isEmpty());
  }


  @Test
  public void SortByPriceFail() {
    ArrayList<Flight> priceSorted = new ArrayList<Flight>(FC.getSortedByPrice());
    assertTrue(priceSorted.isEmpty());
  }


  @Test
  public void SortByDepartureTimeFail() {
    ArrayList<Flight> departureTimeSorted = new ArrayList<Flight>(FC.getSortedByDepartureTime());
    assertTrue(departureTimeSorted.isEmpty());
  }


  @Test
  public void SortByArrivalTimeFail() {
    ArrayList<Flight> arrivalTimeSorted = new ArrayList<Flight>(FC.getSortedByArrivalTime());
    assertTrue(arrivalTimeSorted.isEmpty());
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
