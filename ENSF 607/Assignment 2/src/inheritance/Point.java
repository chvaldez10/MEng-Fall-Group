package inheritance;

/**
 * The Point class represents a two-dimensional point with x and y coordinates and an associated color.
 * It provides methods for setting and getting coordinates, calculating the distance between points,
 * and generating a textual representation of the point.
 *
 */
class Point {

    /**
     * The color of the point.
     */
    private Colour colour;

    /**
     * The x-coordinate of the point.
     */
    private Double xCoordinate;

    /**
     * The y-coordinate of the point.
     */
    private Double yCoordinate;

    /**
     * Constructs a Point object with the specified coordinates and color.
     *
     * @param a The x-coordinate of the point.
     * @param b The y-coordinate of the point.
     * @param c The color of the point.
     */
    public Point(Double a, Double b, Colour c) {
        colour = c;
        xCoordinate = a;
        yCoordinate = b;
    }

    /**
     * Returns a string representation of the point, including its x and y coordinates and color.
     *
     * @return A string representation of the point.
     */
    @Override
    public String toString() {
        String s;
        s = "X-coordinate: " + xCoordinate + "\nY-coordinate: " + yCoordinate +
                "\n" + colour + " point";
        return s;
    }

    /**
     * Gets the x-coordinate of the point.
     *
     * @return The x-coordinate of the point.
     */
    public Double getx() {
        return xCoordinate;
    }

    /**
     * Sets the x-coordinate of the point to a new value.
     *
     * @param newvalue The new x-coordinate value.
     */
    public void setx(Double newvalue) {
        xCoordinate = newvalue;
    }

    /**
     * Gets the y-coordinate of the point.
     *
     * @return The y-coordinate of the point.
     */
    public Double gety() {
        return yCoordinate;
    }

    /**
     * Sets the y-coordinate of the point to a new value.
     *
     * @param newvalue The new y-coordinate value.
     */
    public void sety(Double newvalue) {
        yCoordinate = newvalue;
    }

    /**
     * Calculates the Euclidean distance between this point and another point.
     *
     * @param other The other point.
     * @return The Euclidean distance between this point and the other point.
     */
    public Double distance(Point other) {
        Double dist_x = other.xCoordinate - xCoordinate;
        Double dist_y = other.yCoordinate - yCoordinate;
        return Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));
    }

    /**
     * Calculates the Euclidean distance between two points.
     *
     * @param that The first point.
     * @param other The second point.
     * @return The Euclidean distance between the two points.
     */
    public static Double distance(Point that, Point other) {
        Double dist_x = other.xCoordinate - that.xCoordinate;
        Double dist_y = other.yCoordinate - that.yCoordinate;
        return Math.sqrt(Math.pow(dist_x, 2) + Math.pow(dist_y, 2));
    }
}
