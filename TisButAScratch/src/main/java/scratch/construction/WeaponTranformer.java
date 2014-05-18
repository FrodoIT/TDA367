package scratch.construction;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import org.simpleframework.xml.transform.Transform;
import scratch.model.GameCharacter;
import scratch.model.Vector2D;
import scratch.model.weapons.Weapon;
import scratch.model.weapons.IWeapon;

import java.io.File;

/**
 * Created by Anna on 2014-05-08.
 */
public class WeaponTranformer implements Transform<IWeapon> {
	@Override
	public IWeapon read(String value) {
		return value.isEmpty()? null: loadWeapon(value);
	}

	@Override
	public String write(IWeapon value) throws Exception {
		return null;
	}

	private Weapon loadWeapon(String file) {
		final Serializer serializer = new Persister(new MyMatcher());
		final StringBuilder fileBuild = new StringBuilder();
		fileBuild.append("res/");
		fileBuild.append(file);
		fileBuild.append(".xml");
		final File source = new File(fileBuild.toString());
		Weapon weapon;
		try {
			weapon = serializer.read(Weapon.class, source);

		} catch (Exception e) {
			System.out.println(e.toString());
			return null;
		}
		return weapon;
	}
}
