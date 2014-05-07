package scratch;


import org.newdawn.slick.AppGameContainer;
import org.newdawn.slick.SlickException;
import scratch.controller.Controller;
import scratch.menu.Menu;
import scratch.model.Model;
import scratch.view.View;

/**
 * 
 * @author Alma Ottedag, Ivar Josefsson
 * @revisedBy Anna Nylander
 *
 */
public class Main {
	public static void main (String[] args) throws SlickException{
                Menu.mainMenu();
                
                
            
                /*
                Model model = new Model();
		View view = new View();
		AppGameContainer app = new AppGameContainer(new Controller(model, view));
		app.start();
                        */
	}
}
