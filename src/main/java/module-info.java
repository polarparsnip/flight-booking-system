module hi.verkefni.vidmot.flight_booking_system {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.sql;


    opens hi.verkefni.vidmot to javafx.fxml;
    exports hi.verkefni.classes;
    exports hi.verkefni.interfaces;
    exports hi.verkefni.vidmot;
}