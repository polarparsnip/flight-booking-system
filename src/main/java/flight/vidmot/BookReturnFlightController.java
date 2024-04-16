package flight.vidmot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import flight.classes.Flight;
import flight.classes.User;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class BookReturnFlightController {

    @FXML
    private TextField fxLoggedIn;

    @FXML
    private TextField fxDeparture;

    @FXML
    private TextField fxDestination;

    @FXML
    private TextField fxDepartureDate;

    @FXML
    private TextField fxArrivalDate;

    @FXML
    private TextField fxFlightCost;

    @FXML
    private TextField fxNumTraveler;

    @FXML
    private ListView<Flight> fxFlightList;

    @FXML
    private TextField fxDepartureHourField;

    @FXML
    private TextField fxArrivalHourField;

    private List<Flight> selectedFlights = null;

    // private List<Flight> returningFlights = null;

    private User purchaser;

    private int numTravelers;

    private String departureLocation;

    private String destinationLocation;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private ObservableList<Flight> flightList;

    private Flight selectedFlight = null;

    // Only used if there is return flight as well
    private Flight selectedDepartureFlight = null;

    private LocalDate originalDepartureDate = null;

    private LocalTime originalDepartureTime = null;

    private LocalTime originalArrivalTime = null;


    
    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
        this.fxLoggedIn.setText("Skráður inn sem: " + purchaser.getName());
    }


    public void setNumTravelers(int numTravelers) {
        this.numTravelers = numTravelers;
        fxNumTraveler.setText("Fjöldi farþega: " + Integer.toString(numTravelers));

    }


    public void setDepartureLocation(String departureLocation) {
        this.departureLocation = departureLocation;
        this.fxDeparture.setText(departureLocation);
    }


    public void setDestinationLocation(String destinationLocation) {
        this.destinationLocation = destinationLocation;
        this.fxDestination.setText(destinationLocation);
    }


    public void setDepartureDate(LocalDate departureDate) {
        this.departureDate = departureDate;
        this.fxDepartureDate.setText("Brottfaratími: " + departureDate.toString());
    }


    public void setArrivalDate(LocalDate arrivalDate) {
        this.arrivalDate = arrivalDate;
        this.fxArrivalDate.setText("Komutími: " + arrivalDate.toString());
    }


    public void setOriginalDepartureDate(LocalDate originalDepartureDate) {
        this.originalDepartureDate = originalDepartureDate;
    }

    public void setOriginalDepartureTime(LocalTime originalDepartureTime) {
        this.originalDepartureTime = originalDepartureTime;
    }

    public void setOriginalArrivalTime(LocalTime originalArrivalTime) {
        this.originalArrivalTime = originalArrivalTime;
    }

    
    public void setSelectedFlights(List<Flight> selectedFlights) {
        this.selectedFlights = selectedFlights;

        flightList = FXCollections.observableArrayList(selectedFlights);
        fxFlightList.setItems(flightList);

        fxFlightList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fxDepartureHourField.setText(newValue.getDepartureTime().toString());
                fxArrivalHourField.setText(newValue.getArrivalTime().toString());
                fxFlightCost.setText(Integer.toString(newValue.getPrice()) + " kr");
                selectedFlight = newValue;
            }
        });
    }


    // public void setReturningFlights(List<Flight> returningFlights) {
    //     this.returningFlights = returningFlights;
    // }


    public void setSelectedDepartureFlight(Flight selectedDepartureFlight) {
        this.selectedDepartureFlight = selectedDepartureFlight;
    }


    @FXML
    private void fxBackButtonHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.BOOKFLIGHT);
    }


    @FXML
    private void fxBookButtonHandler(ActionEvent event) {
        System.out.println("Book");

        if (selectedFlight != null && selectedDepartureFlight != null) {
            ViewSwitcher.switchTo(View.SEATSELECTION);
            SeatSelectionController ssc = (SeatSelectionController) ViewSwitcher.lookup(View.SEATSELECTION);
            ssc.setPurchaser(purchaser);
            ssc.setNumTravelers(numTravelers);

            ssc.setDepartureLocation(destinationLocation);
            ssc.setDestinationLocation(departureLocation);
            
            ssc.setDepartureDate(originalDepartureDate);
            ssc.setArrivalDate(originalDepartureDate);

            ssc.setDepartureTime(originalDepartureTime);
            ssc.setArrivalTime(originalArrivalTime);

            ssc.setSelectedFlight(selectedDepartureFlight);
            ssc.setReturningFlight(selectedFlight);
            ssc.setReturnDate(departureDate);
            
            ssc.resetSeats();
            
        } else {
            System.out.println("Fix");    
        }

    }
    
}
