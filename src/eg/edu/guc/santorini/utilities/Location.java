package eg.edu.guc.santorini.utilities;

public class Location {
	private int level;
	private int x;
	private int y;
	private boolean containsPiece; //contains a piece 
	
	public Location() {

	}

	public Location(int y, int x) {
		level = 0;
		this.y = y;
		this.x = x;
	}
	
	public boolean isContainsPiece() {
		return containsPiece;
	}



	public void setContainsPiece(boolean cantains) {
		this.containsPiece = cantains;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getLevel() {
		return level;
	}
	public void incrementLevel() {
		level++;
	}

	public void setLevel(int level) {
		this.level = level;
	}
	public boolean equals(Object loc) {
	Location l = (Location) loc;
		return this.getX() == l.getX() && this.getY() == l.getY(); 	
	}

	
	
	
	
}
