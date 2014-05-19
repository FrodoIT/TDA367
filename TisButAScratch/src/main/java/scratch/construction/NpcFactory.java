package scratch.construction;

import scratch.model.IRoomData;
import scratch.model.NpcType;
import scratch.model.Room;
import scratch.model.Vector2D;

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
        for (final Room room : rooms) {
            final List<NpcSpecification> npcSpecs = room.getMap().getNpcSpecifications();
            final List<NpcType> npcs = new ArrayList<>();

            for (final NpcSpecification npc : npcSpecs) {
	            NpcType loadedNpc;
	            try {
		            loadedNpc = loadNpc(
				            npc.getPluginName(),
				            new Vector2D(npc.getArea().getX(), npc.getArea().getY()),
				            npc.getId(),
				            room);
	            }catch(NullPointerException e){
		            e.printStackTrace();
		            return;
	            }
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
	private synchronized NpcType loadNpc(String file, Vector2D position, int id, IRoomData room){
		NpcType npc = (NpcType) new LoadXMLObject().loadObject("NpcType", file);

        npc.setPosition(position);
        npc.setId(id);
        npc.getMovementPattern().setRoomData(room);

        return npc;
    }
}
