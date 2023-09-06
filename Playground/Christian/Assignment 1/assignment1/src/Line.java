
class Line {

	Point start, end;
	private static int classID = 0;
	private int objID;

	public Line(Point a, Point b) {
	start = a;
	end = b; 
	 objID = ++ classID;
	}
	
   
	public double distance(){
	    return Point.distance(start, end);
    }
    
    public String toString()
    {
        // THIS METHOD DOESN'T WORK. AS PART OF EXERCISE-2 STUDENTS MUST FIX IT
        // TO RETURN A STRING WITH TWO COORDINATES OF THE START AND END POINTS
        // OF A LINE, IN THE FORMAT SHOWN IN THE EXAMPLE BELOW:
        // Line 1: starts at (20, 30), and ends at (50, 100)
        //
    	String s = "Oops... Line's toString doesn't know how to use Line's toString to display"
    			+ " the start and end point of a line coordinates...";
    	return s;
    }
}
