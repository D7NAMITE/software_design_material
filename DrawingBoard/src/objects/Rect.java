package objects;

import java.awt.Color;
import java.awt.Graphics;

public class Rect extends GObject  {

	private Color color;
	
	public Rect(int x, int y, int width, int height, Color color) {
		super(x, y, width, height);
		this.color = color;
	}

	@Override
	public void paintObject(Graphics g) {
		g.setColor(color);
		g.fillRect(x, y, width, height);
	}
	
	@Override
	public void paintLabel(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Rectangle", x, y);
	}
	
}
