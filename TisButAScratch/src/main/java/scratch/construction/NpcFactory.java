package scratch.construction;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.construction.plugin.exported.SimpleNPCPlugin;
import scratch.model.*;

import java.awt.geom.Rectangle2D;
import java.io.File;
import java.util.List;
import java.util.Map;

public final class NpcFactory extends PluginUserFactory<NpcType> {

    public static final String KEY = "npc_factory";
    public static final String BASICMONSTER = "basicMonster";
    public static final String SPECIALMONSTER = "specialMonster";
    private Room room;

    public NpcFactory(IMap map, Room room){
        super(map);
        this.room = room;
        loadType();
    }

    @Override
    public void loadType() {

	        List<NpcSpecification> npcs = super.getMap().getNpcSpecifications();

	        for(NpcSpecification npc: npcs) {
		        System.out.println("Boppe! ");
				int keyToConstant=+1;
		        NpcType loadedNpc = NPCXML(npc.getPluginName(), new Vector2D(npc.getArea().getX(), npc.getArea().getY()), npc.getId());
		        super.getGivenTypeMap().put(keyToConstant, loadedNpc);
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
			npc.getMovementPattern().setRoomData(room);
			npc.registerListener(room);
			return npc;
		} catch (Exception e) {
			System.out.println("XML-READING FAILED: " + e.toString());
		}
		return null;
	}
}
