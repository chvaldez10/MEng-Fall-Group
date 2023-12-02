-- Show all tables and explain how they are related to one another
SHOW TABLES;

-- A basic retrieval query
SELECT * FROM Shows;

-- A retrieval query with ordered results
SELECT * FROM Shows ORDER BY PerformanceYear DESC;

-- A nested retrieval query
SELECT * FROM Performers WHERE PerformerID IN (SELECT PerformerID FROM Aerialist WHERE Sport LIKE 'Aerial%');

-- A retrieval query using joined tables
SELECT Shows.Name, Venues.Name AS VenueName, hostedby.ShowDate
FROM Shows
JOIN hostedby ON Shows.Name = hostedby.ShowName AND Shows.PerformanceYear = hostedby.ShowPerformanceYear
JOIN Venues ON hostedby.VenueID = Venues.VenueID;

-- An update operation with any necessary triggers
-- Trigger to prevent updating ShowDate to the past
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
SET ShowDate = '2023-12-02'
WHERE ShowName = 'Alegria' AND ShowPerformanceYear = 2006 AND VenueID = 1;

-- A deletion operation with any necessary triggers
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



