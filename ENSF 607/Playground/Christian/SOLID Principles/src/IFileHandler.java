import java.util.List;
import java.io.IOException;

/**
 * Interface provides a method to read data from a CSV file.
 */
public interface IFileHandler {
    /**
     * Reads a CSV file and returns its content as a list of string arrays.
     * Each line in the CSV file is represented as an array of strings, where each string is a field.
     *
     * @param filePath the path to the CSV file
     * @return a list of string arrays, each representing a line in the CSV file
     * @throws IOException if an I/O error occurs while reading the file
     */
    List<String[]> readCsv(String filePath) throws IOException;
}
