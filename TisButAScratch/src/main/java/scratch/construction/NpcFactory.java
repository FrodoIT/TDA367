package scratch.construction;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.construction.plugin.exported.SimpleNPCPlugin;
import scratch.model.*;

import java.awt.geom.Rectangle2D;
import java.io.File;
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
        //TODO: This will extend when we add MORE types of NPCS.
        for(Map.Entry<String, Rectangle2D.Double> entry : super.getMap().getNpcRectangleMap().entrySet()){
            int keyToConstant = 0;
            if(BASICMONSTER.compareTo(entry.getKey()) == 0){
                keyToConstant = 1;
            } else if(SPECIALMONSTER.compareTo(entry.getKey()) == 0){
                keyToConstant = 2;
            }

            //TODO save NpcType returned from should be saved. moveaistuffplugin should be added to it
            INPCMove movePattern = new SimpleNPCPlugin(); // this should be taken from loaded plugins not created here.
            movePattern.setRoomData(room);
            final String standardEnemy = "StandardEnemy";
            NpcType loadedNpc = npcXML(standardEnemy, new Vector2D(500, 400), 0);
            loadedNpc.setMovementPattern(movePattern);
            loadedNpc.registerListener(room);

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
    private NpcType npcXML(String file, Vector2D position, int id){
        Serializer serializer = new Persister(new MyMatcher());
        File source = new File("res/" + file + ".xml");
        try {
            NpcType npc= serializer.read(NpcType.class, source);
            npc.setPosition(position);
            npc.setId(id);
            return npc;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
