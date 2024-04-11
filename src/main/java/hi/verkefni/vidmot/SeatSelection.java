package hi.verkefni.vidmot;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.layout.GridPane;


public class SeatSelection extends BookingPart2 {
    @FXML
    private CheckBox amenitiesChecked;
    @FXML
    private CheckBox sitTogetherChecked;
    @FXML
    private CheckBox specialAssistanceChecked;
    @FXML
    private CheckBox insuranceChecked;
    @FXML  
    private Label totalCost;
    @FXML
    private ComboBox<String> luggageDropBox;
    @FXML
    private GridPane gridPane;
   
    @FXML
    public void initialize() {
    }


    @FXML
    public void amenitiesChecked() {
        if (amenitiesChecked.isSelected()) {
            flightCost += 1000;
        } else {
        }
        updateTotalCost();
        
    }

    @FXML
    public void sitTogetherChecked() {
        if (sitTogetherChecked.isSelected()) {
            flightCost += 5000;
        } else {
        }
        updateTotalCost();
    }


//        luggageDropBox.getItems().addAll("Handbag", "Carry On", "Extra 21 KG", "Extra 32 KG");
//        luggageDropBox.setValue("Handbag");
    @FXML   
    public void luggageDropBox() {
        switch (luggageDropBox.getValue()) {
            case "Handbag":
                flightCost += 0;
                break;
            case "Carry On":
                flightCost += 5000;
                break;
            case "Extra 21 KG":
                flightCost += 10000;
                break;
            case "Extra 32 KG":
                flightCost += 15000;
                break;
        }
        updateTotalCost();

    }

    @FXML
    public void specialAssistanceChecked() {
        if (specialAssistanceChecked.isSelected()) {
            flightCost += 0;
        } else {
        }
        updateTotalCost();
    }


    @FXML
    public void insuranceChecked() {
        if (insuranceChecked.isSelected()) {
            flightCost += 5000;
        } else {
        }
        updateTotalCost();
    }
    private void updateTotalCost() {
        totalCost.setText(String.valueOf(flightCost));
    }



}
