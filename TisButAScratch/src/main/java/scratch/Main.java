package scratch;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import scratch.controller.Controller;
import scratch.model.Game;

/**
 * 
 * @author Alma Ottedag, Ivar Josefsson
 * @revisedBy Anna Nylander
 *
 */
public class Main {
	public static void main (String[] args) throws SlickException{
		Game game = new Game();
		AppGameContainer app = new AppGameContainer(new Controller(game));
		app.start();
	}
}
