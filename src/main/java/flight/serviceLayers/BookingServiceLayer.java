package flight.serviceLayers;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.time.LocalDate;
import java.time.LocalTime;

import flight.classes.Booking;
import flight.classes.User;
import flight.classes.Seat;
import flight.classes.Flight;
import flight.database.Database;
import flight.interfaces.BookingServiceLayerInterface;

/**
 * The BookingServiceLayer provides methods for interacting with booking entries
 * in the database.
 */
public class BookingServiceLayer implements BookingServiceLayerInterface {

    private final String databasePath = "sql/flightBookingSystem.db";
    
    private final ArrayList<Booking> bookings = new ArrayList<>();


   /**
   * Creates a booking entry in the database based on a {@link Booking} object.
   * 
   * @param booking The {@link Booking} to be inserted in the database.
   */
    public void createBooking(Booking booking) {
        Database db = new Database(databasePath);
        db.open();

        db.beginTransaction();
    
        String[] UserValues = { booking.getBookingPurchaser().getId(), booking.getBookingPurchaser().getName() };
        
        db.query(
            "insert or ignore into Users (userId, name) values (?, ?)", 
            UserValues,
            false
        );
    
        String[] BookingValues = { 
            booking.getBookingPurchaser().getId(), 
            booking.getBookingId(),
            booking.getFlight().getFlightNr(), 
            booking.getBookingDate().toString(), 
            booking.isInsured().toString() 
        };
    
        db.query(
            "insert into Bookings (purchaserId, bookingId, flightNr, bookingDate, insured) values (?, ?, ?, ?, ?)",
            BookingValues, 
            false
        );
    
        for (Seat seat : booking.getSeatsInBooking()) {
            String[] seatsValues = { booking.getFlight().getFlightNr(), seat.getSeatNr() };
            String[] bookedSeatsValues = { booking.getFlight().getFlightNr(), seat.getSeatNr(), booking.getBookingId() };

            if (!seat.getReservationStatus()) {
                db.query(
                    "UPDATE Seats set reserved = True WHERE flightNr = ? AND seatNumber = ?", 
                    seatsValues, 
                    false
                );

                db.query(
                    "insert into BookedSeats (bookingFlightNr, bookedSeatNumber, bookingId) values (?, ?, ?)", 
                    bookedSeatsValues,
                false
                );
            }
        }

        db.endTransaction();

        db.close();
    }


    /**
     * Updates a pre-existing booking entry in the database.
     * 
     * @param booking The {@link Booking} to be updated in the database.
     */
    public void updateBooking(Booking booking) {
        Database db = new Database(databasePath);
        db.open();

        db.beginTransaction();

        String[] bookingIdValue = { booking.getBookingId() };
        
        String[] updateSeatsValue = { booking.getFlight().getFlightNr(), booking.getBookingId() };
        
        String[] bookingValues = { 
            booking.getBookingPurchaser().getId(), 
            booking.getFlight().getFlightNr(), 
            booking.getBookingDate().toString(), 
            booking.isInsured().toString(),
            booking.getBookingId()
        };
        
        db.query(
            "UPDATE Bookings SET purchaserId = ?, flightNr = ?, bookingDate = ?, insured = ? WHERE bookingId = ?",
            bookingValues, 
            false
        );

        db.query(
            "UPDATE Seats " +
            "SET reserved = false " +
            "WHERE flightNr = ? AND seatNumber IN (SELECT bookedSeatNumber FROM BookedSeats WHERE bookingId = ?)",   
            updateSeatsValue,
            false
        );

        db.query(
            "DELETE FROM BookedSeats WHERE bookingId = ?",   
            bookingIdValue,
            false
        );
        
        for (Seat seat : booking.getSeatsInBooking()) {
            String[] seatsValues = { booking.getFlight().getFlightNr(), seat.getSeatNr() };
            
            String[] bookedSeatsValues = { 
                booking.getFlightNr(), 
                seat.getSeatNr(),
                booking.getBookingId()
            };

            if (!seat.getReservationStatus()) {
                db.query(
                    "UPDATE Seats set reserved = True WHERE flightNr = ? AND seatNumber = ?", 
                    seatsValues, 
                    false
                );

                db.query(
                    "insert into BookedSeats (bookingFlightNr, bookedSeatNumber, bookingId) values (?, ?, ?)", 
                    bookedSeatsValues,
                false
                );
            }
        }

        db.endTransaction();

        db.close();
    }

