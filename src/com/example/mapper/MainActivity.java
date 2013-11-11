package com.example.mapper;

import fileChose.ListFileActivity;
import android.opengl.GLSurfaceView;
import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
//import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
//import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class MainActivity extends Activity implements OnClickListener{

	Button abrir;
	TextView textView;
	ToggleButton poligono;
	GLSurfaceView glView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		abrir = (Button) this.findViewById(R.id.botonAbrir);
		poligono = (ToggleButton) this.findViewById(R.id.poligono);
		textView = (TextView) this.findViewById(R.id.textView);
		textView.setText("Toca y arrastra");
		glView = new SurfaceView(this);
		glView.setRenderer(new MyRender(this));
		abrir.setOnClickListener(this);
		LinearLayout layout = (LinearLayout) findViewById(R.id.ll);
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


	@Override
	public void onClick(View arg0) {
		int idView = arg0.getId();
		if (idView == findViewById(R.id.botonAbrir).getId()) {
			Toast.makeText(this, "click boton abrir", Toast.LENGTH_LONG).show();
			Intent i = new Intent(this, ListFileActivity.class);
			startActivityForResult(i, 0);
		}
	}

	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == 1) {
			String result = data.getStringExtra("result");
			// result nombre del archivo
			Toast.makeText(this, "url: " + result, Toast.LENGTH_LONG).show();
		}
	}

}
