package construction;

import model.INpc;
import model.Knuckles;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by pippin on 4/6/14.
 */
public class EnemyFactory {
    private List<INpc> npcs;

    public EnemyFactory(){
        npcs = new ArrayList<INpc>();
        npcs.add(new MonsterType(new Rectangle(32,32), new Knuckles(), 4, 2, "res/playerSprite.tmx", 0));
    }

    public List<INpc> getEnemies(){
        return npcs;
    }
}
