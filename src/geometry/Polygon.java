package geometry;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

import javax.microedition.khronos.opengles.GL10;

public class Polygon {
	
	public List<Point2D> points;
	
	public Polygon() {
		this.points = new ArrayList<Point2D>();
	}	
	
	public void addPoint(Point2D point) {
		this.points.add(point);
	}
	
	public void draw(GL10 gl) {
		int index = 0;
		float[] vertexs = new float[points.size()*2];
		
		for(geometry.Point2D point:this.points){
			vertexs[index] =point.x;
			vertexs[index+1] =point.y;
			index+=2;
		}
		
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertexs.length*4);
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.put(vertexs);
		floatBuffer.position(0);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, floatBuffer);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, vertexs.length/2);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
	}
}