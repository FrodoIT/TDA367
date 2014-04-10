import model.Model;

import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;

import view.View;
import controller.Controller;

/**
 * 
 * @author Alma Ottedag, Ivar Josefsson
 * @revisedBy Anna Nylander
 *
 */
public class Main {
	public static void main (String[] args) throws SlickException{
		Model model = new Model();
		View view = new View(model);
		AppGameContainer app = new AppGameContainer(new Controller(model, view));
		app.start();
	}
}
