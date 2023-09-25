package inheritance;

/**
 * The Shape class is an abstract base class that represents a geometric shape in a two-dimensional space.
 * It includes properties such as the shape's origin, name, and methods for calculating area, perimeter,
 * volume, and measuring the distance between shapes. It also implements the Comparable interface to enable
 * sorting of shapes based on their names.
 *
 */
abstract class Shape implements Comparable<Shape> {

    /**
     * The origin point of the shape.
     */
    protected Point origin;

    /**
     * The name of the shape.
     */
    protected Text name;

    /**
     * Constructs a Shape object with the specified parameters.
     *
     * @param x_origin The x-coordinate of the shape's origin.
     * @param y_origin The y-coordinate of the shape's origin.
     * @param name The name of the shape.
     * @param colour The color of the shape.
     */
    protected Shape(Double x_origin, Double y_origin, String name, Colour colour) {
        origin = new Point(x_origin, y_origin, colour);
        this.name = new Text(name);
    }

    /**
     * Gets the origin point of the shape.
     *
     * @return The origin point of the shape.
     */
    protected Point getOrigin() {
        return origin;
    }

    /**
     * Gets the name of the shape.
     *
     * @return The name of the shape.
     */
    protected String getName() {
        return name.getText();
    }

    /**
     * Calculates and returns the area of the shape.
     *
     * @return The area of the shape.
     */
    abstract protected Double area();

    /**
     * Calculates and returns the perimeter of the shape.
     *
     * @return The perimeter of the shape.
     */
    abstract protected Double perimeter();

    /**
     * Calculates and returns the volume of the shape.
     *
     * @return The volume of the shape.
     */
    abstract protected Double volume();

    /**
     * Calculates and returns the Euclidean distance between this shape and another shape.
     *
     * @param other The other shape.
     * @return The Euclidean distance between this shape and the other shape.
     */
    protected Double distance(Shape other) {
        return origin.distance(other.origin);
    }

    /**
     * Calculates and returns the Euclidean distance between two shapes.
     *
     * @param a The first shape.
     * @param b The second shape.
     * @return The Euclidean distance between the two shapes.
     */
    protected Double distance(Shape a, Shape b) {
        return Point.distance(a.origin, b.origin);
    }

    /**
     * Moves the shape by a specified displacement along the x and y axes.
     *
     * @param dx The displacement along the x-axis.
     * @param dy The displacement along the y-axis.
     */
    protected void move(Double dx, Double dy) {
        origin.setx(origin.getx() + dx);
        origin.sety(origin.gety() + dy);
    }

    /**
     * Compares this shape to another shape based on their names.
     *
     * @param other The other shape to compare.
     * @return A negative integer, zero, or a positive integer if this shape's name is less than,
     *         equal to, or greater than the other shape's name, respectively.
     */
    @Override
    public int compareTo(Shape other) {
        return this.getName().compareTo(other.getName());
    }

    /**
     * Returns a string representation of the shape, including its name and origin point.
     *
     * @return A string representation of the shape.
     */
    @Override
    public String toString() {
        String s = "\nShape name: " + name + "\nOrigin: " + origin;
        return s;
    }
}




