package eg.edu.guc.santorini.tiles;

import java.util.ArrayList;

import eg.edu.guc.santorini.utilities.Location;

public class Cube extends Piece {


	public Cube(Location l) {
		super(l);
	}

	public ArrayList<Location> possibleMoves() {
		getPossibleMoves().clear();
		//initially it can move to any location
		getPossibleMoves().add(new 
				Location(getLocation().getY(), getLocation().getX() + 1));
		getPossibleMoves().add(new
				Location(getLocation().getY(), getLocation().getX() - 1));
		getPossibleMoves().add(new
				Location(getLocation().getY() - 1, getLocation().getX()));
		getPossibleMoves().add(new 
				Location(getLocation().getY() + 1, getLocation().getX()));
		//if it cannot be placed somewhere remove this place
		//check corners 
		if (getLocation().getY() == 0 && getLocation().getX() == 0) {
			getPossibleMoves().remove(2);
			getPossibleMoves().remove(1);
		} else if (getLocation().getY() == 4 && getLocation().getX() == 0) {
			getPossibleMoves().remove(3);
			getPossibleMoves().remove(1);
		} else if (getLocation().getY() == 4 && getLocation().getX() == 4) {
			getPossibleMoves().remove(3);
			getPossibleMoves().remove(0);

		} else if (getLocation().getY() == 0 && getLocation().getX() == 4) {
			getPossibleMoves().remove(2);
			getPossibleMoves().remove(0);

	    //Checking Sides
		} else if (getLocation().getX() == 0) {
			getPossibleMoves().remove(1);

		} else if (getLocation().getX() == 4) {
			getPossibleMoves().remove(0);

		} else if (getLocation().getY() == 0) {
			getPossibleMoves().remove(2);

		} else if (getLocation().getY() == 4) {
			getPossibleMoves().remove(3);


		}
		return getPossibleMoves(); }
	
	
}
