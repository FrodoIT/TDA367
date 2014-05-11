package scratch.construction.plugin;

/**
 * Created by pippin on 5/7/14.
 */
public final class PluginConstants {

    public static final int NPC = 1;
    public static final int PLAYER = 2;
    public static final int DOOR = 3;
    public static final int BOX = 4;

	public static int getIdFromType(String type) {
		//TODO this can be done diffrently but i have better things to do

		switch (type.toLowerCase()){
			case "npc": return 1;
			case "player": return 2;
			case "door": return 3;
			case "box": return 4;
			default: return -1;
		}
	}
}
