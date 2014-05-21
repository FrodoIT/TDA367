package scratch.construction;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.model.GameCharacter;
import scratch.model.NpcType;
import scratch.model.weapons.Weapon;

import java.io.File;

/**
 * Created by Anna on 2014-05-18.
 */
public class LoadXMLObject {
	final static Serializer serializer = new Persister(new MyMatcher());

	public Object loadObject (String loadObject, String file){
		final StringBuilder fileBuild = new StringBuilder();
		fileBuild.append("res/");
		fileBuild.append(file);
		fileBuild.append(".xml");
		final File source = new File(fileBuild.toString());
		Object object = new Object();
		try {
			if("NpcType".equals(loadObject)) {
				object = serializer.read(NpcType.class, source);
			}else if("GameCharacter".equals(loadObject)) {
				object = serializer.read(GameCharacter.class, source);
			}else if("Weapon".equals(loadObject)){
				object = serializer.read(Weapon.class, source);
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
		return object;
	}

}
