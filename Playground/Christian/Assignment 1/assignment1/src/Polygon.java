
import java.util.*;

class Polygon {
	private final LinkedHashSet <Line> polygon;
	private int objID;
	private static int classID;
	Iterator <Line> it;

	public Polygon(LinkedHashSet<Line> polygon) {
		 this.polygon = new LinkedHashSet<Line>();
		 for(Line l: polygon)
			 this.polygon.add (l);  
		 objID = ++ classID;
		 it = this.polygon.iterator();
	}
	
	public Iterator <Line> getLine() {
		it = polygon.iterator();
		return it;
	}
	
	public static int classID(){
		return classID;
	}
	
    
        public String toString() {
            // THIS METHOD DOESN'T WORK. AS PART OF EXERCISE-2 STUDENTS MUST FIX
            // IT TO RETURN A STRING WITH THE INFORMATION ABOUT START AND END
            // POINTS OF N LINES OF A POLYGON. AS SHOWN IN THE EXAMPLE BELOW:
            // The lines in polygon 1 are:
            //   Line 1: starts at (20, 30), and ends at (50, 100)
            //   Line 2: starts at (50, 100), and ends at (100, 30)
            //   Line 3: starts at (100, 30), and ends at (20, 30)
            //
        String s = "\nOops... Polygon's toString method doesn't know how to use "
    			+ "the Line's toString method.\n        And Line's toString"
    			+ " method doesn't know how to use Point's toString method "
    			+ "to\n        display its lines' start and end point coordinates. ";

    	return s;	
    }
}


