import java.util.List;

/**
 * The IDataLoader interface defines a contract for loading ride data.
 */
public interface IDataLoader {
    /**
     * Loads ride data from a list of string arrays.
     *
     * @param rawData a list of string arrays, each representing raw ride data
     * @return a list of Ride objects constructed from the raw data
     */
    List<Ride> load(List<String[]> rawData);
}
