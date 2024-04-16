package flight.vidmot;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import flight.classes.Booking;
import flight.classes.Flight;
import flight.classes.Seat;
import flight.classes.User;
import flight.interfaces.BookingServiceLayerInterface;
import flight.serviceLayers.BookingServiceLayer;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;

public class CheckoutController {

    @FXML
    private TextField fxLoggedIn;

    @FXML
    private TextField fxTotalPrice;


    private User purchaser;

    private int numTravelers;

    private Flight departingFlight;

    private List<String> departingSeats;

    private int departingFlightPrice;

    private Flight returningFlight = null;

    private List<String> returningSeats = null;

    private int returningFlightPrice;

    private boolean departingInsured = false;
    private boolean returningInsured = false;


    private BookingServiceLayerInterface BSL = new BookingServiceLayer();
    private BookingController BC = new BookingController(BSL);


    public void setPurchaser(User purchaser) {
        this.purchaser = purchaser;
        this.fxLoggedIn.setText("Skráður inn sem: " + purchaser.getName());
    }


    public void setNumTravelers(int numTravelers) {
        this.numTravelers = numTravelers;
    }
    

    public void setDepartingFlight(Flight departingFlight) {
        this.departingFlight = departingFlight;
    }
    

    public void setDepartingSeats(List<String> departingSeats) {
        this.departingSeats = departingSeats;
    }


    public void setReturningFlight(Flight returningFlight) {
        this.returningFlight = returningFlight;
    }


    public void setReturningSeats(List<String> returningSeats) {
        this.returningSeats = returningSeats;
    }


    public void setDepartingInsured(boolean insured) {
        this.departingInsured = departingInsured;
    }


    public void setReturningInsured(boolean insured) {
        this.returningInsured = returningInsured;
    }


    public void setDepartingPrice(int departingFlightPrice) {
        this.departingFlightPrice = departingFlightPrice;
        fxTotalPrice.setText(Integer.toString(departingFlightPrice) + " kr");
    }


    public void setReturningPrice(int returningFlightPrice) {
        this.returningFlightPrice = returningFlightPrice;
        fxTotalPrice.setText(Integer.toString(departingFlightPrice + returningFlightPrice) + " kr");
    }

    @FXML
    private void fxBackButtonHandler(ActionEvent event) {
        if (ViewSwitcher.lookup(View.RETURNSEATSELECTION) != null) {
            ViewSwitcher.switchTo(View.RETURNSEATSELECTION);            

        } else {
            ViewSwitcher.switchTo(View.SEATSELECTION);            
        }

    }


    @FXML
    private void fxCreateBookingButton(ActionEvent event) {
        System.out.println("User: " + purchaser);
        System.out.println("Number of Travelers: " + numTravelers);
        System.out.println("Departing Flight: " + departingFlight.toString());
        System.out.println("Departing Seats: " + departingSeats);
        if (returningFlight != null && returningSeats != null) {
            System.out.println("Returning Flight: " + returningFlight.toString());
            System.out.println("Returning Seats: " + returningSeats);            
        }

        // System.out.println("Insured: " + insured);

        String bookingId = "";

        LocalDate bookingDate = LocalDate.now();

        List<Seat> departingFlightSeats = new ArrayList<Seat>();
        for (int i = 0; i < departingSeats.size(); i++) {
            Seat s = departingFlight.getSeatBySeatNr(departingSeats.get(i));
            departingFlightSeats.add(s);
        }

        Booking booking = BC.createBooking(purchaser, departingFlight, departingFlightSeats, bookingDate, departingInsured);
        
        System.out.println(booking.getBookingId());

        Booking secondBooking = null;
        if (returningFlight != null && returningSeats != null) {

            List<Seat> returningFlightSeats = new ArrayList<Seat>();
            for (int i = 0; i < returningSeats.size(); i++) {
                Seat s = returningFlight.getSeatBySeatNr(returningSeats.get(i));
                returningFlightSeats.add(s);
            }
    
            secondBooking = BC.createBooking(purchaser, returningFlight, returningFlightSeats, bookingDate, returningInsured);
            bookingId = booking.getBookingId() + " og " + secondBooking.getBookingId();
        } else {
            bookingId = booking.getBookingId();
        }


        ViewSwitcher.switchTo(View.CHECKOUTDONE);
        CheckoutDoneController cdc = (CheckoutDoneController) ViewSwitcher.lookup(View.CHECKOUTDONE);
        cdc.setBookingNumber(bookingId);
        cdc.setPurchaser(purchaser);
    }
}
