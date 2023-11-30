-- Create database
DROP DATABASE IF EXISTS CIRQUEDUSOLEIL;
CREATE DATABASE IF NOT EXISTS CIRQUEDUSOLEIL;
USE CIRQUEDUSOLEIL;

-- Table for Shows
DROP TABLE IF EXISTS SHOWS;
CREATE TABLE SHOWS (
    Name VARCHAR(255) NOT NULL,
    PerformanceYear INT NOT NULL,
    Sponsor VARCHAR(255),
    Producer VARCHAR(255),
    PRIMARY KEY (Name, PerformanceYear)
);

-- Inserting data into Shows table
INSERT INTO SHOWS (Name, PerformanceYear, Sponsor, Producer) VALUES
('Alegria', 2006, 'SponsorA', 'ProducerA'),
('Kooza', 2010, 'SponsorB', 'ProducerB'),
('Corteo', 2015, 'SponsorC', 'ProducerC');

-- Table for Venues
DROP TABLE IF EXISTS VENUES;
CREATE TABLE VENUES (
    VenueID INT NOT NULL,
    Name VARCHAR(255) NOT NULL,
    Capacity INT,
    StreetAddress VARCHAR(255),
    City VARCHAR(255),
    ProvinceorState VARCHAR(255),
    AccessibleByTransit CHAR(1),
    PRIMARY KEY (VenueID)
);

-- Inserting data into Venues table
INSERT INTO VENUES (VenueID, Name, Capacity, StreetAddress, City, ProvinceorState, AccessibleByTransit) VALUES
(1, 'Venue1', 1000, '123 Main St', 'CityA', 'StateA', 'Y'),
(2, 'Venue2', 1500, '456 Broadway', 'CityB', 'StateB', 'N'),
(3, 'Venue3', 1200, '789 Oak St', 'CityC', 'StateC', 'Y');

