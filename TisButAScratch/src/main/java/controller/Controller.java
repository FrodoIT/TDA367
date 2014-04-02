package controller;

import java.util.List;

import model.Model;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import view.RoomView;
import view.View;


public class Controller implements Game{
	private Model model;
	private View view;
	private List<PlayerController> playerControllerList;
	private List<WorldController> worldControllerList;	
	private TiledMap map;
	
	public Controller(Model model, View view){
		this.view = view;
		this.model = model;
		playerControllerList.add(new PlayerController(model));
	}
	
	@Override
	public void init(GameContainer arg0) throws SlickException {
		container.setTargetFrameRate(60);
		try {
			map = new TiledMap("res/spawn.tmx");
		} catch (SlickException e) {
			e.printStackTrace();
		}
		
		RoomView roomView = new RoomView(map);
		
	}
	@Override
	public void render(GameContainer arg0, Graphics arg1) throws SlickException {
		view.render(container, g);
		
	}
	@Override
	public void update(GameContainer container, int delta)throws SlickException {
		for (PlayerController pc: playerControllerList){
			pc.registerInput(container.getInput());
		}
		model.update();
		
	}
	
	public List<PlayerController> getPlayerControllerList(){
		return playerControllerList;
	}
	public List<WorldController> getWorldControllerList(){
		return worldControllerList;
	}
	@Override
	public boolean closeRequested() {
		return true;
	}
	@Override
	public String getTitle() {
		return "This but a Scratch";
	}
}
