module flight.vidmot.flight_booking_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens flight.vidmot to javafx.fxml;
    exports flight.classes;
    exports flight.interfaces;
    exports flight.vidmot;
}