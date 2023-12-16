import java.util.List;

/**
 * DataAnalyzer implements IDataAnalyzer to provide analysis of ride data.
 */
public class DataAnalyzer implements IDataAnalyzer {
    /**
     * Calculates the average trip duration from a list of rides.
     *
     * @param rides a list of Ride objects
     * @return the average trip duration as a double. If there are no rides, returns 0.0
     */
    public double averageTripDuration(List<Ride> rides) {
        if (rides.isEmpty()) return 0.0;

        double totalDuration = 0.0;
        for (Ride ride : rides) {
            totalDuration += ride.getTripDuration();
        }
        return totalDuration / rides.size();
    }
}
