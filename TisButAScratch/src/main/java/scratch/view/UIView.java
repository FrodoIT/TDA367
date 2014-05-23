package scratch.view;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.geom.Rectangle;
import scratch.model.GameCharacter;

/**
 * Created by Anna on 2014-05-19.
 */
public class UIView {

	private GameCharacter character;
	private final double maxHealth;

	public UIView(GameCharacter character) {
		this.character = character;
		maxHealth=character.getHealth();
	}

	public void setCharacter(GameCharacter character) {
		this.character=character;
	}

	public void render(GameContainer gameContainer){
		Graphics g = gameContainer.getGraphics();

		//get percentage of life left;
		double currentHealth = (double)(character.getHealth())/maxHealth;
                int yPos = gameContainer.getHeight()-30;
                
                final int height=10;
                final double fullLength = 620;
                
		Rectangle lifebarGreen = new Rectangle(10, yPos, (int) (currentHealth*fullLength), height);
		Rectangle lifebarRed = new Rectangle(10, yPos, (int) fullLength, height);

		g.setColor(Color.white);
		g.drawString("Life", 10, gameContainer.getHeight() - 50);
		g.setColor(Color.red);
		g.fill(lifebarRed);
		g.setColor(Color.green);
		g.fill(lifebarGreen);

	}
}
