package inheritance;

/**
 * The Circle class represents a two-dimensional circle shape that inherits properties
 * from the Shape class. It includes methods for setting and getting the radius, calculating
 * the area and perimeter, and providing a textual representation of the circle.
 *
 */
class Circle extends Shape {

    /**
     * The radius of the circle.
     */
    private Double radius;

    /**
     * Constructs a Circle object with the specified parameters.
     *
     * @param x_origin The x-coordinate of the circle's origin.
     * @param y_origin The y-coordinate of the circle's origin.
     * @param newradius The radius of the circle.
     * @param name The name of the circle.
     * @param colour The color of the circle.
     */
    Circle(Double x_origin, Double y_origin, Double newradius, String name, Colour colour) {
        super(x_origin, y_origin, name, colour);
        radius = newradius;
    }

    /**
     * Sets the radius of the circle.
     *
     * @param newradius The new radius value to set.
     */
    public void set_radius(Double newradius) {
        radius = newradius;
    }

    /**
     * Gets the radius of the circle.
     *
     * @return The radius of the circle.
     */
    public Double get_radius() {
        return radius;
    }

    /**
     * Calculates and returns the area of the circle.
     *
     * @return The area of the circle.
     */
    public Double area() {
        return Math.PI * Math.pow(radius, 2);
    }

    /**
     * Calculates and returns the perimeter (circumference) of the circle.
     *
     * @return The perimeter of the circle.
     */
    public Double perimeter() {
        return 2 * Math.PI * radius;
    }

    /**
     * Calculates and returns the volume of the circle (always 0 for 2D shapes).
     *
     * @return The volume of the circle (always 0 for 2D shapes).
     */
    public Double volume() {
        return 0.0;
    }

    /**
     * Returns a string representation of the circle, including its origin, name, color,
     * and radius.
     *
     * @return A string representation of the circle.
     */
    public String toString() {
        String s = super.toString() + "\nRadius: " + radius;
        return s;
    }
}
