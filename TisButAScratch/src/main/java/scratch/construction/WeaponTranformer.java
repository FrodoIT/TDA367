package scratch.construction;

import org.simpleframework.xml.transform.Transform;
import scratch.model.weapons.Weapon;
import scratch.model.weapons.IWeapon;

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
		return (Weapon) new LoadXMLObject().loadObject("Weapon", file);

	}
}
