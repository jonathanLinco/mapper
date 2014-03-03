package com.example.mapper;

import geometry.Point2D;
import geometry.Polygon;
import rotate.Rotate;
import traslate.Traslate;
import zoom.Zoom;
import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.OnTouchListener;

public class SurfaceView extends GLSurfaceView implements OnTouchListener {

	private MainActivity mainActivity;
	private Traslate traslate;
	private Rotate rotate;
	private Zoom zoom;
	private Polygon poligono;

	public SurfaceView(Context context) {
		super(context);
		this.mainActivity = (MainActivity) context;
		this.traslate = new Traslate(this.mainActivity);
		this.rotate = new Rotate(this.mainActivity);
		this.zoom = new Zoom(this.mainActivity);
		this.setOnTouchListener((OnTouchListener) this);
	}

	
	public boolean onTouch(View view, MotionEvent event) {

		
		float x = event.getX(), y = event.getY();
		float posX =((2*x / (float)this.mainActivity.renderer.ancho) ) - 1;
		float posY = ((2 *y/ (float)this.mainActivity.renderer.largo)) - 1;
		
			
		String coordenadas="coordenadas pantalla------> x: "+x+"  y: "+y+"\n"+
		"coordenadas OpenGl--------> x: "+posX+"  y: +"+posY;
		this.mainActivity.log.setText(coordenadas);
		
		
		if (this.mainActivity.poligono.isChecked() && event.getAction()==MotionEvent.ACTION_UP) {
			poligono.addPoint(new Point2D(posX,posY));  /*<----------se agrega el punto al poligono*/
			this.mainActivity.glView.onResume();
			
		} else if (this.mainActivity.traslate.isChecked()) {
			this.traslate.event(event);
		} else if (this.mainActivity.zoom.isChecked()) {
			this.zoom.event(event);
		} else if (this.mainActivity.rotate.isChecked()) {
			this.rotate.event(event);
		}
		return true;
	}

}
