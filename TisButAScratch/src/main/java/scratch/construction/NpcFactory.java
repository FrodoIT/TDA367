package scratch.construction;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.construction.plugin.PluginConstants;
import scratch.construction.plugin.exported.SimpleNPCPlugin;
import scratch.model.IMap;
import scratch.model.NpcType;
import scratch.model.Vector2D;
import scratch.model.weapons.DefaultWeapon;

import java.awt.*;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.Map;

public final class NpcFactory extends PluginUserFactory<NpcType> {

    public static final String KEY = "npc_factory";

    public NpcFactory(IMap map){
        super(map);
        loadType();
    }

    @Override
    public void loadType() {
        //TODO: This will extend when we add MORE types of NPCS.
        for(Map.Entry<String, Rectangle2D.Double> entry : super.getMap().getNpcRectangleMap().entrySet()){
            int keyToConstant = 0;
            if(entry.getKey().equals("basicMonster")){
                keyToConstant = PluginConstants.DOOR;
            } else if(entry.getKey().equals("specialMonster")){
                keyToConstant = PluginConstants.BOX;
            }
          //  super.getGivenTypeMap().put(keyToConstant, new NpcType(entry.getValue(), new DefaultWeapon(), 10, 2, "monster.tmx", 10, new SimpleNPCPlugin()));
			super.getGivenTypeMap().put(keyToConstant,NPCXML("StandardEnemy", new Vector2D(500,400), 0));

        }

    }

	/**
	 * Method for reading an xml file. If creating new xml, please follow the structure in StanardEnemy.xml and add to res-map.
	 * If new weapon/NPCMOVEplugin is created you will need to add them to the respective transformer class.
	 * @param file the name of the xmlfile without extension, eg "StandardEnemy" Do NOT add location.
	 * @param position The position the npc should have
	 * @return A npc with the attributes as in the xml file.
	 */
	private NpcType NPCXML(String file, Vector2D position, int id){
		Serializer serializer = new Persister(new MyMatcher());
		file= "res/"+file+".xml";
		File source = new File(file);
		try {
			NpcType npc= serializer.read(NpcType.class, source);
			System.out.println(npc.toString());
			npc.setPosition(position);
			npc.setId(id);
			return npc;
		} catch (Exception e) {
			System.out.println("XML-READING FAILED: " + e.toString());
		}
		return null;
	}
}
