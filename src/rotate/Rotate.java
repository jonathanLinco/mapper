package rotate;

import geometry.Point2D;
import android.view.MotionEvent;

import com.example.mapper.MainActivity;

public class Rotate {

	private Point2D p1, p2;
	private StringBuilder builder;
	private MainActivity mainActivity;

	public Rotate(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		this.builder = new StringBuilder();
	}

	public void event(MotionEvent event) {
		if (event.getPointerCount() == 2) {
			p1 = new Point2D(event.getX(0), event.getY(0));
			p2 = new Point2D(event.getX(1), event.getY(1));
			getAngle();
		}
	}

	public float getAngle() {
		float angle = p1.angle(p2);
		builder.setLength(0);
		builder.append("Angle = " + angle + "\n");
		this.mainActivity.log.setText(builder.toString());
		return angle;
	}

}
