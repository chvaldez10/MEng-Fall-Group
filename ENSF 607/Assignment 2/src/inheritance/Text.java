package inheritance;

/**
 * The Text class represents a simple text string. It provides methods for setting and getting
 * the text content, as well as a textual representation of the text object.
 *
 */
public class Text {

    /**
     * The text content of the Text object.
     */
    private String text;

    /**
     * Constructs a Text object with the specified text content.
     *
     * @param text The text content to set for the Text object.
     */
    public Text(String text) {
        this.text = text;
    }

    /**
     * Sets the text content of the Text object to a new value.
     *
     * @param newText The new text content to set.
     */
    public void setText(String newText) {
        text = newText;
    }

    /**
     * Gets the current text content of the Text object.
     *
     * @return The current text content of the Text object.
     */
    public String getText() {
        return text;
    }

    /**
     * Returns a string representation of the Text object, which is its text content.
     *
     * @return The text content of the Text object as a string.
     */
    @Override
    public String toString() {
        return text;
    }
}

