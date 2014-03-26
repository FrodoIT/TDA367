import java.awt.Point;


public interface ICharacter {
	public int getHealth();
	public Point getPosition();
	public int getDamage();
	public int getMovementSpeed();
	public void move(Direction direction);
}
