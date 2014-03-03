package com.example.mapper;

import android.content.Context;
import android.opengl.GLSurfaceView.Renderer;
import android.opengl.GLU;
import geometry.Polyline;
import geometry.Polygon;
import geometry.Scale3D;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;
import java.util.ArrayList;
import java.util.List;

public class MyRender implements Renderer {
	
	public Scale3D scale3d;
	public Context context;
	public List<Polyline> polylines;
	public List<Polygon> polygons;
	public MainActivity main;
	public float ancho,largo;

	public MyRender(Context context) {
		
		this.polygons=new ArrayList<Polygon>();
		this.polylines=new ArrayList<Polyline>();
		this.scale3d = new Scale3D();
		this.context = context;
		
	}
	
	public void updateScale3D() {
		for(Polyline polyline:this.polylines){
			for(geometry.Point3D point:polyline.points){	
				this.scale3d.update(point);	
			}
		}
	}

	@Override
	public void onSurfaceCreated(GL10 gl, EGLConfig config) {
		
	   /*
		* 1.-Habilita el sombreado suave.
		* 2.-Configura el buffer de profundidad.
		* 3.-Habilita el testeo de profundidad.
		* 4.-El tipo de testeo de profundidad a hacer.
		* 5.-Calculo de perspectivas.
		*/		
		gl.glClearColor(0.0f, 0.0f, 0.0f, 0.5f);
		gl.glShadeModel(GL10.GL_SMOOTH);
		gl.glClearDepthf(1.0f);
		gl.glEnable(GL10.GL_DEPTH_TEST);
		gl.glDepthFunc(GL10.GL_LEQUAL);
		gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_NICEST);
		 
	}

	@Override
	public void onSurfaceChanged(GL10 gl, int width, int height) {
	   this.ancho=width;
	   this.largo=height;
		/* 
	   * 1.-Establece el puerto de vista actual al nuevo tamaño.
	   * 2.-Selecciona la matriz de proyeccion.
	   * 3.-Reinicia la matriz de proyeccion.
	   * 4.-Calcula la proporcion del aspecto de la ventana.
	   * 5.-Selecciona la matriz de la vista del modelo.
	   * 6.-Reinicia la matriz de la vista del modelo.
	   */
		gl.glViewport(0, 0, width, height);
		gl.glMatrixMode(GL10.GL_PROJECTION);
		gl.glLoadIdentity();
		gl.glOrthof(-1.0f, 1.0f, 1.0f, -1.0f, -10.0f, 10.0f);
		gl.glMatrixMode(GL10.GL_MODELVIEW);
	
		gl.glLoadIdentity();

	}

	public void onDrawFrame(GL10 gl) {
		
	   /*
		* 1.-Limpia la pantalla y el buffer de profundidad.
		* 2.-Reemplaza la matriz actual con la matriz identidad.
		* 3.-Traslada 4 unidades en el eje Z.
		*/
		
	
		gl.glClearColor(0.2f, 0.2f, 0.2f, 1.0f);
		gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
		gl.glLoadIdentity();
		gl.glTranslatef(0, 0, -4);
		
		float[] vertexs = {-1f,1,0f,0f};
	
		ByteBuffer byteBuffer = ByteBuffer.allocateDirect(vertexs.length*4);
		byteBuffer.order(ByteOrder.nativeOrder());
		FloatBuffer floatBuffer = byteBuffer.asFloatBuffer();
		floatBuffer.put(vertexs);
		floatBuffer.position(0);
		
		gl.glEnableClientState(GL10.GL_VERTEX_ARRAY);
		gl.glVertexPointer(2, GL10.GL_FLOAT, 0, floatBuffer);
		gl.glDrawArrays(GL10.GL_LINE_STRIP, 0, vertexs.length/2);
		gl.glDisableClientState(GL10.GL_VERTEX_ARRAY);
		
		
		
		
		for(Polyline poliline:this.polylines){
			poliline.draw(gl, scale3d);			
		}
		
		for(Polygon poligon:this.polygons)
			poligon.draw(gl);

	}
}
