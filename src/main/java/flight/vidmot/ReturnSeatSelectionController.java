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

public class ReturnSeatSelectionController {

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

    private List<String> selectedSeats = new ArrayList<>();

    private Map<String, Boolean> seatsReservationStatus;


    // From previous seat selection for departing flight
    private Flight departingFlight = null;

    // private LocalDate originalDepartureDate = null;

    private List<String> departingFlightSeats = null;

    private boolean departingInsured = false;
    private boolean departingSpecialAssistance = false;


    
    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
        this.fxLoggedIn.setText("Skráður inn sem: " + purchaser.getName());
    }


    public void setNumTravelers(int numTravelers) {
        this.numTravelers = numTravelers;
    }


    public void setDepartureLocation(String departureLocation) {
        this.fxDeparture.setText(departureLocation);
    }


    public void setDestinationLocation(String destinationLocation) {
        this.fxDestination.setText(destinationLocation);
    }


    public void setDepartureDate(LocalDate departureDate) {
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

        fxLuggageDropBox.getItems().addAll("Auka taska", "Engin auka taska");
        fxLuggageDropBox.setValue("Auka farangur");
    }


    public void setDepartingFlight(Flight departingFlight) {
        this.departingFlight = departingFlight;
    }


    // public void setOriginalDepartureDate(LocalDate originalDepartureDate) {
    //     this.originalDepartureDate = originalDepartureDate;
    // }


    public void setDepartingFlightSeats(List<String> departingFlightSeats) {
        this.departingFlightSeats = departingFlightSeats;
    }


    public void setDepartingInsured(boolean insured) {
        this.departingInsured = insured;
    }


    public void setDepartingSpecialAssistance(boolean departingSpecialAssistance) {
        this.departingSpecialAssistance = departingSpecialAssistance;
    }


    public void resetSeats() {
        for (Node node : seatSelectionGrid.getChildren()) {
            if (node instanceof Button) {
                Button button = (Button) node;
                String buttonId = button.getId();

                if (buttonId != null && selectedSeats.contains(buttonId)) {
                    selectedSeats.remove(buttonId);
                    button.setStyle("");
                }

            }
        }

        this.selectedSeats.clear();
        // this.departingFlightSeats.clear();

        fxTotalCost.setText(Integer.toString(selectedFlight.getPrice() * selectedSeats.size()) + " kr");
    }


    @FXML
    private void fxBackButtonHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.SEATSELECTION);
        SeatSelectionController ssc = (SeatSelectionController) ViewSwitcher.lookup(View.SEATSELECTION);
        ssc.resetSeats();
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

        if (departingFlightSeats != null && !departingFlightSeats.isEmpty() && !selectedSeats.isEmpty()) {
            ViewSwitcher.switchTo(View.CHECKOUT);
            CheckoutController cc = (CheckoutController) ViewSwitcher.lookup(View.CHECKOUT);
            cc.setPurchaser(purchaser);
            cc.setNumTravelers(numTravelers);

            
            cc.setDepartingFlight(departingFlight);
            cc.setDepartingSeats(departingFlightSeats);
            cc.setDepartingInsured(departingInsured);
            cc.setDepartingSpecialAssistance(departingSpecialAssistance);

            cc.setReturningFlight(selectedFlight);
            cc.setReturningSeats(selectedSeats);
            cc.setReturningInsured(insurance);
            cc.setReturningSpecialAssistance(specialAssistance);

            cc.setPurchaser(purchaser);

            cc.setDepartingPrice(numTravelers * departingFlight.getPrice());
            cc.setReturningPrice(numTravelers * selectedFlight.getPrice());

            
        }
    }
    
}
