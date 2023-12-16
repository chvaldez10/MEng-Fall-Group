import java.util.List;

/**
 * IDataAnalyzer provides a set of methods for analyzing ride data.
 */
public interface IDataAnalyzer {
    /**
     * Calculates the average trip duration from a list of rides.
     *
     * @param rides a list of Ride objects
     * @return the average trip duration as a double
     */
    double averageTripDuration(List<Ride> rides);
}
