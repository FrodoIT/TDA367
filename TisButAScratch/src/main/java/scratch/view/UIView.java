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
	private final int maxHealth, scalingFactor;

	public UIView(GameCharacter character) {
		this.character = character;
		maxHealth=character.getHealth();
		scalingFactor=30;
	}

	public void setCharacter(GameCharacter character) {
		this.character=character;
	}

	public void render(GameContainer gameContainer){
		Graphics g = gameContainer.getGraphics();
		int currentHealth = character.getHealth();

		Rectangle lifebarGreen = new Rectangle(10, gameContainer.getHeight()-30, currentHealth*scalingFactor, 10);
		Rectangle lifebarRed = new Rectangle(10, gameContainer.getHeight()-30, maxHealth*scalingFactor, 10);
		g.setColor(Color.white);
		g.drawString("Life", 10, gameContainer.getHeight()-50);
		g.setColor(Color.red);
		g.fill(lifebarRed);
		g.setColor(Color.green);
		g.fill(lifebarGreen);

	}
}
