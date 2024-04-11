package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleButton;


public class UpdateBookingManagement {
    @FXML
    private TextField removePassengerButton;

    @FXML
    private ToggleButton addInsuranceToggleButton;

    @FXML
    private ToggleButton addAssistanceToggleButton;

    @FXML
    private ToggleButton addSeatToggleButton;

    @FXML
    private ToggleButton addAmenitiesToggleButton;

    @FXML
    private Button addPassengerButton;

    @FXML
    private void addPassengerButton(ActionEvent event) {
        nrTravellers += 1;
            }

    @FXML
    private void removePassengerButtin(ActionEvent event) {
        if (nrTravellers > 0) {
            nrTravellers -= 1;
        }
    }

    private void addInsuranceToggleButton(ActionEvent event) {
        if (addInsuranceToggleButton.isSelected()) {
            insurance = true;
        } else {
            insurance = false;
        }
    }

    private void addAssistanceToggleButton(ActionEvent event) {
        if (addAssistanceToggleButton.isSelected()) {
            assistance = true;
        } else {
            assistance = false;
        }
    }

    private void addSeatToggleButton(ActionEvent event) {
        if (addSeatToggleButton.isSelected()) {
            seat = true;
        } else {
            seat = false;
        }
    }

    private void addAmenitiesToggleButton(ActionEvent event) {
        if (addAmenitiesToggleButton.isSelected()) {
            amenities = true;
        } else {
            amenities = false;
        }
    }



}
