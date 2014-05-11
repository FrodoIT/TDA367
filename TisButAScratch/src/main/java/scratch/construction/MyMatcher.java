package scratch.construction;

import org.simpleframework.xml.transform.Matcher;
import org.simpleframework.xml.transform.Transform;
import scratch.model.INPCMove;
import scratch.model.IRoomData;
import scratch.model.MoveDirection;
import scratch.model.weapons.IWeapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;

/**
 * Created by Anna on 2014-05-08.
 */
public class MyMatcher implements Matcher{
	@Override
	public Transform match(Class type) throws Exception {
		if(type.equals(INPCMove.class) ){
			return new INPCMoveTransformer();
		}
		if(type.equals(Rectangle2D.Double.class)){
			return new RectangleTransformer();
		}
		if(type.equals(IWeapon.class)) {
			return new WeaponTranformer();
		}
		return null;
	}
}
