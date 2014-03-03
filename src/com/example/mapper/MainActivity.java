package com.example.mapper;

import java.util.ArrayList;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.WindowManager;
import java.util.List;

import depth.DepthListener;
import file.DXF;
import file.FileListener;
import geometry.Point2D;
import geometry.Point3D;
import geometry.Polygon;
import geometry.Polyline;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.ToggleButton;

public class MainActivity extends Activity {

	public ToggleButton poligono, traslate, rotate, zoom;
	public Button openFile, depthButton;
	public TextView log;
	public GLSurfaceView glView;
	public MyRender renderer;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		this.log = (TextView) this.findViewById(R.id.textView);
		this.zoom = (ToggleButton) this.findViewById(R.id.zoom);
		this.rotate = (ToggleButton) this.findViewById(R.id.rotar);
		this.poligono = (ToggleButton) this.findViewById(R.id.poligono);
		this.traslate = (ToggleButton) this.findViewById(R.id.mover);
		this.openFile = (Button) this.findViewById(R.id.botonAbrir);
		this.depthButton = (Button) this.findViewById(R.id.levelButton);

		LinearLayout layout = (LinearLayout) findViewById(R.id.ll);

		this.renderer = new MyRender(this);
		this.glView = new SurfaceView(this);
		this.renderer.main = this;

		this.depthButton.setOnClickListener(new DepthListener(this));
		this.openFile.setOnClickListener(new FileListener(this));
		this.glView.setRenderer(this.renderer);

		layout.addView(glView);

	}

	@Override
	protected void onPause() {
		super.onPause();
		glView.onPause();
	}

	@Override
	protected void onResume() {
		super.onResume();
		glView.onResume();
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {
			String result = data.getStringExtra("result");
			for (Polyline i : DXF.getPolylines(result)) {
				this.renderer.polylines.add(i);
			}

			this.renderer.updateScale3D();
			this.glView.onResume();
			this.log.setText(String.valueOf(this.renderer.scale3d.minx));
		}
	}
}
