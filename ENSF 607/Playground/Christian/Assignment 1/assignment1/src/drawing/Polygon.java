package drawing;

import java.util.*;

/**
 * Represents a polygon defined by a set of lines.
 */
class Polygon {

	/** The lines that make up the polygon. */
	private final LinkedHashSet<Line> polygon;

	/** Unique identifier for each instance of the Polygon class. */
	private int objID;

	/** Static counter to generate unique identifiers for Polygon instances. */
	private static int classID;

	/** Iterator for iterating over the lines in the polygon. */
	private Iterator<Line> it;

	/**
	 * Constructs a new Polygon using the provided set of lines.
	 *
	 * @param polygon The lines that make up the polygon.
	 */
	public Polygon(LinkedHashSet<Line> polygon) {
		this.polygon = new LinkedHashSet<Line>();
		for (Line l : polygon) {
			this.polygon.add(l);
		}
		objID = ++classID;
		it = this.polygon.iterator();
	}

	/**
	 * Returns an iterator over the lines in the polygon.
	 *
	 * @return An iterator over the lines in the polygon.
	 */
	public Iterator<Line> getLine() {
		it = polygon.iterator();
		return it;
	}

	/**
	 * Returns the current value of the static class ID counter.
	 *
	 * @return The current value of the class ID counter.
	 */
	public static int classID() {
		return classID;
	}

	/**
	 * Returns a string representation of the polygon.
	 *
	 * @return A string representation of the polygon, listing its lines.
	 */
	@Override
	public String toString() {
		String s = "The lines in polygon " + this.objID + " are:";

		for (Line l : polygon) {
			s += l;
		}

		return s;
	}
}