    /**
     * Gets the booking entry with the specified booking id from the database.
     * and converts it into a {@link Booking} object that is then returned.
     * 
     * @return {@link Booking} object with the queried booking id.
     */
    public Booking getBookingById(String bookingId) {
        Database db = new Database(databasePath);
        db.open();

        db.beginTransaction();

        Booking booking = null;
        
        String[] bookingIdValue = { bookingId };    

        try {
        
            ResultSet rs = db.query("SELECT * FROM Bookings WHERE bookingId = ?", bookingIdValue, true);

            if (rs.next()) {
                String[] purchaserId = { rs.getString("purchaserId") };
                String[] bookingFlightNr = { rs.getString("flightNr") };
                LocalDate bookingDate = rs.getObject("bookingDate", LocalDate.class);
                Boolean insured = rs.getBoolean("insured");

                ResultSet userRs = db.query("SELECT * FROM Users WHERE userId = ?", purchaserId, true);
                String userName = userRs.getString("name");
                User user = new User(purchaserId[0], userName);

                ResultSet SeatsRs = db.query(
                    "SELECT * FROM Seats JOIN BookedSeats ON Seats.flightNr = BookedSeats.bookingFlightNr " + 
                    "AND Seats.seatNumber = BookedSeats.bookedSeatNumber WHERE BookedSeats.bookingId = ?;", 
                    bookingIdValue, 
                    true
                );

                List<Seat> bookedSeats = new ArrayList<Seat>();
                while (SeatsRs.next()) {
                    String seatFlightNr = SeatsRs.getString("flightNr");
                    String bookedSeatNr = SeatsRs.getString("seatNumber");
                    Boolean reserved = SeatsRs.getBoolean("reserved");
                    Seat tempSeat = new Seat(seatFlightNr, bookedSeatNr, reserved);
                    bookedSeats.add(tempSeat);
                }

                ResultSet FlightRs = db.query(
                    "SELECT * FROM Flights WHERE flightNr = ?)",
                    bookingFlightNr, 
                    true
                );

                String flightNr = FlightRs.getString("flightNr");
                String departureAddress = FlightRs.getString("departureAddress");
                String arrivalAddress = FlightRs.getString("arrivalAddress");
                LocalDate departureDate = FlightRs.getObject("departureDate", LocalDate.class);
                LocalDate arrivalDate = FlightRs.getObject("arrivalDate", LocalDate.class);
                LocalTime departureTime = FlightRs.getObject("departureTime", LocalTime.class);
                LocalTime arrivalTime = FlightRs.getObject("arrivalTime", LocalTime.class);
                Integer price = FlightRs.getInt("price");
                // Boolean amenities = FlightRs.getBoolean("amenities");

                ResultSet flightSeatsRs = db.query(
                    "SELECT * FROM Seats WHERE flightNr = ?",
                    bookingFlightNr, 
                    true
                );

                List<Seat> flightSeats = new ArrayList<Seat>();
                while (flightSeatsRs.next()) {
                    String seatFlightNr = SeatsRs.getString("flightNr");
                    String bookedSeatNr = SeatsRs.getString("seatNumber");
                    Boolean reserved = SeatsRs.getBoolean("reserved");
                    Seat tempSeat = new Seat(seatFlightNr, bookedSeatNr, reserved);
                    flightSeats.add(tempSeat);
                }


                Flight tempFlight = new Flight(
                    flightNr, 
                    (ArrayList<Seat>) flightSeats, 
                    departureAddress, 
                    arrivalAddress,
                    departureDate, 
                    departureTime, 
                    arrivalDate, 
                    arrivalTime, 
                    price
                );


                bookings.add(new Booking(user, tempFlight, bookedSeats, bookingDate, insured));
            } else {
                System.err.println("No booking found with the specified ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }

        return booking;
    }


    /**
     * Gets all bookings from the database made by the user with the specified user id.
     * and converts them into a list of {@link Booking} objects that is then returned.
     * 
     * @param id The user id being queried
     * @return List of all {@link Booking} belonging to the user with the specified user id.
     */
    public List<Booking> getBookingsByUserId(String id) {
        Database db = new Database(databasePath);
        db.open();

        db.beginTransaction();

        List<Booking> bookings = new ArrayList<Booking>();
        
        String[] userId = { id };    

        try {
            ResultSet userRs = db.query("SELECT * FROM Users WHERE userId = ?", userId, true);
            
            if (userRs.next()) {
                String userName = userRs.getString("name");
                User user = new User(id, userName);

                ResultSet rs = db.query("SELECT * FROM Bookings WHERE purchaserId = ?", userId, true);

                while (rs.next()) {
                    // String[] purchaserId = { rs.getString("purchaserId") };
                    String bookingId = rs.getString("bookingId");
                    String[] bookingFlightNr = { rs.getString("flightNr") };

                    // String date = rs.getString("bookingDate");
                    // DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
                    // LocalDate bookingDate = LocalDate.parse(date, formatter);

                    LocalDate bookingDate = rs.getObject("bookingDate", LocalDate.class);

                    Boolean insured = rs.getBoolean("insured");

                    String[] bookingIdValue = { bookingId };
                    ResultSet SeatsRs = db.query(
                        "SELECT * FROM Seats JOIN BookedSeats ON Seats.flightNr = BookedSeats.bookingFlightNr " + 
                        "AND Seats.seatNumber = BookedSeats.bookedSeatNumber WHERE BookedSeats.bookingId = ?;", 
                        bookingIdValue, 
                        true
                    );

                    List<Seat> bookedSeats = new ArrayList<Seat>();
                    while (SeatsRs.next()) {
                        String seatFlightNr = SeatsRs.getString("flightNr");
                        String bookedSeatNr = SeatsRs.getString("seatNumber");
                        Boolean reserved = SeatsRs.getBoolean("reserved");
                        Seat tempSeat = new Seat(seatFlightNr, bookedSeatNr, reserved);
                        bookedSeats.add(tempSeat);
                    }

                    ResultSet FlightRs = db.query(
                        "SELECT * FROM Flights WHERE flightNr = ?",
                        bookingFlightNr, 
                        true
                    );

                    String flightNr = FlightRs.getString("flightNr");
                    String departureAddress = FlightRs.getString("departureAddress");
                    String arrivalAddress = FlightRs.getString("arrivalAddress");
                    LocalDate departureDate = FlightRs.getObject("departureDate", LocalDate.class);
                    LocalDate arrivalDate = FlightRs.getObject("arrivalDate", LocalDate.class);
                    LocalTime departureTime = FlightRs.getObject("departureTime", LocalTime.class);
                    LocalTime arrivalTime = FlightRs.getObject("arrivalTime", LocalTime.class);
                    Integer price = FlightRs.getInt("price");
                    // Boolean amenities = FlightRs.getBoolean("amenities");

                    ResultSet flightSeatsRs = db.query(
                        "SELECT * FROM Seats WHERE flightNr = ?",
                        bookingFlightNr, 
                        true
                    );

                    List<Seat> flightSeats = new ArrayList<Seat>();
                    while (flightSeatsRs.next()) {
                        String seatFlightNr = SeatsRs.getString("flightNr");
                        String bookedSeatNr = SeatsRs.getString("seatNumber");
                        Boolean reserved = SeatsRs.getBoolean("reserved");
                        Seat tempSeat = new Seat(seatFlightNr, bookedSeatNr, reserved);
                        flightSeats.add(tempSeat);
                    }

                    Flight tempFlight = new Flight(
                        flightNr, 
                        (ArrayList<Seat>) flightSeats, 
                        departureAddress, 
                        arrivalAddress,
                        departureDate, 
                        departureTime,
                        arrivalDate,
                        arrivalTime,
                        price
                    );


                    bookings.add(new Booking(user, tempFlight, bookedSeats, bookingDate, insured));

                }      

            } else {
                System.err.println("No user found with the specified ID.");
            }

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return bookings;
    }


    /**
     * Gets all bookings from the database sorted by booking date.
     * and converts them into a list of {@link Booking} objects that is then returned.
     * 
     * @return List of all {@link Booking} entries sorted by booking date.
     */
    public List<Booking> getAllBookings() {
        Database db = new Database(databasePath);
        db.open();

        db.beginTransaction();

        List<Booking> bookings = new ArrayList<Booking>();

        try {

            ResultSet rs = db.query("SELECT * FROM Bookings", null, true);

            while (rs.next()) {
                String[] purchaserId = { rs.getString("purchaserId") };
                String bookingId = rs.getString("bookingId");
                String[] bookingFlightNr = { rs.getString("flightNr") };
                LocalDate bookingDate = rs.getObject("bookingDate", LocalDate.class);
                Boolean insured = rs.getBoolean("insured");

                ResultSet userRs = db.query("SELECT * FROM Users WHERE userId = ?", purchaserId, true);
                String userName = userRs.getString("name");
                User user = new User(purchaserId[0], userName);

                String[] bookingIdValue = { bookingId };
                ResultSet SeatsRs = db.query(
                    "SELECT * FROM Seats JOIN BookedSeats ON Seats.flightNr = BookedSeats.bookingFlightNr " + 
                    "AND Seats.seatNumber = BookedSeats.bookedSeatNumber WHERE BookedSeats.bookingId = ?;", 
                    bookingIdValue, 
                    true
                );

                List<Seat> bookedSeats = new ArrayList<Seat>();
                while (SeatsRs.next()) {
                    String seatFlightNr = SeatsRs.getString("flightNr");
                    String bookedSeatNr = SeatsRs.getString("seatNumber");
                    Boolean reserved = SeatsRs.getBoolean("reserved");
                    Seat tempSeat = new Seat(seatFlightNr, bookedSeatNr, reserved);
                    bookedSeats.add(tempSeat);
                }


                ResultSet FlightRs = db.query(
                    "SELECT * FROM Flights WHERE flightNr = ?",
                    bookingFlightNr, 
                    true
                );

                String flightNr = FlightRs.getString("flightNr");
                String departureAddress = FlightRs.getString("departureAddress");
                String arrivalAddress = FlightRs.getString("arrivalAddress");
                LocalDate departureDate = FlightRs.getObject("departureDate", LocalDate.class);
                LocalDate arrivalDate = FlightRs.getObject("arrivalDate", LocalDate.class);
                LocalTime departureTime = FlightRs.getObject("departureTime", LocalTime.class);
                LocalTime arrivalTime = FlightRs.getObject("arrivalTime", LocalTime.class);
                Integer price = FlightRs.getInt("price");

                
                ResultSet flightSeatsRs = db.query(
                    "SELECT * FROM Seats WHERE flightNr = ?",
                    bookingFlightNr, 
                    true
                );

                List<Seat> flightSeats = new ArrayList<Seat>();
                while (flightSeatsRs.next()) {
                    String seatFlightNr = SeatsRs.getString("flightNr");
                    String bookedSeatNr = SeatsRs.getString("seatNumber");
                    Boolean reserved = SeatsRs.getBoolean("reserved");
                    Seat tempSeat = new Seat(seatFlightNr, bookedSeatNr, reserved);
                    flightSeats.add(tempSeat);
                }

                Flight tempFlight = new Flight(
                    flightNr, (ArrayList<Seat>) flightSeats, 
                    departureAddress, 
                    arrivalAddress,
                    departureDate, 
                    departureTime,
                    arrivalDate, 
                    arrivalTime,
                    price
                );


                bookings.add(new Booking(user, tempFlight, bookedSeats, bookingDate, insured));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
            db.close();
        }
        return bookings;
    }


  /**
   * Deletes a pre-existing booking entry from the database.
   * 
   * @param booking The {@link Booking} to be deleted from the database.
   */
    public void deleteBooking(Booking booking) {
        Database db = new Database(databasePath);
        db.open();

        db.beginTransaction();

        String[] bookingId = { booking.getBookingId() };

        for (Seat seat : booking.getSeatsInBooking()) {
            String[] bookedSeatsValues = { booking.getFlightNr(), seat.getSeatNr() };

            if (seat.getReservationStatus()) {
                db.query("UPDATE Seats set reserved = False WHERE flightNr = ? AND seatNumber = ?", bookedSeatsValues, false);
            }

            db.query("DELETE FROM BookedSeats WHERE bookingFlightNr = ? AND bookedSeatNumber = ?", bookedSeatsValues, false);
        }

        db.query("DELETE FROM Bookings WHERE bookingId = ?", bookingId, false);            


        db.endTransaction();
            
        db.close();
    
    }
    
}
