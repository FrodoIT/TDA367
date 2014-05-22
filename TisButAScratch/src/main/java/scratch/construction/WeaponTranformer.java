package scratch.construction;

import org.simpleframework.xml.transform.Transform;
import scratch.model.weapons.Weapon;

/**
 * Created by Anna on 2014-05-08.
 */
public class WeaponTranformer implements Transform<Weapon> {

    @Override
    public Weapon read(String value) {
        return value.isEmpty() ? null : loadWeapon(value);
    }

    @Override
    public String write(Weapon value) throws Exception {
        return null;
    }

    private Weapon loadWeapon(String file) {
        return (Weapon) new LoadXMLObject().loadObject("Weapon", file);

    }
}
