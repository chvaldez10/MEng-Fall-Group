package drawing;
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
    
    public String toString() {
		String s = "\n  Line " + this.objID +": starts at" + this.start + ", and ends at " + this.end;
		return s;
    }
}
