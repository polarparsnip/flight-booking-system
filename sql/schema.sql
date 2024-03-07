DROP TABLE IF EXISTS Bookings;
DROP TABLE IF EXISTS Users;
DROP TABLE IF EXISTS Seats;
DROP TABLE IF EXISTS Flights;

CREATE TABLE Users (
    userId VARCHAR(15) PRIMARY KEY,
    name VARCHAR(30),
    kennitala VARCHAR(10)
);

CREATE TABLE Flights (
    flightId VARCHAR(5) PRIMARY KEY,
    departureAddress VARCHAR(55),
    arrivalAddress VARCHAR(55),
    departureTime VARCHAR(20),
    arrivalTime VARCHAR(20),
    price INT,
    amenities BOOLEAN
);

CREATE TABLE Bookings (
    bookingId VARCHAR(15),
    flightId VARCHAR(30) REFERENCES Flights(flightId),
    userId VARCHAR(30) REFERENCES User(userId),
    seatNr VARCHAR(3) REFERENCES Seats(seatNumber),
    extraLuggage INT DEFAULT 0,
    insured Boolean DEFAULT FALSE,
    minor Boolean DEFAULT FALSE
);

CREATE TABLE Seats (
    flightId VARCHAR(30) REFERENCES Flight(flightId),
    seatNumber VARCHAR(3),
    reserved BOOLEAN,
    PRIMARY KEY (flightId, seatNumber)
);
