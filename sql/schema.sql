DROP TABLE IF EXISTS Bookings;
DROP TABLE IF EXISTS Passengers;
DROP TABLE IF EXISTS Seats;
DROP TABLE IF EXISTS Flights;

CREATE TABLE Passengers (
    passengerId VARCHAR(15) PRIMARY KEY,
    name VARCHAR(30),
    kennitala VARCHAR(10)
);

CREATE TABLE Flights (
    flightNr VARCHAR(5) PRIMARY KEY,
    departureAddress VARCHAR(55),
    arrivalAddress VARCHAR(55),
    departureTime VARCHAR(20),
    arrivalTime VARCHAR(20),
    price INT,
    amenities BOOLEAN
);

CREATE TABLE Bookings (
    purchaserId VARCHAR(30) REFERENCES Passengers(passengerId) ON DELETE CASCADE,
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
    bookingId VARCHAR(15) REFERENCES Bookings(bookingId),
    bookingPassengerId VARCHAR(15) REFERENCES Passengers(passengerId),
    bookedSeatNumber VARCHAR(3) REFERENCES Seats(seatNr),
    PRIMARY KEY (bookingId, bookingPassengerId, bookedSeatNumber)
);
