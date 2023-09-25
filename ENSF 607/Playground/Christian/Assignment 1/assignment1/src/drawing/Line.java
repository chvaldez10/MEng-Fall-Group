package drawing;

/**
 * Represents a line segment defined by two points.
 */
class Line {

	/** The starting point of the line segment. */
	Point start;

	/** The ending point of the line segment. */
	Point end;

	/** Static counter to generate unique identifiers for Line instances. */
	private static int classID = 0;

	/** Unique identifier for each instance of the Line class. */
	private int objID;

	/**
	 * Constructs a new Line segment using the provided start and end points.
	 *
	 * @param a The starting point of the line segment.
	 * @param b The ending point of the line segment.
	 */
	public Line(Point a, Point b) {
		start = a;
		end = b;
		objID = ++classID;
	}

	/**
	 * Calculates and returns the distance between the start and end points of the line segment.
	 *
	 * @return The distance between the start and end points.
	 */
	public double distance() {
		return Point.distance(start, end);
	}

	/**
	 * Returns a string representation of the line segment.
	 *
	 * @return A string representation of the line segment, showing its start and end points.
	 */
	@Override
	public String toString() {
		String s = "\n  Line " + this.objID + ": starts at" + this.start + ", and ends at " + this.end;
		return s;
	}
}
