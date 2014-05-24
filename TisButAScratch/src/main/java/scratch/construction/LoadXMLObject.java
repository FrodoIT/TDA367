package scratch.construction;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.model.GameCharacter;
import scratch.model.NpcType;
import scratch.model.weapons.Weapon;

import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Created by Anna on 2014-05-18.
 */
public class LoadXMLObject {
	private final static Serializer SERIALIZER = new Persister(new MyMatcher());

	public Object loadObject (String loadObject, String file){
		if (file.trim().isEmpty()){
            throw new IllegalArgumentException("Tried to load file: " + file + ". It is not a proper filename");
        }
        final StringBuilder fileBuild = new StringBuilder();
        fileBuild.append("res/");
		fileBuild.append(file.trim());
		fileBuild.append(".xml");
        final File source = new File(fileBuild.toString());
        try {
            if("NpcType".equals(loadObject)) {
				return SERIALIZER.read(NpcType.class, source);
			}else if("GameCharacter".equals(loadObject)) {
				return SERIALIZER.read(GameCharacter.class, source);
			}else if("Weapon".equals(loadObject)){
				return SERIALIZER.read(Weapon.class, source);
			}
		} catch (Exception e) {
            Logger.getLogger(LoadXMLObject.class.getName()).log(Level.SEVERE, null, e);
		}
        return null;
    }

}
