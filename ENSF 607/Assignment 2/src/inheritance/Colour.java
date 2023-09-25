package inheritance;

/**
 * The Colour class represents a color. It encapsulates the color as a string and provides
 * methods for setting and retrieving the color value, as well as a method for obtaining
 * a string representation of the color.
 *
 */
class Colour {

    /**
     * The color value represented as a string.
     */
    private String colour;

    /**
     * Constructs a Colour object with the specified color value.
     *
     * @param s The color value as a string.
     */
    public Colour(String s) {
        colour = s;
    }

    /**
     * Sets the color value to a new value.
     *
     * @param newColour The new color value to set.
     */
    public void setColour(String newColour) {
        colour = newColour;
    }

    /**
     * Returns the color value as a string.
     *
     * @return The color value as a string.
     */
    @Override
    public String toString() {
        return colour;
    }
}
