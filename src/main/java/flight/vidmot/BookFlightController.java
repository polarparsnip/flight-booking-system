package flight.vidmot;

import java.time.LocalDate;
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

public class BookFlightController {

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

    private List<Flight> returningFlights = null;

    private User purchaser;

    private int numTravelers;

    private String departureLocation;

    private String destinationLocation;

    private LocalDate departureDate;

    private LocalDate arrivalDate;

    private LocalDate returnDate;

    // private static int activeIndex=0;

    private ObservableList<Flight> flightList;

    private Flight selectedFlight = null;


    
    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
        this.fxLoggedIn.setText("Skráður inn sem: " + purchaser.getName());
    }


    public void setNumTravelers(int numTravelers) {
        this.numTravelers = numTravelers;
        this.fxNumTraveler.setText(Integer.toString(numTravelers));
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


    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    
    public void setSelectedFlights(List<Flight> selectedFlights) {
        this.selectedFlights = selectedFlights;

        flightList = FXCollections.observableArrayList(selectedFlights);
        fxFlightList.setItems(flightList);

        fxFlightList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fxDepartureHourField.setText(newValue.getDepartureTime().toString());
                fxArrivalHourField.setText(newValue.getArrivalTime().toString());
                fxNumTraveler.setText("Fjöldi farþega: " + Integer.toString(numTravelers));
                fxFlightCost.setText(Integer.toString(newValue.getPrice()) + " kr");
                selectedFlight = newValue;
            }
        });
    }


    public void setReturningFlights(List<Flight> returningFlights) {
        this.returningFlights = returningFlights;
    }


    @FXML
    private void fxBackButtonHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.FLIGHTS);
    }


    @FXML
    private void fxBookButtonHandler(ActionEvent event) {
        System.out.println("Book");

        if (selectedFlight != null && returningFlights != null && returnDate != null) {

            ViewSwitcher.switchTo(View.BOOKRETURNFLIGHT);
            BookReturnFlightController brfc = (BookReturnFlightController) ViewSwitcher.lookup(View.BOOKRETURNFLIGHT);

            brfc.setPurchaser(purchaser);
            brfc.setNumTravelers(numTravelers);

            brfc.setDepartureLocation(destinationLocation);
            brfc.setDestinationLocation(departureLocation);
            brfc.setDepartureDate(returnDate);
            brfc.setArrivalDate(returnDate);

            brfc.setSelectedFlights(returningFlights);

            brfc.setSelectedDepartureFlight(selectedFlight);
            brfc.setOriginalDepartureDate(departureDate);

            brfc.setOriginalDepartureTime(selectedFlight.getDepartureTime());
            brfc.setOriginalArrivalTime(selectedFlight.getArrivalTime());

        } else if (selectedFlight != null) {
            ViewSwitcher.switchTo(View.SEATSELECTION);
            SeatSelectionController ssc = (SeatSelectionController) ViewSwitcher.lookup(View.SEATSELECTION);
            ssc.setPurchaser(purchaser);
            ssc.setNumTravelers(numTravelers);

            ssc.setDepartureLocation(departureLocation);
            ssc.setDestinationLocation(destinationLocation);
            ssc.setDepartureDate(departureDate);
            ssc.setArrivalDate(arrivalDate);
            ssc.setDepartureTime(selectedFlight.getDepartureTime());
            ssc.setArrivalTime(selectedFlight.getArrivalTime());

            ssc.setSelectedFlight(selectedFlight);
            
        } else {
            System.out.println("Fix");    
        }




    }


    public void initialize() {

        // fxFlightList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
        //     activeIndex = fxFlightList.getSelectionModel().getSelectedIndex();
        //     System.out.println(fxFlightList.getSelectionModel().getSelectedItem());
        // });

    }
    
}
