package eg.edu.guc.santorini.players;

import eg.edu.guc.santorini.tiles.Cube;
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.tiles.Pyramid;
import eg.edu.guc.santorini.utilities.Location;

public class Player {
	private String name;
	private int piece;
	private Piece t1;
	private Piece t2;
	private boolean turn;  //to know is it his turn or not
	private boolean move;  // to know he is finishing move or not
	private boolean place;  // to know he is finishing his place


	public Player(String name, int piece) {
		this.name = name;
		if (piece == 1) {
			t1 = new Cube(new Location());
			t2 = new Cube(new Location());
		}
		else {
			if (piece == 2) {
				t1 = new Pyramid(new Location());
				t2 = new Pyramid(new Location());
			}
			else {
				return;
			}
		}
	}
	
	public boolean isPlace() {
		return place;
	}



	public void setPlace(boolean place) {
		this.place = place;
	}




	public boolean isTurn() {
		return turn;
	}



	public void setTurn(boolean turn) {
		this.turn = turn;
	}



	public boolean isMove() {
		return move;
	}



	public void setMove(boolean move) {
		this.move = move;
	}



	public Piece getT1() {
		return t1;
	}

	public void setT1(Piece t1) {
		this.t1 = t1;
	}

	public Piece getT2() {
		return t2;
	}

	public void setT2(Piece t2) {
		this.t2 = t2;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getPiece() {
		return piece;
	}

	public void setPiece(int piece) {
		this.piece = piece;
	}
}
