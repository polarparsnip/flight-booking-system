package flight.vidmot;

import java.util.List;

import flight.classes.Booking;
import flight.classes.User;
import flight.interfaces.BookingServiceLayerInterface;
import flight.serviceLayers.BookingServiceLayer;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;

public class UserBookingsController {

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
    private TextField fxBookingId;

    @FXML
    private ListView<Booking> fxBookingList;

    @FXML
    private TextField fxDepartureHourField;

    @FXML
    private TextField fxArrivalHourField;

    @FXML
    private TextField fxInsurance;

    @FXML
    private TextField fxFlightNr;


    private User purchaser;

    private List<Booking> userBookings = null;

    private ObservableList<Booking> bookingList;

    private Booking selectedBooking = null;

    private BookingServiceLayerInterface BSL = new BookingServiceLayer();
    private BookingController BC = new BookingController(BSL);

    
    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
        this.fxLoggedIn.setText("Skráður inn sem: " + purchaser.getName());


        this.userBookings = BC.getBookingsByUserId(purchaser.getId());

        bookingList = FXCollections.observableArrayList(userBookings);
        fxBookingList.setItems(bookingList);

        fxBookingList.getSelectionModel().selectedItemProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue != null) {
                fxBookingId.setText(newValue.getBookingId());
                fxDeparture.setText(newValue.getFlight().getDepartureAddress());
                fxDestination.setText(newValue.getFlight().getArrivalAddress());

                fxDepartureDate.setText(newValue.getFlight().getDepartureDate().toString());
                fxArrivalDate.setText(newValue.getFlight().getArrivalDate().toString());
                fxDepartureHourField.setText(newValue.getFlight().getDepartureTime().toString());
                fxArrivalHourField.setText(newValue.getFlight().getArrivalTime().toString());

                fxFlightCost.setText(Integer.toString(newValue.getBookingPrice()) + " kr");

                fxFlightNr.setText("Flugnúmer: " + newValue.getFlightNr());

                String insuredStatusString = newValue.isInsured() ? "Já" : "nei";
                fxInsurance.setText("Flugtrygging: " + insuredStatusString);

                selectedBooking = newValue;

            }
        });
    }
    

    @FXML
    private void fxBackButtonHandler(ActionEvent event) {
        ViewSwitcher.switchTo(View.HOMEPAGE);
    }

}
