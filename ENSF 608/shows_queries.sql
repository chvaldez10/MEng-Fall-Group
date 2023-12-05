-- shows_queries.sql
-- ENSF608 Final Project
-- Group Members:
-- Redge Santillan
-- Chioma Ukaegbu
-- Christian Valdez

-- 1. Show all tables and explain how they are related to one another
SHOW TABLES;

-- 2. A basic retrieval query
SELECT * FROM Shows;

-- 3. A retrieval query with ordered results
SELECT * FROM Shows ORDER BY PerformanceYear DESC;

-- 4. A nested retrieval query
SELECT * FROM Performers WHERE PerformerID IN (SELECT PerformerID FROM Aerialist WHERE Sport LIKE 'Aerial%');

-- 5. A retrieval query using joined tables
SELECT Shows.Name, Venues.Name AS VenueName, hostedby.ShowDate
FROM Shows
JOIN hostedby ON Shows.Name = hostedby.ShowName AND Shows.PerformanceYear = hostedby.ShowPerformanceYear
JOIN Venues ON hostedby.VenueID = Venues.VenueID;

-- 6. An update operation with any necessary triggers
-- Trigger to prevent updating ShowDate to the past
DROP TRIGGER IF EXISTS before_showvenue_update;
DELIMITER //
CREATE TRIGGER before_showvenue_update
BEFORE UPDATE ON hostedby
FOR EACH ROW
BEGIN
    IF NEW.ShowDate < CURDATE() THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Invalid show date';
    END IF;
END;
//
DELIMITER ;

-- Example update operation
UPDATE hostedby
SET ShowDate = '2023-12-06'		-- change this date either before or after the current date
WHERE ShowName = 'Alegria' AND ShowPerformanceYear = 2006 AND VenueID = 1;

-- 7. A deletion operation with any necessary triggers
-- Drop the trigger if it exists
DROP TRIGGER IF EXISTS before_show_delete;
DELIMITER //
CREATE TRIGGER before_show_delete
BEFORE DELETE ON Shows
FOR EACH ROW
BEGIN
    -- Check if the show being deleted is sponsored by a specific sponsor
    IF EXISTS (
        SELECT 1
        FROM Shows
        WHERE Shows.Sponsor = 'Cirque du Soleil'
    ) THEN
        SIGNAL SQLSTATE '45000'
            SET MESSAGE_TEXT = 'Cannot delete show sponsored by a specific sponsor';
    END IF;
END;
//
DELIMITER ;

-- Example delete operation that should be prevented by the trigger
DELETE FROM Shows
WHERE Name = 'Alegria' AND PerformanceYear = 2006 AND Sponsor = 'Cirque du Soleil';



