package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public abstract class Piece implements PieceInterface {
	
	private Location location;
	private ArrayList<Location> possibleMoves;
	private ArrayList<Location> possiblePlacements;

	public Piece(Location l) {
	
		location = l;
		possibleMoves = new ArrayList<Location>();
		possiblePlacements = new ArrayList<Location>();
	}

	public Location getLocation() {
		return location;
	}

	public void setLocation(Location location) {
		this.location = location;
		
	}

	public ArrayList<Location> getPossibleMoves() {
		return possibleMoves;
	}
	public void setPossibleMoves(ArrayList<Location> possibleMoves) {
		this.possibleMoves = possibleMoves;
	}
	public ArrayList<Location> getPossiblePlacements() {
		return possiblePlacements;
	}
	public void setPossiblePlacements(ArrayList<Location> possiblePlacements) {
		this.possiblePlacements = possiblePlacements;
	}
	public ArrayList<Location> possiblePlacements() {
		possiblePlacements.clear();
		if (location.getY() == 0 && location.getX() == 0) {
			upperLeftCorner();
			return possiblePlacements;
		}
		if (location.getY() == 4 && location.getX() == 4) {
			lowerRightCorner(); // to increase efficiency recall corner positions from methods
			return possiblePlacements; 
		}
		if (location.getY() == 0 && location.getX() == 4) {
			possiblePlacements.clear();
			lowerLeftCorner();
			return possiblePlacements;
		}
		if (location.getY() == 4 && location.getX() == 0) {
			upperRightCorner();
			return possiblePlacements;
		}
		possiblePlacements.add(new Location(location.getY(), location.getX() + 1));
		possiblePlacements.add(new Location(location.getY(), location.getX() - 1));
		possiblePlacements.add(new Location(location.getY() + 1, location.getX()));
		possiblePlacements.add(new Location(location.getY() - 1, location.getX()));
		possiblePlacements.add(new Location(location.getY() + 1, location.getX() + 1));
		possiblePlacements.add(new Location(location.getY() + 1, location.getX() - 1));
		possiblePlacements.add(new Location(location.getY() - 1, location.getX() + 1));
		possiblePlacements.add(new Location(location.getY() - 1, location.getX() - 1));
		if (location.getY() == 0) { //else it is more efficient to remove locations
			possiblePlacements.remove(7);
			possiblePlacements.remove(6);
			possiblePlacements.remove(3);
		}
		if (location.getY() == 4) {
			possiblePlacements.remove(5);
			possiblePlacements.remove(4);
			possiblePlacements.remove(2);
		}
		if (location.getX() == 0) {
			possiblePlacements.remove(7);
			possiblePlacements.remove(5);
			possiblePlacements.remove(1);
		}
		if (location.getX() == 4) {
			possiblePlacements.remove(6);
			possiblePlacements.remove(4);
			possiblePlacements.remove(0);
		}

		return possiblePlacements;
	}

	public void upperRightCorner() {
		possiblePlacements.add(new Location(location.getY(), location.getX() + 1));
		possiblePlacements.add(new Location(location.getY() - 1, location.getX()));
		possiblePlacements.add(new Location(location.getY() - 1, location.getX() + 1));
	}

	public void lowerLeftCorner() {
		possiblePlacements.add(new Location(location.getY(), location.getX() - 1));
		possiblePlacements.add(new Location(location.getY() + 1, location.getX()));
		possiblePlacements.add(new Location(location.getY() + 1, location.getX() - 1));
	}

	public void lowerRightCorner() {
		possiblePlacements.add(new Location(location.getY(), location.getX() - 1));
		possiblePlacements.add(new Location(location.getY() - 1, location.getX()));
		possiblePlacements.add(new Location(location.getY() - 1, location.getX() - 1));
	}

	public void upperLeftCorner() {
		possiblePlacements.add(new Location(location.getY(), location.getX() + 1));
		possiblePlacements.add(new Location(location.getY() + 1, location.getX()));
		possiblePlacements.add(new Location(location.getY() + 1, location.getX() + 1));
	}


}
