package scratch.construction;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.IInteractiveObject;
import scratch.model.InteractiveObject;

import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by tejp on 2014-05-08.
 */
public class TiledMapPlus  extends TiledMap {

	private final List<IInteractiveObject> interactiveObjects = new ArrayList<>();
    private final List<NpcSpecification> npcSpecifications = new ArrayList<>();

	/**
	 * Create a new tile map based on a given TMX file
	 *
	 * @param ref The location of the tile map to load
	 * @throws org.newdawn.slick.SlickException Indicates a failure to load the tilemap
	 */
	public TiledMapPlus(String ref) throws SlickException {
		super(ref);

		initializeInteractiveObjects();
		initializeNpcSpecifications();
    }

	private void initializeNpcSpecifications() {
		for (final Object oGroup : objectGroups) {
			final ObjectGroup objectGroup = (ObjectGroup) oGroup;
			if ( ! "npc".equals( objectGroup.name ) ) {
                continue;
            }

			for (final Object gObject : objectGroup.objects) {
				final GroupObject groupObject = (GroupObject) gObject;
				npcSpecifications.add( new NpcSpecification(
                                groupObject.props.getProperty("npcType"),
                                Integer.parseInt(groupObject.props.getProperty("id")),
                                new Rectangle2D.Double(
                                        groupObject.x,
                                        groupObject.y,
                                        groupObject.width,
                                        groupObject.height
                                )

                        )
                );
			}
		}
	}

	private void initializeInteractiveObjects() {
		for (final Object oGroup : objectGroups) {
            final ObjectGroup objectGroup = (ObjectGroup) oGroup;
			if ( ! "interactive".equals(objectGroup.name)) {
                continue;
            }

			for (final Object gObject : objectGroup.objects) {
                final GroupObject groupObject = (GroupObject) gObject;

				interactiveObjects.add(
						new InteractiveObject(
								groupObject.name,
								groupObject.type,
								groupObject.x,
								groupObject.y,
								groupObject.width,
								groupObject.height,
								groupObject.props
						)
				);
			}

		}
	}

	public List<NpcSpecification> getNpcSpecifications () {
		return npcSpecifications;
	}

	public List<IInteractiveObject> getInteractiveObjects() {
		return interactiveObjects;
	}
}
