package objects;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.List;

public class CompositeGObject extends GObject {

	private List<GObject> gObjects;

	public CompositeGObject() {
		super(0, 0, 0, 0);
		gObjects = new ArrayList<GObject>();
	}

	public void add(GObject gObject) {
		gObjects.add(gObject);
	}

	public void remove(GObject gObject) {
		gObjects.remove(gObject);
	}

	@Override
	public void move(int dX, int dY) {
		for (GObject o : gObjects) {
			o.move(dX, dY);
		}
		recalculateRegion();
	}
	
	public void recalculateRegion() {
		x = gObjects.stream().mapToInt(o -> o.x).min().orElse(0);
		y = gObjects.stream().mapToInt(o -> o.y).min().orElse(0);
		width = gObjects.stream().mapToInt(o -> o.x + o.width).max().orElse(0) - x;
		height = gObjects.stream().mapToInt(o -> o.y + o.height).max().orElse(0) - y;
	}

	@Override
	public void paintObject(Graphics g) {
		for (GObject o : gObjects) {
			o.paintObject(g);
		}
	}

	@Override
	public void paintLabel(Graphics g) {
		g.setColor(Color.black);
		g.drawString("Composite", x, y);
	}
	
}
