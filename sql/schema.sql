DROP TABLE IF EXISTS BookedSeats;
DROP TABLE IF EXISTS Bookings;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Seats;
DROP TABLE IF EXISTS Flights;

CREATE TABLE Users (
    userId VARCHAR(15) PRIMARY KEY,
    name VARCHAR(30)
);

CREATE TABLE Flights (
    flightNr VARCHAR(5) PRIMARY KEY,
    departureAddress VARCHAR(55),
    arrivalAddress VARCHAR(55),
    departureDate VARCHAR(20),
    departureTime TIME,
    arrivalDate VARCHAR(20),
    arrivalTime TIME,
    price INT,
    amenities BOOLEAN DEFAULT FALSE
);

CREATE TABLE Bookings (
    purchaserId VARCHAR(30) REFERENCES Users(userId) ON DELETE CASCADE,
    bookingId VARCHAR(15),
    flightNr VARCHAR(30) REFERENCES Flights(flightNr) ON DELETE CASCADE,
    bookingDate DATE,
    insured Boolean DEFAULT FALSE
);

CREATE TABLE Seats (
    flightNr VARCHAR(30) REFERENCES Flight(flightNr) ON DELETE CASCADE,
    seatNumber VARCHAR(3),
    reserved BOOLEAN,
    PRIMARY KEY (flightNr, seatNumber)
);

CREATE TABLE BookedSeats (
    bookingFlightNr VARCHAR(30) REFERENCES Flights(flightNr) ON DELETE CASCADE,
    bookedSeatNumber VARCHAR(3) REFERENCES Seats(seatNumber) ON DELETE CASCADE,
    bookingId VARCHAR(15) REFERENCES Bookings(bookingId) ON DELETE CASCADE,
    PRIMARY KEY (bookingFlightNr, bookedSeatNumber)
);
