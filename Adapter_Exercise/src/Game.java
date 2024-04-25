import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class Game extends JFrame {

	public static final int SIZE = 600;

	private JPanel panel;
	private List<IUnit> units = new ArrayList<IUnit>();
	private Thread thread;
	private boolean running;

	public Game() {
		initGameData();
		panel = new JPanel() {
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				drawBackground(g);
				drawUnits(g);
			}
		};
		panel.setPreferredSize(new Dimension(SIZE, SIZE));
		add(panel);
		pack();
	}

	private void initGameData() {
		for (int i = 0; i < 20; i++) {
			Unit unit = new Unit("U" + (i + 1));
			units.add(unit);
		}

		for (int i = 0; i < 20; i++) {
			AdapterUnit adapterUnit = new AdapterUnit(new LegacyUnit());
			units.add(adapterUnit);
		}
	}

	private void start() {
		running = true;
		thread = new Thread() {
			@Override
			public void run() {
				super.run();
				while (running) {
					for (IUnit unit : units) {
						unit.move();
					}
					panel.repaint();
					try {
						Thread.sleep(1000 / 60);
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		};
		thread.start();
	}

	private void drawBackground(Graphics g) {
		g.setColor(Color.white);
		g.fillRect(0, 0, SIZE, SIZE);
	}

	private void drawUnits(Graphics g) {
		for (IUnit unit : units) {
			if (unit.dead()) {
				continue;
			}
			// Circle
			g.setColor(Color.black);
			g.fillOval(unit.getX(), unit.getY(), Unit.SIZE, Unit.SIZE);
			// Name
			g.setColor(Color.gray);
			g.drawString(unit.getName() + "", unit.getX() - 2, unit.getY() - 5);
			// Hp
			g.setColor(Color.red);
			g.drawString("HP:" + unit.getHealth(), unit.getX() - 8, unit.getY() + 25);
		}
	}

	public static void main(String[] args) {
		Game game = new Game();
		game.setVisible(true);
		game.setDefaultCloseOperation(EXIT_ON_CLOSE);
		game.start();
	}

}
