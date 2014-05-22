package scratch.construction;

import java.awt.geom.Rectangle2D;
import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;
import scratch.model.INPCMove;
import scratch.model.weapons.WeaponPath;

/**
 * Created by Anna on 2014-05-08.
 */
public class MyMatcher implements Matcher{
	@Override
	public Transform match(Class type) {
            System.out.println("trying to load: " + type);
		if(type.equals(INPCMove.class) ){
			return new INPCMoveTransformer();
		}
		if(type.equals(Rectangle2D.Double.class)){
			return new RectangleTransformer();
		}
		if(type.equals(WeaponPath.class)) {
                    System.out.println("Match found! calling weapontranformer");
			return new WeaponTranformer();
		}
		return null;
	}
}
