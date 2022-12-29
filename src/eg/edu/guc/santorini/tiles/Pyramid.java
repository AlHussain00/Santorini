package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Pyramid extends Piece {
	public Pyramid(Location l) {
		super(l);
	}

	public ArrayList<Location> possibleMoves() {
		getPossibleMoves().clear();
		Location l1 = getLocation();  //only to reduce line's length
		// check the 4 corners
		if (getLocation().getY() == 0 && getLocation().getX() == 0) {
			getPossibleMoves().add(new Location(l1.getY() + 1, l1.getX() + 1));

		} else if (getLocation().getY() == 4 && getLocation().getX() == 0) {
			getPossibleMoves().add(new Location(l1.getY() - 1, l1.getX() + 1));

		} else if (getLocation().getY() == 4 && getLocation().getX() == 4) {
			getPossibleMoves().add(new Location(l1.getY() - 1, l1.getX() - 1));

		} else if (getLocation().getY() == 0 && getLocation().getX() == 4) {
			getPossibleMoves().add(new Location(l1.getY() + 1, l1.getX() - 1));
		}
		// Checking Sides
		else if ((getLocation().getY() > 0 && getLocation().getX() == 0)) {
			getPossibleMoves().add(new Location(l1.getY() + 1, l1.getX() + 1));
			getPossibleMoves().add(new Location(l1.getY() - 1, l1.getX() + 1));
		} else if ((getLocation().getY() > 0 && l1.getX() == 4)) {
			getPossibleMoves().add(new Location(l1.getY() + 1, l1.getX() - 1));
			getPossibleMoves().add(new Location(l1.getY() - 1, l1.getX() - 1));

		} else if ((getLocation().getY() == 0) && (getLocation().getX() < 4)) {
			getPossibleMoves().add(new Location(l1.getY() + 1, l1.getX() + 1));
			getPossibleMoves().add(new Location(l1.getY() + 1, l1.getX() - 1));
		} else if (getLocation().getY() == 4 && (getLocation().getX() < 4)) {
			getPossibleMoves().add(new Location(l1.getY() - 1, l1.getX() + 1));
			getPossibleMoves().add(new Location(l1.getY() - 1, l1.getX() - 1));
		} else {
			// if it didn't enter any condition then it is possible to move in 4 directions	
			getPossibleMoves().add(new Location(getLocation().getY() + 1
					, getLocation().getX() + 1));
			getPossibleMoves().add(new Location(getLocation().getY() + 1
					, getLocation().getX() - 1));
			getPossibleMoves().add(new Location(getLocation().getY() - 1
					, getLocation().getX() + 1));
			getPossibleMoves().add(new Location(getLocation().getY() - 1
					, getLocation().getX() - 1));
		}
		return getPossibleMoves(); }
}
