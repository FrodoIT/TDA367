package scratch.construction;

import org.simpleframework.xml.*;
import org.simpleframework.xml.convert.Convert;
import scratch.model.weapons.DefaultWeapon;

import java.awt.*;
import java.awt.geom.Rectangle2D;
import java.util.Objects;

/**
 * Created by Anna on 2014-05-08.
 */
@Root
public class Test {

	@Attribute
	public int id;
	@Element
	public int nmbr;
	@Element
	public String string;
	@Element(type=Point.class)
	public Point weapon;
	@Element(type=Rectangle2D.class)
	public Rectangle2D rect;

	public Test(int id, int nmbr, String string, Point weapon, Rectangle2D rect) {
		this.id = id;
		this.rect=rect;
		this.weapon=weapon;
		this.nmbr=nmbr;
		this.string=string;

	}

	public Test() {
		super();
	}
	public void isItAlive(){
		System.out.println("Yes it is! " + string + id + nmbr + weapon + rect.toString());
	}
}