-- Table for HOSTEDBY (to handle the many-to-many relationship between Shows and Venues)
DROP TABLE IF EXISTS HOSTEDBY;
CREATE TABLE HOSTEDBY (
    ShowName VARCHAR(255) NOT NULL,
    ShowPerformanceYear INT NOT NULL,
    VenueID INT NOT NULL,
    ShowDate DATE,
    location INT,
    PRIMARY KEY (ShowName, ShowPerformanceYear, VenueID),
    FOREIGN KEY (ShowName, ShowPerformanceYear) REFERENCES SHOWS(Name, PerformanceYear) ON DELETE CASCADE ON UPDATE CASCADE,
    FOREIGN KEY (VenueID) REFERENCES VENUES(VenueID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Inserting data into ShowVenues table
INSERT INTO HOSTEDBY (ShowName, ShowPerformanceYear, VenueID, ShowDate, location) VALUES
('Alegria', 2006, 1, '2006-01-15', 2),
('Alegria', 2006, 2, '2006-02-20', 5),
('Kooza', 2010, 2, '2010-03-10', 3),
('Corteo', 2015, 3, '2015-05-05', 1);

-- Table for Performers
DROP TABLE IF EXISTS PERFORMERS;
CREATE TABLE PERFORMERS (
    PerformerID INT NOT NULL,
    FirstName VARCHAR(255),
    LastName VARCHAR(255),
    Citizenship VARCHAR(255),
    Birthdate DATE,
    SpecialDiet VARCHAR(255),
    UnderstudyID INT,
	ShowName VARCHAR(255),
    ShowPerformanceYear INT,
    PRIMARY KEY (PerformerID),
    FOREIGN KEY (ShowName, ShowPerformanceYear) REFERENCES SHOWS(Name, PerformanceYear) ON DELETE CASCADE ON UPDATE CASCADE,
	FOREIGN KEY (UnderstudyID) REFERENCES PERFORMERS(PerformerID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Inserting data into Performers table
INSERT INTO PERFORMERS (PerformerID, FirstName, LastName, Citizenship, Birthdate, SpecialDiet, UnderstudyID, ShowName, ShowPerformanceYear) VALUES
(1, 'John', 'Doe', 'CountryA', '1980-01-01', 'Vegetarian', NULL, 'Alegria', 2006),
(2, 'Jane', 'Smith', 'CountryB', '1990-05-12', 'Vegan', 1, 'Kooza', 2010),
(3, 'Bob', 'Johnson', 'CountryC', '1985-08-25', 'High Protein', 1, 'Corteo', 2015);

-- Table for Musicians 
DROP TABLE IF EXISTS MUSICIAN;
CREATE TABLE MUSICIAN (
    PerformerID INT NOT NULL,
    Instrument VARCHAR(255) NOT NULL,
    PRIMARY KEY (PerformerID, Instrument),
    FOREIGN KEY (PerformerID) REFERENCES PERFORMERS(PerformerID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Inserting data into Musicians table
INSERT INTO MUSICIAN (PerformerID, Instrument) VALUES
(1, 'Guitar'),
(1, 'Drums'),
(3, 'Piano');

-- Table for Aerialists
DROP TABLE IF EXISTS AERIALIST;
CREATE TABLE AERIALIST (
    PerformerID INT NOT NULL,
    Sport VARCHAR(255),
    Equipment VARCHAR(255),
    PRIMARY KEY (PerformerID),
    FOREIGN KEY (PerformerID) REFERENCES PERFORMERS(PerformerID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Inserting data into Aerialists table
INSERT INTO AERIALIST (PerformerID, Sport, Equipment) VALUES
(2, 'Aerial Silks', 'Silk Fabric'),
(3, 'Trapeze', 'Metal Bar');

-- Table for Entertainers
DROP TABLE IF EXISTS ENTERTAINER;
CREATE TABLE ENTERTAINER (
    PerformerID INT NOT NULL,
    MainAct VARCHAR(255),
    PRIMARY KEY (PerformerID),
    FOREIGN KEY (PerformerID) REFERENCES PERFORMERS(PerformerID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Inserting data into Entertainers table
INSERT INTO ENTERTAINER (PerformerID, MainAct) VALUES
(3, 'Juggling');

-- Table for Emergency Contact
DROP TABLE IF EXISTS EMERGENCYCONTACT;
CREATE TABLE EMERGENCYCONTACT (
    PerformerID INT NOT NULL,
	PhoneNumber VARCHAR(20),
    ContactFirstName VARCHAR(255),
    ContactLastName VARCHAR(255),
    Relationship VARCHAR(255),
    PRIMARY KEY (PerformerID, PhoneNumber),
    FOREIGN KEY (PerformerID) REFERENCES Performers(PerformerID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Inserting data into EmergencyContact table
INSERT INTO EMERGENCYCONTACT (PerformerID, ContactFirstName, ContactLastName, PhoneNumber, Relationship) VALUES
(1, 'EmergencyJohn', 'EmergencyDoe', '555-1234', 'Spouse'),
(2, 'EmergencyJane', 'EmergencySmith', '555-5678', 'Sibling'),
(3, 'EmergencyBob', 'EmergencyJohnson', '555-9876', 'Friend');

-- Table for Emergency Contact
DROP TABLE IF EXISTS PERFORMANCEMEDICATION;
CREATE TABLE PERFORMANCEMEDICATION (
    PerformerID INT NOT NULL,
    Medication VARCHAR(255),
    PRIMARY KEY (PerformerID, Medication),
    FOREIGN KEY (PerformerID) REFERENCES Performers(PerformerID) ON DELETE CASCADE ON UPDATE CASCADE
);

-- Inserting data into EmergencyContact table
INSERT INTO PERFORMANCEMEDICATION (PerformerID, medication) VALUES
(1, 'Advil'),
(1, 'Tylenol'),
(3, 'VitaminC');
