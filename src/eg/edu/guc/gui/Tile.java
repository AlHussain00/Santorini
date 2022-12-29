package eg.edu.guc.gui;

import javax.swing.*;

import eg.edu.guc.santorini.utilities.Location;

@SuppressWarnings("serial")
public class Tile extends JLabel {
	Location locations;
	int layer;
	public Tile() { 
		super();
	}
	
	public Tile(ImageIcon icon) {
		super(icon);
		locations = new Location();
		layer = 0;
	}

	public Location getLocations() {
		return locations;
	}

	public void setLocations(Location locations) {
		this.locations = locations;
	}

	public int getLayer() {
		return layer;
	}

	public void setLayer(int layer) {
		this.layer = layer;
	}
}
