package scratch.construction;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.construction.plugin.AIPlugin;
import scratch.construction.plugin.Pluggable;
import scratch.construction.plugin.PluginLoader;
import scratch.model.NpcType;
import scratch.model.Room;
import scratch.model.Vector2D;

import java.io.File;
import java.util.*;

public final class NpcFactory {

    public static final String KEY = "npc_factory";
    private final List<Room> rooms;
    private final Map<Room, List<NpcType>> roomNpcsMap;

    public NpcFactory(List<Room> rooms) {
        roomNpcsMap = new HashMap<>();
        this.rooms = rooms;
        loadNpcsFromMap();
    }

    public Collection<NpcType> getNpcsForRoom(Room room) {
        return roomNpcsMap.get(room);
    }

    public void loadNpcsFromMap() {
        for (Room room : rooms) {
            final List<NpcSpecification> npcSpecs = room.getMap().getNpcSpecifications();

            System.out.println("mobbs found on map: " + npcSpecs.size());

            List<NpcType> npcs = new ArrayList<>();
            for (NpcSpecification npc : npcSpecs) {
                final NpcType loadedNpc = loadNpc(
                        npc.getPluginName(),
                        new Vector2D(npc.getArea().getX(),npc.getArea().getY()),
                        npc.getId(),
                        room);
                if (loadedNpc != null) {
                    npcs.add(loadedNpc);
                }
            }

            roomNpcsMap.put(room, npcs);
        }
    }

    /**
     * Method for reading an xml file. If creating new xml, please follow the
     * structure in StanardEnemy.xml and add to res-map. If new
     * weapon/NPCMOVEplugin is created you will need to add them to the
     * respective transformer class.
     *
     * @param file the name of the xmlfile without extension, eg "StandardEnemy"
     * Do NOT add location.
     * @param position The position the npc should have
     * @return A npc with the attributes as in the xml file.
     */
	private NpcType loadNpc(String file, Vector2D position, int id, Room room){
		final Serializer serializer = new Persister(new MyMatcher());
        final StringBuilder fileBuild = new StringBuilder();
        fileBuild.append("res/");
        fileBuild.append(file);
        fileBuild.append(".xml");
        final File source = new File(fileBuild.toString());
        NpcType npc;
        try {
            npc = serializer.read(NpcType.class, source);

        } catch (Exception e) {
            System.out.println(e.toString());
            return null;
        }

        npc.setPosition(position);
        npc.setId(id);
        npc.getMovementPattern().setRoomData(room);
        npc.registerListener(room);
        return npc;
    }
}
