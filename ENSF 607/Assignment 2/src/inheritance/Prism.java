package inheritance;

/**
 * The Prism class represents a three-dimensional prism shape that extends properties
 * from the Rectangle class. It includes methods for setting and getting the height,
 * calculating the area, perimeter, and volume, and providing a textual representation
 * of the prism.
 *
 */
class Prism extends Rectangle {

    /**
     * The height of the prism.
     */
    private Double height;

    /**
     * Constructs a Prism object with the specified parameters.
     *
     * @param x The x-coordinate of the prism's origin.
     * @param y The y-coordinate of the prism's origin.
     * @param l The length of the prism's base.
     * @param w The width of the prism's base.
     * @param h The height of the prism.
     * @param name The name of the prism.
     * @param colour The color of the prism.
     */
    public Prism(Double x, Double y, Double l, Double w, Double h, String name, Colour colour) {
        super(x, y, l, w, name, colour);
        height = h;
    }

    /**
     * Sets the height of the prism.
     *
     * @param h The new height value to set.
     */
    public void set_height(Double h) {
        height = h;
    }

    /**
     * Gets the height of the prism.
     *
     * @return The height of the prism.
     */
    public Double height() {
        return height;
    }

    /**
     * Calculates and returns the surface area of the prism.
     *
     * @return The surface area of the prism.
     */
    public Double area() {
        return 2 * (length * width) + 2 * (height * length) + 2 * (height * width);
    }

    /**
     * Calculates and returns the perimeter of the prism (perimeter of the base).
     *
     * @return The perimeter of the prism.
     */
    public Double perimeter() {
        return width * 2 + length * 2;
    }

    /**
     * Calculates and returns the volume of the prism.
     *
     * @return The volume of the prism.
     */
    public Double volume() {
        return width * length * height;
    }

    /**
     * Returns a string representation of the prism, including its origin, name, color,
     * base dimensions, and height.
     *
     * @return A string representation of the prism.
     */
    public String toString() {
        String s = super.toString() + "\nHeight: " + height;
        return s;
    }
}
