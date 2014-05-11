package scratch.construction;

import org.simpleframework.xml.transform.Transform;

import java.awt.geom.Rectangle2D;
import java.util.List;

/**
 * Created by Anna on 2014-05-08.
 */
public class RectangleTransformer implements Transform<Rectangle2D.Double> {
	@Override
	public Rectangle2D.Double read(String value) throws Exception {
		String[] values = value.split(",");
		int[] results = new int[values.length];
		for(int i =0; i<4; i++) {
			results[i]=Integer.parseInt(values[i]);
		}
		return new Rectangle2D.Double(results[0],results[1],results[2],results[3]);
	}

	@Override
	public String write(Rectangle2D.Double value) throws Exception {
		return null;
	}
}
