import java.util.Random;

public class Unit implements IUnit {

	public static final int MAX_HEALTH = 20;
	public static final float MAX_V = 1.0f;
	public static final int SIZE = 10;

	private String name;
	private int hp;
	private float x, y;
	private float vx, vy;

	public Unit(String name) {
		this.name = name;
		Random r = new Random();
		this.hp = 1 + r.nextInt(MAX_HEALTH - 1);
		this.x = r.nextInt(Game.SIZE);
		this.y = r.nextInt(Game.SIZE);
		this.vx = r.nextFloat() * MAX_V;
		this.vy = r.nextFloat() * MAX_V;
	}

	public void move() {
		if (dead()) {
			return;
		}
		x += vx;
		y += vy;
		if (x <= 0 || x >= Game.SIZE) {
			vx *= -1;
			hp--;
		}
		if (y <= 0 || y >= Game.SIZE) {
			vy *= -1;
			hp--;
		}
	}

	public int getX() {
		return (int) x;
	}

	public int getY() {
		return (int) y;
	}

	public String getName() {
		return name;
	}

	public int getHealth() {
		return hp;
	}

	public boolean dead() {
		return hp <= 0;
	}

}
