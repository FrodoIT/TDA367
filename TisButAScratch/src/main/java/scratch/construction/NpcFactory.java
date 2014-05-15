package scratch.construction;

import org.simpleframework.xml.Serializer;
import org.simpleframework.xml.core.Persister;
import scratch.model.IMap;
import scratch.model.NpcType;
import scratch.model.Room;
import scratch.model.Vector2D;

import java.io.File;
import java.util.List;

public final class NpcFactory extends AbstractPluginUserFactory<NpcType> {

    public static final String KEY = "npc_factory";
    public static final String BASICMONSTER = "basicMonster";
    public static final String SPECIALMONSTER = "specialMonster";
    private final Room room;

    public NpcFactory(IMap map, Room room) {
        super(map);
        this.room = room;
        loadType();
    }

    @Override
    public void loadType() {

        final List<NpcSpecification> npcs = super.getMap().getNpcSpecifications();

	    //TODO viktigt för att vi inte ska lägga till i mappen med samma nyckel.
        //ska mappen vara kvar eller ersättas med en lista?, just nu är det endast NpcFactory som
        // extendar PluginUserFactory.
        int keyToConstant = 0;
        for (NpcSpecification npc : npcs) {
            final NpcType loadedNpc = loadNpc(npc.getPluginName(), new Vector2D(npc.getArea().getX(), npc.getArea().getY()), npc.getId());
            super.getGivenTypeMap().put(keyToConstant++, loadedNpc);
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
	private NpcType loadNpc(String file, Vector2D position, int id){
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
