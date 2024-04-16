package flight.vidmot;

public enum View {
    MENU("menu-view.fxml"),
    HOMEPAGE("homepage-view.fxml"),
    FLIGHTS("flights-view.fxml"),
    BOOKFLIGHT("book-flight-view.fxml"),
    BOOKRETURNFLIGHT("book-return-flight-view.fxml"),
    SEATSELECTION("seat-selection-view.fxml"),
    RETURNSEATSELECTION("return-seat-selection-view.fxml"),
    CHECKOUT("checkout-view.fxml"),
    CHECKOUTDONE("checkout-done-view.fxml"),
    USERBOOKINGS("user-bookings-view.fxml");


    private final String fileName;

    View(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return fileName;
    }
}
