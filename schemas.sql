DROP DATABASE IF EXISTS uts_visual_programming;
CREATE DATABASE uts_visual_programming;
USE uts_visual_programming;

CREATE TABLE rooms (
    _ INT AUTO_INCREMENT PRIMARY KEY,
    room_code       VARCHAR(64) UNIQUE,
    room_name       VARCHAR(128),
    room_location   VARCHAR(128),
    room_wide       INT,
    room_capacity   INT,
    room_price      BIGINT,
    room_note       TEXT
);

CREATE INDEX idx_room_code
  ON rooms (room_code);

CREATE INDEX idx_room_name
  ON rooms (room_name);

CREATE INDEX idx_room_location
  ON rooms (room_location);

CREATE INDEX idx_room_wide
  ON rooms (room_wide);

CREATE INDEX idx_room_capacity
  ON rooms (room_capacity);

CREATE INDEX idx_room_price
  ON rooms (room_price);

CREATE INDEX idx_room_note
  ON rooms (room_note);

DELIMITER //

CREATE TRIGGER set_room_code BEFORE INSERT ON rooms
FOR EACH ROW
BEGIN
    DECLARE next_id INT;
    SELECT COALESCE(MAX(_) + 1, 1) INTO next_id FROM rooms;
    SET NEW.room_code = CONCAT('ROOM-', next_id);
END;
//

DELIMITER ;

INSERT INTO rooms (
    room_name, room_location, room_wide,
    room_capacity, room_price, room_note
) VALUES (
    'Super Deluxe', 'Lantai 1, Nomor 101', 10, 
    3, 784000, 'Peraturan khusus berlaku'
);