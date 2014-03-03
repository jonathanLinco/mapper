package file;

import geometry.Point3D;
import geometry.Polyline;

import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;

public class DXF {

	public static List<Polyline> getPolylines(String direccion) {
		List<Polyline> polylines = new ArrayList<Polyline>();
		try {
			BufferedReader br = new BufferedReader(new FileReader(direccion));
			String line = br.readLine();
			Polyline polyline = null;
			while (line != null) {
				if (line.equals("POLYLINE")) {
					if (polyline != null) {
						polylines.add(polyline);
					}
					polyline = new Polyline();
				}
				
				if (line.equals(" 10") && polyline != null) {
					float x = Float.parseFloat(br.readLine());
					br.readLine();
					float y = Float.parseFloat(br.readLine());
					br.readLine();
					float z = Float.parseFloat(br.readLine());
					polyline.addPoint(new Point3D(x, y, z));
				}
				line = br.readLine();
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return polylines;
	}
}
