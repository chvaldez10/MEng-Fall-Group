import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import java.util.Arrays;

/**
 * DataLoader is an implementation of IDataLoader for loading ride data.
 */
public class DataLoader implements IDataLoader {
    /**
     * Converts a list of raw string arrays representing ride data into a list of Ride objects.
     *
     * @param rawData List of string arrays, each representing a row of ride data.
     * @return List of Ride objects created from the raw data.
     */
    public List<Ride> load(List<String[]> rawData) {
        // Skip header row and process the data
        return rawData.stream()
                .skip(1)
                .map(this::createRideFromRow)
                .collect(Collectors.toList());
    }

    /**
     * Creates a Ride object from a string array of ride data.
     *
     * @param row A string array representing a single row of ride data.
     * @return A Ride object, or null if the data could not be parsed.
     */
    private Ride createRideFromRow(String[] row) {
        try {
            String pickupDateTime = row[0];
            String dropoffDateTime = row[1];
            int passengerCount = Integer.parseInt(row[2]);
            double pickupLongitude = Double.parseDouble(row[3]);
            double pickupLatitude = Double.parseDouble(row[4]);
            double dropoffLongitude = Double.parseDouble(row[5]);
            double dropoffLatitude = Double.parseDouble(row[6]);
            int tripDuration = Integer.parseInt(row[7]);

            return new Ride(pickupDateTime, dropoffDateTime, passengerCount, pickupLongitude, pickupLatitude, dropoffLongitude, dropoffLatitude, tripDuration);
        } catch (NumberFormatException e) {
            System.err.println("Error parsing ride data: " + Arrays.toString(row));
            return null;
        }
    }
}
