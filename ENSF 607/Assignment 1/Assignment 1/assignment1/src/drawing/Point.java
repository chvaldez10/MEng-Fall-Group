package drawing;

/**
 * Represents a 2D point with x and y coordinates.
 */
class Point {

	/** The x-coordinate of the point. */
	private int x;

	/** The y-coordinate of the point. */
	private int y;

	/**
	 * Constructs a new Point with the specified x and y coordinates.
	 *
	 * @param x The x-coordinate of the point.
	 * @param y The y-coordinate of the point.
	 */
	public Point(int x, int y) {
		this.x = x;
		this.y = y;
	}

	/**
	 * Calculates and returns the Euclidean distance between two points.
	 *
	 * @param a The first point.
	 * @param b The second point.
	 * @return The distance between the two points.
	 */
	static public double distance(Point a, Point b) {
		double diffx = a.x - b.x;
		double diffy = a.y - b.y;
		return Math.sqrt(diffx * diffx + diffy * diffy);
	}

	/**
	 * Returns a string representation of the point.
	 *
	 * @return A string representation of the point in the format "(x, y)".
	 */
	@Override
	public String toString() {
		String s = "(" + this.x + ", " + this.y + ")";
		return s;
	}
}
