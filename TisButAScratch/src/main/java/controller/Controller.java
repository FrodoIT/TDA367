package controller;

import java.util.List;

import model.Model;
import view.View;


public class Controller {
	private Model model;
	private View view;
	private List<PlayerController> playerControllerList;
	private List<WorldController> worldControllerList;	
	
	public Model getModel(){
		return model;
	}
	public View getView(){
		return view;
	}
	
	public List<PlayerController> getPlayerControllerList(){
		return playerControllerList;
	}
	public List<WorldController> getWorldControllerList(){
		return worldControllerList;
	}
}
