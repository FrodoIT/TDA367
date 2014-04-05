package view;

import java.awt.Point;

import model.MoveDirection;
import model.Player;
import model.PlayerInput;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

/**
 * 
 * @author André Samuelsson
 * @RevisedBy Anna Nylander
 */

public class PlayerView {
	
	private Player player;
	private TiledMap monsters;
	
	private Image [] movementNorth = new Image[3];
	private Image [] movementSouth = new Image[3];
	private Image [] movementWest = new Image[3];
	private Image [] movementEast = new Image[3];
	private int [] duration = {10, 10, 10};
	private Animation sprite, north, south, west, east;
		
	public PlayerView(Player player) {
		this.player = player;
		animationSetUp();
	}
	
	public void animationSetUp(){
		//TODO should probably be moved to another class later
		//we fetch the sprite through a tiledmap (like we do with the room map)
		try {
			monsters = new TiledMap("res/monster.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		int layerIndex=monsters.getLayerIndex("Tile Layer 1");

		movementNorth[0]= monsters.getTileImage(6, 6, layerIndex);
		movementNorth[1]= monsters.getTileImage(7, 6, layerIndex);
		movementNorth[2]= monsters.getTileImage(8, 6, layerIndex);
		
		movementSouth[0]= monsters.getTileImage(0, 6, layerIndex);
		movementSouth[1]= monsters.getTileImage(1, 6, layerIndex);
		movementSouth[2]= monsters.getTileImage(2, 6, layerIndex);

		movementWest[0]= monsters.getTileImage(9, 6, layerIndex);
		movementWest[1]= monsters.getTileImage(10, 6, layerIndex);
		movementWest[2]= monsters.getTileImage(11, 6, layerIndex);

		movementEast[0]= monsters.getTileImage(3, 6, layerIndex);
		movementEast[1]= monsters.getTileImage(4, 6, layerIndex);
		movementEast[2]= monsters.getTileImage(5, 6, layerIndex);
		
		//start animation loop
		north = new Animation(movementNorth, duration, true);
		north.start();
		south = new Animation(movementSouth, duration, true);
		south.start();
		west = new Animation(movementWest, duration, true);
		west.start();
		east = new Animation(movementEast, duration, true);
		east.start();
		
	}
	
	public void render(GameContainer c, Graphics g) {
		Point p = player.getPosition();
		PlayerInput playerInput = player.getPlayerInput();
		sprite=south;
		MoveDirection input= playerInput.getMoveInput();
		
		if(input==MoveDirection.NORTH){
			sprite=north;
		}else if(input==MoveDirection.SOUTH){
			sprite=south;
		}else if(input==MoveDirection.WEST || input==MoveDirection.NORTHWEST || input==MoveDirection.SOUTHWEST ){
			sprite=west;
		}else if(input==MoveDirection.EAST || input==MoveDirection.NORTHEAST || input==MoveDirection.SOUTHEAST ){
			sprite=east;
		}
		
		g.drawAnimation(sprite, p.x-16, p.y-16);
	}
	

}
