package geometry;

public class Point2D {
	
	public float x;
	public float y;
	
	public Point2D(float x, float y) {
		this.x = x;
		this.y = y;
	}
	
	public float distanceEuclidean(Point2D p) {
		return (float) Math.sqrt(Math.pow(x - p.x, 2) + Math.pow(y - p.y, 2));
	}
	
	public Point2D middlePoint(Point2D p) {
		return new Point2D(((p.x - x) / 2) + x, ((p.y - y) / 2) + y);
	}
	
	public float angle(Point2D p){
		return (float) ((float) Math.atan2((p.y-y),(p.x-x))*-(180/Math.PI));
	}
	
	public int distanceX(Point2D p){
		return (int) (p.x-x);
	}
	
	public int distanceY(Point2D p){
		return (int) (p.y-y);
	}
}