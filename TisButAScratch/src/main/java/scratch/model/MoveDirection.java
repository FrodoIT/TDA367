package scratch.model;
/**
 * @author Alma Ottedag
 * revised Ivar Josefsson 2014-03-29
 */
public enum MoveDirection {
	NORTH(0, -1), SOUTH(0, 1), WEST(-1,0), EAST(1,0), NORTHEAST(1,-1), NORTHWEST(-1,-1), SOUTHEAST(1,1), SOUTHWEST(-1,1), NONE(0,0);

	private int x, y;

	MoveDirection(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX(){
		return x;
	}

	public int getY() {
		return y;
	}
}
