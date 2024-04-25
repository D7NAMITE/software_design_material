package objects;

import java.awt.Color;
import java.awt.Graphics;

public class Oval extends GObject {

	private Color color;
	
	public Oval(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}

	@Override
	public void paintObject(Graphics g) {
		g.setColor(color);
		g.fillOval(x, y, width, height);
	}
	
	@Override
	public void paintLabel(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Oval", x, y);
	}
	
}
