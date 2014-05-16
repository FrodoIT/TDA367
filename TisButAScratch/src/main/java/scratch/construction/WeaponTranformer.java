package scratch.construction;

import org.simpleframework.xml.transform.Transform;
import scratch.model.weapons.DefaultWeapon;
import scratch.model.weapons.IWeapon;

/**
 * Created by Anna on 2014-05-08.
 */
public class WeaponTranformer implements Transform<IWeapon> {
	@Override
	public IWeapon read(String value) {
        final String defaultWeapon = "DefaultWeapon";
		return defaultWeapon.compareTo(value)==0? new DefaultWeapon(): null;

	}

	@Override
	public String write(IWeapon value) throws Exception {
		return null;
	}
}
