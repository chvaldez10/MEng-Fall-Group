
class Point {
	private int x, y;

	public Point(int x, int y) {
	this.x = x;
	this.y = y;
	}
	
	static public double distance(Point a, Point b){
		double diffx = a.x - b.x;
		double diffy = a.y - b.y;
		return Math.sqrt(diffx * diffx + diffy * diffy);
	}
	
	public String toString(){
		String s = "(" + this.x + ", " + this.y + ")";
		return  s;
	}
}
