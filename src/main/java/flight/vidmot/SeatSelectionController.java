package flight.vidmot;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import flight.classes.Flight;
import flight.classes.User;
import flight.classes.Seat;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.CheckBox;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;

public class SeatSelectionController {

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
    private TextField fxDepartureHourField;

    @FXML
    private TextField fxArrivalHourField;

    @FXML
    private TextField fxTotalCost;

    @FXML
    private GridPane seatSelectionGrid;

    @FXML
    private CheckBox insuranceChecked;

    @FXML
    private CheckBox specialAssistanceChecked;

    @FXML
    private ChoiceBox<String> fxLuggageDropBox;

    private Flight selectedFlight;

    private User purchaser;

    private int numTravelers;

    private String departureLocation;

    private String destinationLocation;

    // private LocalDate departureDate;

    private List<String> selectedSeats = new ArrayList<>();

    private Map<String, Boolean> seatsReservationStatus;


    // Only used if there is a return flight as well
    private Flight returningFlight = null;

    private LocalDate returnDate = null;

    private List<String> departingFlightSeats = null;



    
    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
        this.fxLoggedIn.setText("Skráður inn sem: " + purchaser.getName());
    }


    public void setNumTravelers(int numTravelers) {
        this.numTravelers = numTravelers;
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
        // this.departureDate = departureDate;
        this.fxDepartureDate.setText("Dagsetning: " + departureDate.toString());
    }


    public void setArrivalDate(LocalDate arrivalDate) {
        this.fxArrivalDate.setText("Dagsetning: " + arrivalDate.toString());
    }


    public void setDepartureTime(LocalTime departureTime) {
        this.fxDepartureHourField.setText(departureTime.toString());
    }


    public void setArrivalTime(LocalTime arrivalTime) {
        this.fxArrivalHourField.setText(arrivalTime.toString());
    }


    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }
   
    
    public void resetSeats() {
        for (Node node : seatSelectionGrid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                String buttonId = button.getId();

                if (buttonId != null) {
                    if (selectedSeats.contains(buttonId)) {
                        selectedSeats.remove(buttonId);
                    }
                    
                    button.setStyle(null);
                }

            }
        }

        this.selectedSeats.clear();

        fxTotalCost.setText(Integer.toString(selectedFlight.getPrice() * selectedSeats.size()) + " kr");
    }


    public void setSelectedFlight(Flight selectedFlight) {
        this.selectedFlight = selectedFlight;

        List<Seat> seats = selectedFlight.getSeats();
        seatsReservationStatus = new HashMap<>();

        for (int i = 0; i < seats.size();i++) {
            String seatNr = seats.get(i).getSeatNr();
            boolean isReserved = seats.get(i).getReservationStatus();
            seatsReservationStatus.put(seatNr, isReserved);
        }

        for (Node node : seatSelectionGrid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                String buttonId = button.getId();

                if (buttonId != null && seatsReservationStatus.containsKey(buttonId) && seatsReservationStatus.get(buttonId)) {
                    button.setOpacity(1.0); 
                    button.setStyle("-fx-background-color: red;");
                    button.setDisable(true);
                }

            }
        }

        fxLuggageDropBox.getItems().addAll("Auka farangur", "Engin auka farangur");
        fxLuggageDropBox.setValue("Auka farangur");
    }


    public void setReturningFlight(Flight returningFlight) {
        this.returningFlight = returningFlight;
    }


    public void setDepartingFlightSeats(List<String> departingFlightSeats) {
        this.departingFlightSeats = departingFlightSeats;
    }


    @FXML
    private void fxBackButtonHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.BOOKFLIGHT);
        resetSeats();
    }



    @FXML
    private void fxToggleSeat(ActionEvent event) {
        Button clickedButton = (Button) event.getSource();
        String seatId = clickedButton.getId();

        if (selectedSeats.contains(seatId)) {
            selectedSeats.remove(seatId);
            clickedButton.setStyle("");
            fxTotalCost.setText(Integer.toString(selectedFlight.getPrice() * selectedSeats.size()) + " kr");
        } else if (selectedSeats.size() < numTravelers) {
            selectedSeats.add(seatId);
            clickedButton.setStyle("-fx-background-color: green;");
            fxTotalCost.setText(Integer.toString(selectedFlight.getPrice() * selectedSeats.size()) + " kr");
        }

    }


    @FXML
    private void fxConfirmSeatSelectionButtonHandler(ActionEvent event) {

        boolean insurance = insuranceChecked.isSelected();

        boolean specialAssistance = specialAssistanceChecked.isSelected();

        if (returningFlight != null && returnDate != null && selectedSeats != null && !selectedSeats.isEmpty()) {
            ViewSwitcher.switchTo(View.RETURNSEATSELECTION);
            ReturnSeatSelectionController rssc = (ReturnSeatSelectionController) ViewSwitcher.lookup(View.RETURNSEATSELECTION);
            

            rssc.setPurchaser(purchaser);
            rssc.setNumTravelers(numTravelers);

            rssc.setDepartureLocation(destinationLocation);
            rssc.setDestinationLocation(departureLocation);

            rssc.setDepartureDate(returnDate);
            rssc.setArrivalDate(returnDate);

            rssc.setDepartureTime(returningFlight.getDepartureTime());
            rssc.setArrivalTime(returningFlight.getArrivalTime());

            rssc.setSelectedFlight(returningFlight);

            // rssc.setOriginalDepartureDate(departureDate);
            rssc.setDepartingFlight(selectedFlight);
            rssc.setDepartingFlightSeats(selectedSeats);
            rssc.setDepartingInsured(insurance);
            rssc.setDepartingSpecialAssistance(specialAssistance);
        }
        else if (!selectedSeats.isEmpty()){
            ViewSwitcher.switchTo(View.CHECKOUT);
            CheckoutController cc = (CheckoutController) ViewSwitcher.lookup(View.CHECKOUT);
            cc.setPurchaser(purchaser);
            cc.setNumTravelers(numTravelers);
            cc.setDepartingFlight(selectedFlight);
            cc.setDepartingSeats(selectedSeats);
            cc.setDepartingInsured(insurance);
            cc.setDepartingSpecialAssistance(specialAssistance);
            cc.setPurchaser(purchaser);
            cc.setDepartingPrice(numTravelers * selectedFlight.getPrice());
               
        }

        

    }
    
}
