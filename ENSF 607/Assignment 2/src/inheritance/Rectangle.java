package inheritance;

/**
 * The Rectangle class represents a two-dimensional rectangular shape that extends properties
 * from the Shape class. It includes methods for setting and getting the length and width,
 * calculating the area, perimeter, and providing a textual representation of the rectangle.
 *
 */
public class Rectangle extends Shape {

    /**
     * The width of the rectangle.
     */
    protected Double width;

    /**
     * The length of the rectangle.
     */
    protected Double length;

    /**
     * Constructs a Rectangle object with the specified parameters.
     *
     * @param x_origin The x-coordinate of the rectangle's origin.
     * @param y_origin The y-coordinate of the rectangle's origin.
     * @param newlength The length of the rectangle.
     * @param newwidth The width of the rectangle.
     * @param name The name of the rectangle.
     * @param colour The color of the rectangle.
     */
    public Rectangle(Double x_origin, Double y_origin, Double newlength, Double newwidth, String name, Colour colour) {
        super(x_origin, y_origin, name, colour);
        length = newlength;
        width = newwidth;
    }

    /**
     * Sets the length of the rectangle.
     *
     * @param newlength The new length value to set.
     */
    protected void set_length(Double newlength) {
        length = newlength;
    }

    /**
     * Gets the length of the rectangle.
     *
     * @return The length of the rectangle.
     */
    protected Double get_length() {
        return length;
    }

    /**
     * Calculates and returns the area of the rectangle.
     *
     * @return The area of the rectangle.
     */
    protected Double area() {
        return width * length;
    }

    /**
     * Calculates and returns the perimeter of the rectangle.
     *
     * @return The perimeter of the rectangle.
     */
    protected Double perimeter() {
        return width * 2 + length * 2;
    }

    /**
     * Returns a string representation of the rectangle, including its origin, name, color,
     * width, and length.
     *
     * @return A string representation of the rectangle.
     */
    @Override
    public String toString() {
        String s = super.toString() + "\nWidth: " + width + "\nLength: " + length;
        return s;
    }

    /**
     * Calculates and returns the volume of the rectangle (which is always 0 for a 2D shape).
     *
     * @return The volume of the rectangle.
     */
    protected Double volume() {
        return 0.0;
    }
}
