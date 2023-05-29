-- Insert sample data into Railways table
INSERT INTO railways (railway_number, departure_station, arrival_station, departure_datetime, arrival_datetime)
VALUES
    ('R001', 'Station A', 'Station B', '2023-05-01 08:00:00', '2023-05-01 10:00:00'),
    ('R002', 'Station B', 'Station C', '2023-05-02 09:00:00', '2023-05-02 11:00:00'),
    ('R003', 'Station C', 'Station D', '2023-05-03 10:00:00', '2023-05-03 12:00:00');

-- Insert data into users table
-- passwords for users
-- user1: password1
-- user2: password2
-- user3: password3
INSERT INTO users (id, username, email, password, first_name, last_name)
VALUES
    (1, 'john.doe', 'john.doe@gmail.com', '$2a$10$tc7vuewmbdFm.vcfKM885.0OemBi26yWw/WdbLMOyreWOKyq1VVkq', 'John', 'Doe'),
    (2, 'jane.smith', 'jane.smith@gmail.com', '$2a$10$YyAe8bL9rctINX7yXEqoOuykdTZjvTI/rEsnhjEZ4Iun5RYXPf8w6', 'Jane', 'Smith'),
    (3, 'admin', 'admin@gmail.com', '$2a$10$mEUTWV5.C9N/aOA9HUvjpOJFWye63QceAJX/7xiPeU4kf33xuXG4u', 'Adam', 'Levine');

-- Insert data into roles table
INSERT INTO roles (id, name)
VALUES
    (1, 'USER'),
    (2, 'ADMIN');

-- Insert data into user_roles table
INSERT INTO user_roles (user_id, role_id)
VALUES
    (1, 1),
    (2, 1),
    (3, 1),
    (3, 2);

-- Insert sample data into Orders table
INSERT INTO orders (id, order_datetime, railway_id, user_id)
VALUES
    (1, '2023-05-28 09:30:00', 1, 1),
    (2, '2023-05-28 12:00:00', 2, 2),
    (3, '2023-05-28 15:30:00', 3, 3);

INSERT INTO seats (seat_number, carriage, is_available, railway_id)
VALUES
    ('A1', 'Carriage 1', false, 1), ('A2', 'Carriage 1', true, 1), ('A3', 'Carriage 1', true, 1), ('A4', 'Carriage 1', true, 1),
    ('B1', 'Carriage 2', true, 1), ('B2', 'Carriage 2', true, 1), ('B3', 'Carriage 2', true, 1), ('B4', 'Carriage 2', true, 1),
    ('C1', 'Carriage 3', true, 1), ('C2', 'Carriage 3', true, 1), ('C3', 'Carriage 3', true, 1), ('C4', 'Carriage 3', true, 1),
    ('D1', 'Carriage 4', true, 1), ('D2', 'Carriage 4', true, 1), ('D3', 'Carriage 4', true, 1), ('D4', 'Carriage 4', true, 1),
    ('A1', 'Carriage 1', false, 2), ('A2', 'Carriage 1', true, 2), ('A3', 'Carriage 1', true, 2), ('A4', 'Carriage 1', true, 2),
    ('B1', 'Carriage 2', true, 2), ('B2', 'Carriage 2', true, 2), ('B3', 'Carriage 2', true, 2), ('B4', 'Carriage 2', true, 2),
    ('C1', 'Carriage 3', true, 2), ('C2', 'Carriage 3', true, 2), ('C3', 'Carriage 3', true, 2), ('C4', 'Carriage 3', true, 2),
    ('D1', 'Carriage 4', true, 2), ('D2', 'Carriage 4', true, 2), ('D3', 'Carriage 4', true, 2), ('D4', 'Carriage 4', true, 2),
    ('A1', 'Carriage 1', false, 3), ('A2', 'Carriage 1', true, 3), ('A3', 'Carriage 1', true, 3), ('A4', 'Carriage 1', true, 3),
    ('B1', 'Carriage 2', true, 3), ('B2', 'Carriage 2', true, 3), ('B3', 'Carriage 2', true, 3), ('B4', 'Carriage 2', true, 3),
    ('C1', 'Carriage 3', true, 3), ('C2', 'Carriage 3', true, 3), ('C3', 'Carriage 3', true, 3), ('C4', 'Carriage 3', true, 3),
    ('D1', 'Carriage 4', true, 3), ('D2', 'Carriage 4', true, 3), ('D3', 'Carriage 4', true, 3), ('D4', 'Carriage 4', true, 3);


INSERT INTO tickets (id, passenger_first_name, passenger_last_name, seat_id, order_id)
VALUES
    (1, 'John', 'Doe', 1, 1),
    (2, 'Jane', 'Smith', 17, 2),
    (3, 'David', 'Johnson', 33, 3);