package com.example.mapper;

import android.content.Context;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class SurfaceView extends GLSurfaceView implements OnTouchListener {

	private int MAX_ZOOM = 20;
	
	private Point midPoint;
	private Point p1, p2;
	
//	private float[] x = new float[2];
//	private float[] y = new float[2];
//	private float[] ptoMedio = new float[2];
	private double camera = 100, distanciaAnterior = 0;
	private MainActivity mainActivity;
	private StringBuilder builder = new StringBuilder();

	public SurfaceView(Context context) {
		super(context);

		this.mainActivity = (MainActivity) context;
		this.setOnTouchListener((OnTouchListener) this);
	}

	public boolean onTouch(View view, MotionEvent event) {
//		int pointerCount = ;
		if (this.mainActivity.poligono.isChecked()) {
			this.mainActivity.textView.setText("poligono x:" + event.getX() + " y:" + event.getY());
		} else {
			if (event.getPointerCount() == 2) {
				p1 = new Point(event.getX(0), event.getY(0));
				p2 = new Point(event.getX(1), event.getY(1));
				
				
//				for (int pointerIndex = 0; pointerIndex < 2; pointerIndex++) {
//					x[pointerIndex] = (int) event.getX(pointerIndex);
//					y[pointerIndex] = (int) event.getY(pointerIndex);
//				}
				updateTextView();

			}
		}
		return true;
	}

	protected void updateTextView() {
		builder.setLength(0);
		float distancia = p1.distanceEuclidean(p2);
		float dist = (float) (distanciaAnterior - distancia);
		dist = dist < -MAX_ZOOM ? -MAX_ZOOM : dist > MAX_ZOOM ? MAX_ZOOM : dist;

		camera += dist / 5;
		camera = camera < 0 ? 0 : camera > 100 ? 100 : camera;

		midPoint = p1.middlePoint(p2);
//		ptoMedio[0] = ;
//		ptoMedio[1] = ;
		builder.append("Camera = " + camera + "\n");
		builder.append("Pto Medio = " + midPoint.x + " x " + midPoint.y);
		distanciaAnterior = distancia;
		this.mainActivity.textView.setText(builder.toString());
	}
}