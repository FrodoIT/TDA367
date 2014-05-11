package scratch.construction;

import org.simpleframework.xml.transform.Transform;

import java.awt.geom.Rectangle2D;

/**
 * Created by Anna on 2014-05-08.
 */
public class RectangleTransformer implements Transform<Rectangle2D.Double> {
	@Override
	public Rectangle2D.Double read(String value) throws Exception {
		return new Rectangle2D.Double(0,0,32,32);
	}

	@Override
	public String write(Rectangle2D.Double value) throws Exception {
		return null;
	}
}
