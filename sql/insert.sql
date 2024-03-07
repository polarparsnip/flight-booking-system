INSERT INTO Users (
  userId,
  name,
  kennitala
) VALUES 
    ('012345678912345', 'Randver', '1212001234');

INSERT INTO Flights (
    flightId,
    departureAddress,
    arrivalAddress,
    departureTime,
    arrivalTime,
    price,
    amenities
) VALUES 
  ('F-000', 'Reykjavík', 'Akureyri', '2024-04-04', '2024-04-04', 10000, TRUE),
  ('F-001', 'Reykjavík', 'Ísafjörður', '2024-04-04', '2024-04-04', 11000, FALSE),
  ('F-002', 'Reykjavík', 'Vestmannaeyjar', '2024-04-05', '2024-04-05', 12000, FALSE),
  ('F-003', 'Akureyri', 'Reykjavík', '2024-04-06', '2024-04-06', 13000, TRUE),
  ('F-004', 'Ísafjörður', 'Reykjavík', '2024-04-06', '2024-04-06', 14000, FALSE),
  ('F-005', 'Vestmannaeyjar', 'Reykjavík', '2024-04-07', '2024-04-07', 15000, FALSE);

INSERT INTO Bookings (
    bookingId,
    flightId,
    userId,
    seatNr
) VALUES 
    ('F-001-123456789', 'F-001', '012345678912345', 'A1');

INSERT INTO Seats (
    flightId,
    seatNumber,
    reserved
) VALUES 
    ('F-000', 'A1', false), ('F-000', 'A2', false), ('F-000', 'B1', false), ('F-000', 'B2', false), ('F-000', 'C1', false), ('F-000', 'C2', false),
    ('F-001', 'A1', true), ('F-001', 'A2', false), ('F-001', 'B1', false), ('F-001', 'B2', false), ('F-001', 'C1', false), ('F-001', 'C2', false),
    ('F-002', 'A1', false), ('F-002', 'A2', false), ('F-002', 'B1', false), ('F-002', 'B2', false), ('F-002', 'C1', false), ('F-002', 'C2', false),
    ('F-003', 'A1', false), ('F-003', 'A2', false), ('F-003', 'B1', false), ('F-003', 'B2', false), ('F-003', 'C1', false), ('F-003', 'C2', false),
    ('F-004', 'A1', false), ('F-004', 'A2', false), ('F-004', 'B1', false), ('F-004', 'B2', false), ('F-004', 'C1', false), ('F-004', 'C2', false),
    ('F-005', 'A1', false), ('F-005', 'A2', false), ('F-005', 'B1', false), ('F-005', 'B2', false), ('F-005', 'C1', false), ('F-005', 'C2', false);