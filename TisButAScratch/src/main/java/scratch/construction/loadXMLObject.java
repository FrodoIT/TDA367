package scratch.construction;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.model.GameCharacter;
import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Vector2D;
import scratch.model.weapons.IWeapon;
import scratch.model.weapons.Weapon;

import java.io.File;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.Objects;

/**
 * Created by Anna on 2014-05-18.
 */
public class loadXMLObject {
	final static Serializer serializer = new Persister(new MyMatcher());

	public Object loadObject (String loadObject, String file){
		final StringBuilder fileBuild = new StringBuilder();
		fileBuild.append("res/");
		fileBuild.append(file);
		fileBuild.append(".xml");
		final File source = new File(fileBuild.toString());
		Object object = new Object();
		try {
			switch (loadObject){
				case "NpcType": object = serializer.read(NpcType.class, source);
					break;
				case "GameCharacter": object= serializer.read(GameCharacter.class, source);
					break;
				case "Weapon": object = serializer.read(Weapon.class, source);
					break;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}

}
