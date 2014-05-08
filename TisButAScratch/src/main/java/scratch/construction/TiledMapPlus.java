package scratch.construction;

import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;
import scratch.model.IInteractiveObjectProperties;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Properties;

/**
 * Created by tejp on 2014-05-08.
 */
public class TiledMapPlus extends TiledMap {
	/**
	 * Create a new tile map based on a given TMX file
	 *
	 * @param ref The location of the tile map to load
	 * @throws org.newdawn.slick.SlickException Indicates a failure to load the tilemap
	 */
	public TiledMapPlus(String ref) throws SlickException {
		super(ref);
	}

	public List<IInteractiveObjectProperties> getInteractiveGroupObjects() {
		List<IInteractiveObjectProperties> interactiveObject = new ArrayList<>();

		for (Object oGroup : objectGroups) {
			ObjectGroup objectGroup = (ObjectGroup) oGroup;
			if ( ! objectGroup.name.equals("interactive"))
				continue;

			for (Object gObject : objectGroup.objects) {
				GroupObject groupObject = (GroupObject) gObject;

				interactiveObject.add(
						new InteractiveObjectProperties(
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
		return interactiveObject;
	}

	class InteractiveObjectProperties implements IInteractiveObjectProperties {

		private String name, type;
		private int x, y, width, height;
		private Properties properties;

		InteractiveObjectProperties(String name, String type, int x, int y, int width, int height, Properties properties) {
			this.name = name;
			this.type = type;
			this.x = x;
			this.y = y;
			this.width = width;
			this.height = height;
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
		public int getX() {
			return x;
		}

		@Override
		public int getY() {
			return y;
		}

		@Override
		public int getWidth() {
			return width;
		}

		@Override
		public int getHeight() {
			return height;
		}

		@Override
		public Properties getProperties() {
			return properties;
		}
	}
}
