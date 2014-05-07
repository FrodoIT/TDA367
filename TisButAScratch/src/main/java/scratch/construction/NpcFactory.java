package scratch.construction;

import scratch.construction.plugin.Pluggable;
import scratch.model.INPCMove;
import scratch.model.INpc;
import scratch.model.NpcType;
import scratch.model.weapons.DefaultWeapon;

import java.awt.geom.Rectangle2D;

public final class NpcFactory extends PluginUserFactory<INpc> {

	public static final String KEY = "npc_factory";

    public NpcFactory(){
        loadNpcs();
    }

    @Override
    public INpc createType(int id, double xPosition, double yPosition) {
        for(INpc npc : super.getGivenTypeList()){
            if(npc.getID() == id){
                return npc.createCopy(xPosition, yPosition);
            }
        }
        return super.getGivenTypeList().get(0).createCopy(xPosition, yPosition);
    }

    private void loadNpcs() {
        //TODO this needs refactoring. for example all monsters have same id (1)
        System.out.println(super.getPluginMap().values().size());
        System.out.println(super.getPluginMap().keySet() +  "nycklarna");
        super.getGivenTypeList().add(new NpcType(new Rectangle2D.Double(32, 32, 32, 32), new DefaultWeapon(), 1, 1, "/res/playerSprite.tmx", 0, (INPCMove) super.getPluginMap().get(1).get()));
    }
}
