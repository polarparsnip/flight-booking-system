package flight.vidmot;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import flight.classes.Flight;
import flight.classes.User;
import flight.interfaces.FlightServiceLayerInterface;
import flight.serviceLayers.FlightServiceLayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

public class SelectFlightController {

    @FXML
    private TextField fxLoggedIn;

    @FXML
    private TextField fxError;

    @FXML
    private ComboBox<String> fxDepartPlace;
  
    @FXML
    private ComboBox<String> fxDestinationPlace;
  
    @FXML
    private DatePicker fxDepartDate;
  
    @FXML
    private DatePicker fxReturnDate;
  
    @FXML
    private Button fxSearchFlights;
  
    @FXML
    private ComboBox<Integer> fxTravelerCount;


    private User purchaser;

    private ObservableList<String> places = FXCollections.observableArrayList();

    private FlightServiceLayerInterface FSL = new FlightServiceLayer();
    private FlightController FC = new FlightController(FSL);
  

    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
        fxLoggedIn.setText("Skráður inn sem: " + purchaser.getName());
    }


    @FXML
    private void fxBackButtonHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.HOMEPAGE);
    }
    
    @FXML
    private void fxSearchButtonHandler(ActionEvent event) {

        if (fxDepartPlace.getValue() == null) {
            fxError.setText("Velja þarf Brottfarastað");
        } else if (fxDestinationPlace.getValue() == null) {
            fxError.setText("Velja þarf áfangastað");
        } else if (fxDepartDate.getValue() == null) {
            fxError.setText("Velja þarf brottfaratíma");
        } else if (fxDepartDate.getValue().isBefore(LocalDate.now())) {
            fxError.setText("Dagsetning má ekki vera á undan núverandi dagsetningu");
        } else if (fxReturnDate.getValue() != null && fxReturnDate.getValue().isBefore(LocalDate.now())) {
            fxError.setText("Dagsetning má ekki vera á undan núverandi dagsetningu");
        } else if (fxTravelerCount.getValue() == null) {
            fxError.setText("Velja þarf fjöldi farþega");
        } else if (fxDepartPlace.getValue() == fxDestinationPlace.getValue()) {
            fxError.setText("Brottfarastaður og áfangastaður mega ekki vera þeir sömu");
        } else {
            System.out.println("Departure Place: " + fxDepartPlace.getValue());
            System.out.println("Destination Place: " + fxDestinationPlace.getValue());
            System.out.println("Departure Date: " + fxDepartDate.getValue().toString());

            if (fxReturnDate.getValue() != null) {
                LocalDate returnDate = fxReturnDate.getValue();
                System.out.println("Return Date: " + returnDate.toString());
            }
            System.out.println("Amount: " + fxTravelerCount.getValue());

            List<Flight> resultingDepartingFlights = FC.searchFlightsByDepArr(fxDepartPlace.getValue(), fxDestinationPlace.getValue(), fxDepartDate.getValue());
            
            List<Flight> filteredDepartingFlights = resultingDepartingFlights
            .stream().filter(flight -> fxTravelerCount.getValue() <= flight.getSeatsAvailable())
            .collect(Collectors.toList());
            

            List<Flight> filteredReturningFlights = null;

            if (fxReturnDate.getValue() != null) {
                List<Flight> resultingReturningFlights = FC.searchFlightsByDepArr(fxDestinationPlace.getValue(), fxDepartPlace.getValue(), fxReturnDate.getValue());

                filteredReturningFlights = resultingReturningFlights.stream()
                .filter(flight ->  fxTravelerCount.getValue() <= flight.getSeatsAvailable())
                .collect(Collectors.toList());
            }

            ViewSwitcher.switchTo(View.BOOKFLIGHT);
            BookFlightController bfc = (BookFlightController) ViewSwitcher.lookup(View.BOOKFLIGHT);

            bfc.setPurchaser(purchaser);
            bfc.setNumTravelers(fxTravelerCount.getValue());
            bfc.setDepartureLocation(fxDepartPlace.getValue());
            bfc.setDestinationLocation(fxDestinationPlace.getValue());
            bfc.setDepartureDate(fxDepartDate.getValue());
            bfc.setArrivalDate(fxDepartDate.getValue());

            bfc.setSelectedFlights(filteredDepartingFlights);

            if (fxReturnDate.getValue() != null && filteredReturningFlights != null) {
                bfc.setReturningFlights(filteredReturningFlights);
                bfc.setReturnDate(fxReturnDate.getValue());
            }

        }

    }


    public void initialize() {
        places.add("Akureyri");
        places.add("Egilsstaðir");
        places.add("Ísafjörður");
        places.add("Reykjavík");
        places.add("Vestmannaeyjar");

        fxTravelerCount.setItems(FXCollections.observableArrayList(1, 2, 3, 4, 5, 6));
        fxDepartPlace.setItems(places);
        fxDestinationPlace.setItems(places);
    }
}
