package inheritance;
import java.util.Iterator;
import java.util.TreeSet;
/**
 * The Geometry class represents a collection of geometric shapes stored in a TreeSet.
 * It provides methods to add shapes to the collection, display information about the
 * shapes, and calculate and display their area, perimeter, and volume.
 *
 * <p>This class serves as a demonstration of how to work with geometric shapes and
 * provides sample usage of the Shape, Rectangle, Circle, Prism, and Colour classes.
 *
 */
public class Geometry {
    
    /**
     * A TreeSet containing geometric shapes.
     */
    private TreeSet<Shape> shapes;

    /**
     * Constructs a Geometry object with an empty TreeSet to store shapes.
     */
    public Geometry() {
        shapes = new TreeSet<>();
    }

    /**
     * Adds a shape to the collection.
     *
     * @param shape The shape to be added to the collection.
     */
    public void add(Shape shape) {
        shapes.add(shape);
    }

    /**
     * Displays information about all shapes in the collection.
     */
    public void showAll() {
        Iterator<Shape> it = shapes.iterator();
        while (it.hasNext()) {
            System.out.println(it.next());
        }
    }

    /**
     * Calculates and displays the area, perimeter, and volume (if applicable) of a shape.
     *
     * @param shape The shape for which to calculate and display measurements.
     */
    public void calculator(Shape shape) {
        String area = String.format("%.2f", shape.area());
        String perimeter = String.format("%.2f", shape.perimeter());
        String volume = String.format("%.2f", shape.volume());

        System.out.println("The area, perimeter, and volume of " + shape.getName() + " are: " +
                area + ", " + perimeter + ", " + volume + ".");
    }

    /**
     * The main method demonstrates the usage of geometric shapes by creating instances
     * of Rectangle, Circle, and Prism objects, adding them to the Geometry collection,
     * and displaying information and measurements of the shapes.
     *
     * @param args The command-line arguments (not used).
     */
    
	public static void main(String[] args) {
		Rectangle r1 = new Rectangle(3.0, 4.0, 5.0, 6.0, "R1", new Colour("Black"));
        Circle c1 = new Circle (13.0, 14.0, 15.0, "C1",new Colour ("Green"));
        System.out.println(r1);
        System.out.println(c1);
        
		Rectangle r2 = new Rectangle(23.0, 24.0, 25.0, 26.0, "R2", new Colour("Black"));
        Circle c2 = new Circle (33.0, 34.0, 35.0, "C2", new Colour("Yellow"));
        System.out.println(r2);
        System.out.println(c2);
        
		Prism p1 = new Prism(43.0, 44.0, 45.0, 46.0, 47.0, "P1", new Colour("White"));
        Prism p2 = new Prism (53.0, 54.0, 55.0, 56.0, 57.0, "P2", new Colour("Gray"));
        System.out.println(p1);
        System.out.println(p2);
        
        
        //      SECTION 2: THE FOLLOWING CODE SEGMENT MUST BE UNCOMMENTED ONLY
        //      FOR EXERCISE 2
        
         Geometry demo = new Geometry();
         System.out.println("\nAdding Rectangle, Circle, and Prism objects to the list... ");
         demo.add(r1);
         demo.add(r2);
         demo.add(c1);
         demo.add(c2);
         demo.add(p1);
         demo.add(p2);
         
         System.out.println("\nShowing information about objects added to the list:");
         demo.showAll();
         
         System.out.println("\nShowing area, perimeter, and volume of objects in the list:");
         
         Iterator <Shape> it = demo.shapes.iterator();


         while(it.hasNext()){

            demo.calculator(it.next());
         }
         
         

	}
}
