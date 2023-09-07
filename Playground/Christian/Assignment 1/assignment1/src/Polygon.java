
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
		String s = "The lines in polygon " + this.objID + " are:";

		for (Line l : polygon) {
			s += l;
		}

    	return s;
    }
}


