package scratch.controller;

import scratch.construction.EnemyFactory;
import scratch.construction.TempRoomFactory;

import java.util.ArrayList;
import java.util.List;

import scratch.model.INpc;
import scratch.model.Model;

import org.newdawn.slick.Game;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.tiled.TiledMap;

import scratch.view.View;

/**
 * The main controller class to control updates, rendering, initiating and
 * delegate tasks to other controllers.
 * @author Anna Nylander
 *
 */
public class Controller implements Game{
	private Model model;
	private View view;
	private List<PlayerController> playerControllerList;
	private List<WorldController> worldControllerList;	

	public Controller(Model model, View view){
		this.view = view;
		this.model = model;
		playerControllerList=new ArrayList<PlayerController>();
	}
	
	@Override
	public void init(GameContainer container) throws SlickException {
		container.setTargetFrameRate(60);
                
                EnemyFactory enemyFactory = new EnemyFactory();
                for(INpc enemy : enemyFactory.getEnemies()){
                    view.addNpcView(enemy.getID(), enemy.getImagePath());
                }
                
		TempRoomFactory trf = new TempRoomFactory();
                view.addRoomView(trf.getRooms().get(0), trf.getTiledMap());
		model.setMap(trf.getRooms());
		playerControllerList.add(new PlayerController(model, view));
                



	}
	@Override
	public void render(GameContainer container, Graphics g) throws SlickException {
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
