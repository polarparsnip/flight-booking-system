package flight.vidmot;

import flight.classes.User;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class HomepageController {

    @FXML
    private TextField fxLoggedIn;

    private User purchaser;

    

    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
        fxLoggedIn.setText("Skráður inn sem: " + purchaser.getName());
    }


    @FXML
    private void fxFlightArea() {
        ViewSwitcher.switchTo(View.FLIGHTS);
        SelectFlightController sfc = (SelectFlightController) ViewSwitcher.lookup(View.FLIGHTS);
        sfc.setPurchaser(purchaser);

    }


    @FXML
    private void fxUserArea() {
        ViewSwitcher.switchTo(View.USERBOOKINGS);
        UserBookingsController ubc = (UserBookingsController) ViewSwitcher.lookup(View.USERBOOKINGS);
        ubc.setPurchaser(purchaser);
    }

    @FXML
    private void fxLogoutButtonHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.MENU);
    }
    
}
