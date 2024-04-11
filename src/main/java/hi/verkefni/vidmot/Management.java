package hi.verkefni.vidmot;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Management {

    @FXML
    private Label welcomeLabel;

    @FXML
    private Label userLabel;

    @FXML
    private Button dashboardButton;

    @FXML
    private Button bookFlightsButton;

    @FXML
    private Button activeFlightsButton;

    @FXML
    private Button updateFlightsButton;

    @FXML
    private ImageView imageView;

    @FXML
    public String fxUserName;



    @FXML
    private void initialize() {
        Image image = new Image("picture/6681204.png");
        imageView.setImage(image);

        welcomeLabel.setText("Welcome Admin");
        userLabel.setText(fxUserName);
    }

    private void bookFlightsButton(ActionEvent event){
        FXMLLoader goToBookFxmlLoader = new FXMLLoader(HelloApplication.class.getResource("BookingController.fxml"));

    }

    private void activeBookingsButton (ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("ActiveFlightsController.fxml"));
    }

    private void updateBookingButton(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(".fxml"));

    }

    private void DashboardButton(ActionEvent event){
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource("DashboardController.fxml"));
    }
}
