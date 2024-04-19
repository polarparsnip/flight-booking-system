package flight.vidmot;

import flight.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CheckoutDoneController {

    @FXML
    private TextField fxLoggedIn;

    @FXML
    private TextField fxBookingNumber;

    // private User purchaser;


    public void setPurchaser(User purchaser) {
        // this.purchaser = purchaser;

        fxLoggedIn.setText("Skráður inn sem: " + purchaser.getName());
    }


    @FXML
    public void setBookingNumber(String bookingNumber) {
        this.fxBookingNumber.setText(bookingNumber);
    }


    @FXML
    private void fxBackToHomeButtonHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.HOMEPAGE);
    }
    
}
