import java.util.List;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * FileHandler is an implementation of the IFileHandler interface, providing methods to handle file operations.
 */
public class FileHandler implements IFileHandler {
    /**
     * Reads a CSV file and returns its content as a list of string arrays.
     * Each line in the CSV file is split by commas and represented as an array of strings.
     *
     * @param filePath the path to the CSV file
     * @return a list of string arrays, each representing a line in the CSV file
     * @throws IOException if an I/O error occurs while reading the file
     */
    public List<String[]> readCsv(String filePath) throws IOException {
        List<String[]> data = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(filePath))) {
            String line;
            while ((line = br.readLine()) != null) {
                data.add(line.split(","));
            }
        }
        return data;
    }
}
