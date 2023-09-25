package drawing;

import java.util.Iterator;
import java.util.LinkedHashSet;

/**
 * Represents a drawing containing multiple lines and polygons.
 */
public class Drawing {

	/** A collection of lines that make up the drawing. */
	private LinkedHashSet<Line> lines;

	/**
	 * Draws a polygon using the provided lines and prints its details.
	 *
	 * @param lines The lines that make up the polygon.
	 */
	public void drawPolygon(LinkedHashSet<Line> lines){
		this.lines = lines;
		Polygon p = new Polygon(lines);
		System.out.println(p);
		System.out.printf("The perimeter of the polygon %d is %.2f: \n", Polygon.classID(), perimeter(p));
	}

	/**
	 * Calculates the perimeter of the given polygon.
	 *
	 * @param p The polygon for which the perimeter is to be calculated.
	 * @return The perimeter of the polygon.
	 */
	private double perimeter(Polygon p){
		Iterator<Line> it = p.getLine();
		double perim = 0;
		while(it.hasNext()){
			perim += it.next().distance();
		}
		return perim;
	}

	/**
	 * Main method to demonstrate the drawing of polygons.
	 *
	 * @param args Command line arguments (not used).
	 */
	public static void main(String[] args) {
		Drawing drawing = new Drawing();

		// Sample points for demonstration
		Point[] points = {
				new Point(20,30), new Point(50, 100), new Point(105, 30),
				new Point(120,130), new Point(150, 200), new Point(200, 130),
				new Point(320,330), new Point(250, 400), new Point(400, 330)
		};

		// Sample lines connecting the points
		Line[] lines = {
				new Line(points[0], points[1]),
				new Line(points[1], points[2]),
				new Line(points[2], points[0]),
				new Line(points[3], points[4]),
				new Line(points[4], points[5]),
				new Line(points[5], points[3]),
				new Line(points[6], points[7]),
				new Line(points[7], points[8]),
				new Line(points[8], points[6])
		};

		// Drawing polygons using the sample lines
		LinkedHashSet<Line> poly1 = new LinkedHashSet<Line>();
		poly1.add(lines[0]);
		poly1.add(lines[1]);
		poly1.add(lines[2]);
		drawing.drawPolygon(poly1);

		LinkedHashSet<Line> poly2 = new LinkedHashSet<Line>();
		poly2.add(lines[3]);
		poly2.add(lines[4]);
		poly2.add(lines[5]);
		drawing.drawPolygon(poly2);

		LinkedHashSet<Line> poly3 = new LinkedHashSet<Line>();
		poly3.add(lines[6]);
		poly3.add(lines[7]);
		poly3.add(lines[8]);
		drawing.drawPolygon(poly3);
	}
}
