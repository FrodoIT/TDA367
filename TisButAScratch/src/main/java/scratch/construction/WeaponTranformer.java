package scratch.construction;

import org.simpleframework.xml.transform.Transform;
import scratch.model.weapons.Weapon;

/**
 * Created by Anna on 2014-05-08.
 */
public class WeaponTranformer implements Transform<Weapon> {

    @Override
    public Weapon read(String value) {
        System.out.println("this is the value: " + value + (value.isEmpty()));
        return value.isEmpty() ? null : loadWeapon(value);
    }

    @Override
    public String write(Weapon value) throws Exception {
        return null;
    }

    private Weapon loadWeapon(String file) {
        System.out.println("the new instance of weapon" + (Weapon) new LoadXMLObject().loadObject("Weapon", file));

        return (Weapon) new LoadXMLObject().loadObject("Weapon", file);

    }
}
