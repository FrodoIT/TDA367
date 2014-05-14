package scratch.construction;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.IInteractiveObject;

import java.awt.geom.Rectangle2D;
import java.util.*;

/**
 * Created by tejp on 2014-05-08.
 */
public class TiledMapPlus extends TiledMap {

	private List<IInteractiveObject> interactiveObjects;
    private List<NpcSpecification> npcSpecifications;

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
		for (Object oGroup : objectGroups) {
			ObjectGroup objectGroup = (ObjectGroup) oGroup;
			if ( ! "npc".equals( objectGroup.name ) )
				continue;

			for (Object gObject : objectGroup.objects) {
				GroupObject groupObject = (GroupObject) gObject;
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
		List<IInteractiveObject> interactiveObjects = new ArrayList<>();

		for (Object oGroup : objectGroups) {
			ObjectGroup objectGroup = (ObjectGroup) oGroup;
			if ( ! objectGroup.name.equals("interactive"))
				continue;

			for (Object gObject : objectGroup.objects) {
				GroupObject groupObject = (GroupObject) gObject;

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
		this.interactiveObjects = interactiveObjects;
	}

	public List<NpcSpecification> getNpcSpecifications () {
		return npcSpecifications;
	}

	public List<IInteractiveObject> getInteractiveObjects() {
		return interactiveObjects;
	}

	class InteractiveObject implements IInteractiveObject {

		private String name, type;
		private Rectangle2D.Double rect;
		private Properties properties;

		InteractiveObject(String name, String type, int x, int y, int width, int height, Properties properties) {
			this.name = name;
			this.type = type;
			this.rect = new Rectangle2D.Double(x, y, width, height);
			this.properties = properties;
		}

		@Override
		public String getName() {
			return name;
		}

		@Override
		public String getType() {
			return type;
		}

		@Override
		public Rectangle2D.Double getArea() {
			return rect;
		}

		@Override
		public Properties getProperties() {
			return properties;
		}
	}
}
