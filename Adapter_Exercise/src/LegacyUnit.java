import java.util.Random;

public class LegacyUnit {

	public static final float MAX_V = 1.0f;
	public static final int SIZE = 10;

	private float x, y;
	private float vx, vy;

	public LegacyUnit() {
		Random r = new Random();
		this.x = r.nextInt(Game.SIZE);
		this.y = r.nextInt(Game.SIZE);
		this.vx = r.nextFloat() * MAX_V;
		this.vy = r.nextFloat() * MAX_V;
	}

	public void walk() {
		x += vx;
		y += vy;
		if (x <= 0 || x >= Game.SIZE) {
			vx *= -1;
		}
		if (y <= 0 || y >= Game.SIZE) {
			vy *= -1;
		}
	}

	public int getPositionX() {
		return (int) x;
	}

	public int getPositionY() {
		return (int) y;
	}

}
