package scratch.construction;

import org.simpleframework.xml.transform.Transform;

import java.awt.geom.Rectangle2D;

/**
 * Created by Anna on 2014-05-08.
 */
public class RectangleTransformer implements Transform<Rectangle2D.Double> {

	@Override
	public Rectangle2D.Double read(String value) {
		final String[] values = value.split(",");
		int[] results = new int[values.length];
		for(int i =0; i<values.length; i++) {
			results[i]=Integer.parseInt(values[i]);
		}
		return new Rectangle2D.Double(0,0, results[0],results[1]);
	}

	@Override
	public String write(Rectangle2D.Double value) throws Exception {
		return null;
	}
}
