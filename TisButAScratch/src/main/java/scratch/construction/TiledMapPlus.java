package scratch.construction;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.*;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Set;

/**
 * Created by tejp on 2014-05-08.
 */
public class TiledMapPlus extends TiledMap implements IMap {

    private final List<IInteractiveObject> interactiveObjects = new ArrayList<>();
    private final List<NpcSpecification> npcSpecifications = new ArrayList<>();
    private final int collisionIndex;
    private final int id;

    /**
     * Create a new tile map based on a given TMX file
     *
     * @param ref The location of the tile map to load
     * @throws org.newdawn.slick.SlickException Indicates a failure to load the
     * tilemap
     */
    public TiledMapPlus(String ref) throws SlickException {
        super(ref);
        id = Integer.parseInt(getMapProperty("id", "0"));
        collisionIndex = getLayerIndex("collision");
        initializeInteractiveObjects();
        initializeNpcSpecifications();
    }

    private void initializeNpcSpecifications() {
        for (final Object oGroup : objectGroups) {
            final ObjectGroup objectGroup = (ObjectGroup) oGroup;
            if ("npc".compareTo(objectGroup.name) != 0) {
                continue;
            }

            for (final Object gObject : objectGroup.objects) {
                final GroupObject groupObject = (GroupObject) gObject;
                npcSpecifications.add(new NpcSpecification(
                        groupObject.props.getProperty("npcType"),
                        Integer.parseInt(groupObject.props.getProperty("id")),
                        new Rectangle2D.Double(
                                groupObject.x,
                                groupObject.y,
                                groupObject.width,
                                groupObject.height)));
            }
        }
    }

    private void initializeInteractiveObjects() {
        for (final Object oGroup : objectGroups) {
            final ObjectGroup objectGroup = (ObjectGroup) oGroup;
            if ("interactive".compareTo(objectGroup.name) != 0) {
                continue;
            }

            for (final Object gObject : objectGroup.objects) {
                final GroupObject groupObject = (GroupObject) gObject;

                Set<String> set = groupObject.props.stringPropertyNames();
                HashMap<String,String> map = new HashMap<>();
                for (String s : set) {
                    map.put(s,groupObject.props.getProperty(s));
                }
                if("box".compareTo(groupObject.props.getProperty("objectType")) == 0) {
                    interactiveObjects.add(
                            new MovableObject(
                                    groupObject.name,
                                    groupObject.type,
                                    groupObject.x,
                                    groupObject.y,
                                    groupObject.width,
                                    groupObject.height,
                                    map
                            ));
                } else {

                    interactiveObjects.add(
                            new InteractiveObject(
                                    groupObject.name,
                                    groupObject.type,
                                    groupObject.x,
                                    groupObject.y,
                                    groupObject.width,
                                    groupObject.height,
                                    map
                            ));
                }
            }
        }
    }

    @Override
    public List<NpcSpecification> getNpcSpecifications() {
        return npcSpecifications;
    }

    @Override
    public boolean hasInteractiveObject() {
        return interactiveObjects.isEmpty();
    }

    @Override
    public boolean hasNpc() {
        return npcSpecifications.isEmpty();
    }

    @Override
    public List<IInteractiveObject> getInteractiveObjects() {
        return interactiveObjects;
    }

    @Override
    public int getWidth() {
        return super.getWidth() * getTileWidth();
    }

    @Override
    public int getHeight() {
        return super.getHeight() * getTileHeight();
    }

    public int getId() {
        return id;
    }

    @Override
    public boolean isColliding(Vector2D coordinate) {
        try {
            return getTileId((int) coordinate.getX() / getTileWidth(), (int) coordinate.getY() / getTileHeight(), collisionIndex) != 0;
        } catch (IndexOutOfBoundsException e) {
            return false;
        }
    }
}
