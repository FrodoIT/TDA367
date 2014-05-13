package scratch.construction;

import org.simpleframework.xml.transform.Transform;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

/**
 * Created by Anna on 2014-05-08.
 */
public class WeaponTranformer implements Transform<IWeapon> {
	@Override
	public IWeapon read(String value) throws Exception {
		if(value.compareTo("DefaultWeapon")==0) {
			return new DefaultWeapon();
		}
		return null;
	}

	@Override
	public String write(IWeapon value) throws Exception {
		return null;
	}
}
