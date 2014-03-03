package zoom;

import java.util.Dictionary;
import java.util.Hashtable;

import geometry.Point2D;

import com.example.mapper.MainActivity;

import android.view.MotionEvent;

public class Zoom {

	private MainActivity mainActivity;
	private Point2D midPoint;
	private Point2D p1, p2;
	private StringBuilder builder;
	private int MAX_ZOOM;
	private float camera, distanciaAnterior;

	public Zoom(MainActivity mainActivity) {
		this.mainActivity = mainActivity;
		this.builder = new StringBuilder();
		this.MAX_ZOOM = 20;
		this.camera = 100;
		this.distanciaAnterior = 0;

	}

	public void event(MotionEvent event) {
		if (event.getPointerCount() == 2) {
			this.p1 = new Point2D(event.getX(0), event.getY(0));
			this.p2 = new Point2D(event.getX(1), event.getY(1));
			getZoom();
		}
	}

	public Dictionary<String, Float> getZoom() {
		Dictionary<String, Float> dates = new Hashtable<String, Float>();

		float distance = p1.distanceEuclidean(p2);
		float distanceTr = (float) (distanciaAnterior - distance);
		distanceTr = distanceTr < -MAX_ZOOM ? -MAX_ZOOM
				: distanceTr > MAX_ZOOM ? MAX_ZOOM : distanceTr;

		camera += distanceTr / 5;
		camera = camera < 0 ? 0 : camera > 100 ? 100 : camera;
		midPoint = p1.middlePoint(p2);

		builder.setLength(0);
		builder.append("Camera = " + camera + "\n");
		builder.append("Pto Medio = " + midPoint.x + " x " + midPoint.y);

		distanciaAnterior = distance;
		this.mainActivity.log.setText(builder.toString());

		dates.put("camera", camera);
		dates.put("ptoMedioX", midPoint.x);
		dates.put("ptoMedioY", midPoint.y);

		return dates;
	}

}
