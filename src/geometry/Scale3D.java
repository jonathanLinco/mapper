package geometry;

public class Scale3D {
	
	public float minx, maxx;
	public float miny, maxy;
	public float minz, maxz;
	
	public Scale3D() {
		this.minx = Float.MAX_VALUE;
		this.maxx = Float.MIN_VALUE;
		this.miny = Float.MAX_VALUE;
		this.maxy = Float.MIN_VALUE;
		this.minz = Float.MAX_VALUE;
		this.maxz = Float.MIN_VALUE;
	}
	
	public Scale3D(float minx, float maxx, float miny, 
			float maxy, float minz, float maxz) {
		
		this.minx = minx;
		this.maxx = maxx;
		this.miny = miny;
		this.maxy = maxy;
		this.minz = minz;
		this.maxz = maxz;
	}
	
	public void update(Point3D point) {
		if (point.x < this.minx) {this.minx = point.x;}
		if (point.x > this.maxx) {this.maxx = point.x;}
		if (point.y < this.miny) {this.miny = point.y;}
		if (point.y > this.maxy) {this.maxy = point.y;}
		if (point.z < this.minz) {this.minz = point.z;}
		if (point.z > this.maxz) {this.maxz = point.z;}
	}
}