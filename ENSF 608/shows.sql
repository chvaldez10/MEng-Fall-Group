-- shows.sql
-- ENSF608 Final Project
-- Group Members:
-- Redge Santillan
-- Chioma Ukaegbu
-- Christian Valdez

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
    ('Alegria', 2006, 'Cirque du Soleil', 'Cirque du Soleil Productions'),
    ('Kooza', 2010, 'Cirque du Soleil', 'Cirque du Soleil Productions'),
    ('Corteo', 2015, 'Cirque du Soleil', 'Cirque du Soleil Productions'),
    ('The Lion King', 2019, 'Disney', 'Disney Theatrical Productions'),
    ('Hamilton', 2020, 'American Express', 'Lin-Manuel Miranda Productions'),
    ('Wicked', 2021, 'Universal Pictures', 'Marc Platt Productions'),
    ('Cats', 2018, 'Jellicle Corporation', 'Really Useful Group'),
    ('Phantom of the Opera', 2022, 'Cameron Mackintosh Ltd', 'Andrew Lloyd Webber Productions'),
    ('Les Misérables', 2017, 'ABC Entertainment', 'Cameron Mackintosh Ltd'),
    ('Chicago', 2019, 'XYZ Corporation', 'Barry and Fran Weissler Productions');

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
    (1, 'The Grand Theater', 1000, '123 Main St', 'Cityville', 'State A', 'Y'),
    (2, 'Majestic Hall', 800, '456 Oak St', 'Townburg', 'State B', 'N'),
    (3, 'Royal Arena', 1500, '789 Pine St', 'Villagetown', 'State C', 'Y'),
    (4, 'Starlight Pavilion', 1200, '101 Elm St', 'Metropolis', 'State D', 'N'),
    (5, 'Sunny Amphitheater', 2000, '202 Maple St', 'Sunshine City', 'State E', 'Y'),
    (6, 'Harmony Hall', 600, '303 Cedar St', 'Melodyville', 'State F', 'N'),
    (7, 'Azure Convention Center', 1800, '404 Birch St', 'Oceanview', 'State G', 'Y'),
    (8, 'Golden Plaza', 900, '505 Walnut St', 'Goldentown', 'State H', 'N'),
    (9, 'Silver Theater', 1300, '606 Spruce St', 'Silvertown', 'State I', 'Y'),
    (10, 'Civic Square', 700, '707 Fir St', 'Cityopolis', 'State J', 'N');

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
    ('Alegria', 2006, 1, '2006-05-15', 1),
    ('Kooza', 2010, 3, '2010-08-20', 2),
    ('Corteo', 2015, 5, '2015-11-10', 3),
    ('The Lion King', 2019, 2, '2019-03-25', 4),
    ('Hamilton', 2020, 4, '2020-06-12', 5),
    ('Wicked', 2021, 6, '2021-09-08', 6),
    ('Cats', 2018, 8, '2018-12-03', 7),
    ('Phantom of the Opera', 2022, 10, '2022-02-18', 8),
    ('Les Misérables', 2017, 7, '2017-07-30', 9),
    ('Chicago', 2019, 9, '2019-10-14', 10);

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
    (1, 'John', 'Doe', 'USA', '1990-05-15', 'Vegetarian', NULL, 'Alegria', 2006),
    (2, 'Jane', 'Smith', 'Canada', '1988-08-20', 'Gluten-Free', NULL, 'Alegria', 2006),
    (3, 'Michael', 'Johnson', 'France', '1995-11-10', 'Vegan', NULL, 'Kooza', 2010),
    (4, 'Emily', 'Williams', 'USA', '1992-03-25', 'Nut-Free', 2, 'Kooza', 2010),
    (5, 'David', 'Brown', 'UK', '1987-06-12', 'Dairy-Free', NULL, 'Corteo', 2015),
    (6, 'Sophia', 'Taylor', 'Australia', '1998-09-08', 'Vegetarian', NULL, 'Corteo', 2015),
    (7, 'Daniel', 'Davis', 'Italy', '1993-12-03', 'Vegan', 1, 'The Lion King', 2019),
    (8, 'Olivia', 'Miller', 'USA', '1996-02-18', 'Gluten-Free', NULL, 'The Lion King', 2019),
    (9, 'Alex', 'White', 'Canada', '1990-07-30', 'Nut-Free', NULL, 'Hamilton', 2020),
    (10, 'Sophie', 'Johnson', 'France', '1994-10-14', 'Dairy-Free', 8, 'Hamilton', 2020);

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
    (1, 'Piano'),
    (1, 'Violin'),
    (7, 'Trumpet');

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
    (2, 'Aerial Silks', 'Silks, Rigging'),
    (4, 'Aerial Hoop', 'Hoop, Rigging'),
    (6, 'Flying Trapeze', 'Trapeze, Safety Harness'),
    (9, 'Aerial Hammock', 'Hammock, Rigging');

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
    (3, 'Juggling and Acrobatics'),
    (5, 'Magic Show'),
    (8, 'Fire Dancing'),
    (10, 'Mime and Comedy');

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
    (1, '555-123-4567', 'John', 'Doe', 'Parent'),
    (2, '555-987-6543', 'Jane', 'Smith', 'Sibling'),
    (3, '555-111-2222', 'Michael', 'Johnson', 'Friend'),
    (4, '555-444-5555', 'Emily', 'Williams', 'Spouse'),
    (5, '555-777-8888', 'David', 'Brown', 'Friend'),
    (6, '555-222-3333', 'Sophia', 'Taylor', 'Parent'),
    (7, '555-999-8888', 'Daniel', 'Davis', 'Sibling'),
    (8, '555-666-7777', 'Olivia', 'Miller', 'Spouse'),
    (9, '555-333-2222', 'Alex', 'White', 'Friend'),
    (10, '555-444-3333', 'Sophie', 'Johnson', 'Parent');

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
    (1, 'Ibuprofen'),
    (1, 'Aspirin'),
    (3, 'Antihistamine'),
    (8, 'Pain Reliever'),
    (5, 'Vitamin C'),
    (6, 'Antacid'),
    (7, 'Allergy Medication'),
    (8, 'Cough Syrup'),
    (3, 'Motion Sickness Pills'),
    (10, 'Throat Lozenges');
