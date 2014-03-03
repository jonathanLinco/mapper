package traslate;

import com.example.mapper.MainActivity;

import geometry.Point2D;
import android.support.v4.view.MotionEventCompat;
import android.view.MotionEvent;

public class Traslate {
	
	public Point2D  pDist1, pDist2;
	public StringBuilder builder;
	public MainActivity mainActivity;

	public Traslate(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		this.builder = new StringBuilder();
	}

	public void event(MotionEvent event) {
		if (event.getPointerCount() == 1) {
			if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_DOWN) {
				this.pDist1 = new Point2D(event.getX(0), event.getY(0));
			} else if (MotionEventCompat.getActionMasked(event) == MotionEvent.ACTION_UP) {
				this.pDist2 = new Point2D(event.getX(0), event.getY(0));
				traslate();
			}
		}
	}

	public float[] traslate() {
		float x = this.pDist1.distanceX(this.pDist2);
		float y = this.pDist1.distanceY(this.pDist2);
		builder.setLength(0);
		builder.append("X = " + x + "Y = " + y + "\n");
		this.mainActivity.log.setText(builder.toString());
		return new float[]{x,y};
	}

}
