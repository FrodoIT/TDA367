package scratch.view;

import java.util.EnumMap;
import java.util.HashMap;
import java.util.Map;

import scratch.model.MoveDirection;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.tiled.TiledMap;

public class SpriteDirectionRenderer {

	private static final int SOUTH = 0, EAST = 1, NORTH = 2, WEST = 3;
	
	private final Map<MoveDirection, Animation> moveAnimations = new EnumMap<>(MoveDirection.class);
	private final Map<MoveDirection, Image> lookDirections = new EnumMap<>(MoveDirection.class);


	private Animation sprite;
	
	//TODO the logic which lastMoveInput is used for can use sprite instead.
	// but the logic for matching MoveDirection with the repective animation needs to be done at one place or another
	private MoveDirection lastMoveInput = MoveDirection.NONE;
	
	private final int imagesPerDirection;
	private final TiledMap map;
	
	public SpriteDirectionRenderer(TiledMap map) {
		int nbrOfSprites = map.getWidth();
		if (nbrOfSprites % 4 != 0)
			throw new IllegalArgumentException();

		this.map = map;

		imagesPerDirection = nbrOfSprites / 4;

		Image [] movementSouth = new Image[imagesPerDirection];
		Image [] movementEast = new Image[imagesPerDirection];
		Image [] movementNorth = new Image[imagesPerDirection];
		Image [] movementWest = new Image[imagesPerDirection];
		
		setupMovementDirectionSprite(movementSouth, SOUTH);
		setupMovementDirectionSprite(movementEast, EAST);
		setupMovementDirectionSprite(movementNorth, NORTH);
		setupMovementDirectionSprite(movementWest, WEST);

		int[] duration = new int[imagesPerDirection];
		for (int i = 0 ; i < imagesPerDirection ; i ++) {
			duration[i] = 100; 
			// TODO duration = 100 can't be used always. if many images is used to show
			//one direction then it would be wiser to let the duration depend on the number of images
		}

		Animation north = new Animation(movementNorth, duration, true);
		Animation south = new Animation(movementSouth, duration, true);
		Animation west = new Animation(movementWest, duration, true);
		Animation east = new Animation(movementEast, duration, true);
		sprite = south;

		
		moveAnimations.put(MoveDirection.NORTHWEST, west);
		moveAnimations.put(MoveDirection.WEST, west);
		moveAnimations.put(MoveDirection.SOUTHWEST, west);
		moveAnimations.put(MoveDirection.NORTHEAST, east);
		moveAnimations.put(MoveDirection.EAST, east);
		moveAnimations.put(MoveDirection.SOUTHEAST, east);
		moveAnimations.put(MoveDirection.NORTH, north);
		moveAnimations.put(MoveDirection.SOUTH, south);
		moveAnimations.put(MoveDirection.NONE, south);
		
		lookDirections.put(MoveDirection.NORTHWEST, movementWest[0]);
		lookDirections.put(MoveDirection.WEST, movementWest[0]);
		lookDirections.put(MoveDirection.SOUTHWEST, movementWest[0]);
		lookDirections.put(MoveDirection.NORTHEAST, movementEast[0]);
		lookDirections.put(MoveDirection.EAST, movementEast[0]);
		lookDirections.put(MoveDirection.SOUTHEAST, movementEast[0]);
		lookDirections.put(MoveDirection.NORTH, movementNorth[0]);
		lookDirections.put(MoveDirection.SOUTH, movementSouth[0]);
		lookDirections.put(MoveDirection.NONE, movementSouth[0]);
	}
	
	/**
	 * this method is used to initializes the array-elements of a specified moveDireciton 
	 * @param spriteDirection the image array containing images to play to simulate walking in a direction
	 * @param index South, East, North, West is represented by 0, 1, 2, 3 respectively
	 */
	private void setupMovementDirectionSprite(Image[] spriteDirection, int index) {
		int layerIndex = map.getLayerIndex("sprite");
		int startCount = index * imagesPerDirection;

		for (int i = 0 ; i < spriteDirection.length ; i++) {
			spriteDirection[i] = map.getTileImage(i + startCount, 0, layerIndex);
		}
	}
	
	public void render(Graphics g, MoveDirection moveInput, int x, int y) {
	
	if (moveInput == MoveDirection.NONE) {
		g.drawImage(lookDirections.get(lastMoveInput), x, y);
		return; 
	}
	
	if (lastMoveInput != moveInput) {
		sprite.stop();
		sprite = moveAnimations.get(moveInput);
		sprite.start();
	}
		lastMoveInput = moveInput;
		g.drawAnimation(sprite, x, y);
	}
}