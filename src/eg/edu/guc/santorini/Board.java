package eg.edu.guc.santorini;


import eg.edu.guc.santorini.exceptions.DeadCellMoveException;
import eg.edu.guc.santorini.exceptions.DeadCellPlacementException;
import eg.edu.guc.santorini.exceptions.GameOverMoveException;
import eg.edu.guc.santorini.exceptions.GameOverPlacementException;
import eg.edu.guc.santorini.exceptions.InvalidLevelMoveException;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.exceptions.MoveAfterMoveException;
import eg.edu.guc.santorini.exceptions.OutOfTurnMoveException;
import eg.edu.guc.santorini.exceptions.PieceInMovementException;
import eg.edu.guc.santorini.exceptions.PieceInPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.tiles.*;
import eg.edu.guc.santorini.utilities.Location;

public class Board implements BoardInterface {
	private Player p1;
	private Player p2;
	private Location[][] board;

	public Board(Player p1, Player p2) {


		board = new Location[5][5];
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				board[i][j] = new Location(i, j);
			}
		}
		this.p1 = p1;
		p1.getT1().setLocation(board[0][0]);
		p1.getT2().setLocation(board[4][1]);
		p1.getT1().getLocation().setContainsPiece(true);
		p1.getT2().getLocation().setContainsPiece(true);

		this.p2 = p2;
		p2.getT1().setLocation(board[0][3]);
		p2.getT2().setLocation(board[4][4]);
		p2.getT1().getLocation().setContainsPiece(true);
		p2.getT2().getLocation().setContainsPiece(true);
		//Player 1 start the game
		p1.setTurn(true);
		p1.setMove(true);
		p1.setPlace(false);
		p2.setTurn(false);
		p2.setMove(false);
		p2.setPlace(false);
	}

	public Player getP1() {
		return p1;
	}

	public void setP1(Player p1) {
		this.p1 = p1;
	}

	public Player getP2() {
		return p2;
	}

	public void setP2(Player p2) {
		this.p2 = p2;
	}

	public Location[][] getBoard() {
		return board;
	}

	public void setBoard(Location[][] board) {
		this.board = board;
	}

	public void move(Piece piece, Location newLocation) throws InvalidMoveException {
		if(isGameOver()) {
			throw new GameOverMoveException();
		}
		if (piece.getLocation().equals(p1.getT1().getLocation()) 
				|| piece.getLocation().equals(p1.getT2().getLocation())) {
			if (!p1.isTurn()) { throw new OutOfTurnMoveException(); }
			if (!p1.isMove()) { throw new MoveAfterMoveException(); }
		}
		else {
			if (!p2.isTurn()) { throw new OutOfTurnMoveException(); } //LOOK AT THIS
			if (!p2.isMove()) { throw new MoveAfterMoveException(); }
		} // canMove method body
		if (newLocation.isContainsPiece()) {throw new PieceInMovementException(); }
		if (board[newLocation.getY()][newLocation.getX()].getLevel() == 4) { //it is a dome 
			throw new DeadCellMoveException();
		}
		if (board[newLocation.getY()][newLocation.getX()].getLevel()
				- piece.getLocation().getLevel() > 1) { // a higher level than it should
			throw new InvalidLevelMoveException();
		}
		if (p1.getT1().getLocation().equals(newLocation) 
				|| p1.getT2().getLocation().equals(newLocation) 
				|| p2.getT1().getLocation().equals(newLocation) 
				|| p2.getT2().getLocation().equals(newLocation)) {
			throw new PieceInMovementException();
		}
		if (!piece.possibleMoves().contains(newLocation)) {
			throw new InvalidMoveException(); 
		}
		if (piece.getLocation().equals(p1.getT1().getLocation()) //CHECK THIS ONE CHECK CHECK !!
				|| piece.getLocation().equals(p1.getT2().getLocation())) {
			p1.setMove(false); //he moved so can't move again
			p1.setPlace(true); //he can place now
			p2.setMove(true); //player 2 can move after placing of p1
		}
		else { //same but in case of p2 turn
			p2.setMove(false);
			p2.setPlace(true);
			p1.setMove(true);
		}
		piece.getLocation().setContainsPiece(false); 
		piece.setLocation(board[newLocation.getY()][newLocation.getX()]); 
		//change his position on board
		piece.getLocation().setContainsPiece(true);
	}

	public Player getWinner() {
		if (isWinner(p1)) {
			return p1;
		}
		if (isWinner(p2)) {
			return p2;
		}
		return null;
	}

	public boolean isWinner(Player player) { //handle
		if (player == p1) {
			return (player.getT1().getLocation().getLevel() == 3 
					|| player.getT2().getLocation().getLevel() == 3) || hasNoMoves(p2)
					&& p2.isTurn();
		}
		else {
			return (player.getT1().getLocation().getLevel() == 3 
					|| player.getT2().getLocation().getLevel() == 3) || hasNoMoves(p1) 
					&& p1.isTurn();
		}
	}

	public boolean isGameOver() {
		return (isWinner(p1)) || (isWinner(p2));
	}

	public boolean hasNoMoves(Player player) {
		for (int i = 0; i < player.getT1().possibleMoves().size(); i++) {
			if (canMove(player.getT1(), player.getT1().possibleMoves().get(i))) {
				return false;
			}
		}
		for (int i = 0; i < player.getT2().possibleMoves().size(); i++) {
			if (canMove(player.getT2(), player.getT2().possibleMoves().get(i))) {
				return false;
			}
		}

		return true;
	}


	public Player getTurn() {
		if (p1.isTurn()) {
			return p1;
		}
		else {
			return p2;
		}
	}

	public void place(Piece piece, Location newLocation) 
			throws InvalidPlacementException {
		if(!isGameOver()) {
			if (piece.getLocation().equals(p1.getT1().getLocation()) 
					|| piece.getLocation().equals(p1.getT2().getLocation())) {
				if (!p1.isPlace()) {
					throw new InvalidPlacementException(); 
				}
				//either because of its not his turn or he did place after place or place before moving		
			}
			else {
				if (!p2.isPlace()) {
					throw new InvalidPlacementException();
				}
			}
			if (canPlace(piece, newLocation)) {
				if (piece == p1.getT1() || piece == p1.getT2()) {
					p1.setTurn(false); //his turn is over
					p2.setTurn(true); // p2 turn
					p1.setPlace(false); //he cant place he must move first 
					p2.setMove(true); // he can move 
				}
				else {
					p2.setTurn(false);
					p1.setTurn(true);
					p1.setMove(true); // same for other player
					p2.setPlace(false);
				}
				board[newLocation.getY()][newLocation.getX()].incrementLevel(); //place on the board
			}
			else {
				if (!piece.possiblePlacements().contains(newLocation)) {
					throw new InvalidPlacementException();
				}
				if (board[newLocation.getY()][newLocation.getX()].getLevel() >= 4) { 
					throw new DeadCellPlacementException(); //domed cell
				}
				if (p1.getT1().getLocation().equals(newLocation) 
						|| p1.getT2().getLocation().equals(newLocation) 
						|| p2.getT1().getLocation().equals(newLocation) 
						|| p2.getT2().getLocation().equals(newLocation)) {
					throw new PieceInPlacementException();
				}
			}
		}else {
			throw new GameOverPlacementException();
		}
	}

	public boolean canMove(Piece piece, Location location) { 
		if (location.isContainsPiece()) {
			return false;
		}
		if (board[location.getY()][location.getX()].getLevel() == 4) { //it is a dome 
			return false;
		}
		if (board[location.getY()][location.getX()].getLevel()
				- piece.getLocation().getLevel() > 1) { // a higher level than it should
			return false;
		}
		if (p1.getT1().getLocation().equals(location) || p1.getT2().getLocation().equals(location) 
				|| p2.getT1().getLocation().equals(location) 
				|| p2.getT2().getLocation().equals(location)) {
			return false;
		}
		if (!piece.possibleMoves().contains(location)) {
			return false; 
		}
		return true;
	}



	public boolean canPlace(Piece piece, Location location) {
		//piece.possiblePlacements().clear();
		if (p1.getT1().getLocation().equals(location) || p1.getT2().getLocation().equals(location) 
				|| p2.getT1().getLocation().equals(location) 
				|| p2.getT2().getLocation().equals(location)) {
			return false;
		}
		return piece.possiblePlacements().contains(location) 
				&& board[location.getY()][location.getX()].getLevel() < 4; //not a dome 
	}

	public String [][] display() {
		String [][] s = new String [5][5];
		String r = "";
		for (int i = 0; i < 5; i++) {
			for (int j = 0; j < 5; j++) {
				s[i][j] = board[i][j].getLevel() + r;
				if (board[i][j].isContainsPiece()) {
					if (p1.getT1().getLocation() == board[i][j]
							|| p1.getT2().getLocation() == board[i][j]) {
						if (p1.getT1() instanceof Cube || p1.getT2() instanceof Cube) {
							s[i][j] = board[i][j].getLevel() + r + "C" + "1";
						}
						else {
							s[i][j] = board[i][j].getLevel() + r + "P" + "1";
						}
					}
					else {
						if (p2.getT1() instanceof Cube || p2.getT2() instanceof Cube) {
							s[i][j] = board[i][j].getLevel() + r + "C" + "2";
						}
						else {
							s[i][j] = board[i][j].getLevel() + r + "P" + "2";

						}
					}
				}
			}
		}
		return s;
	}

//		public static void main(String [] args) throws InvalidMoveException,
//		InvalidPlacementException {
//			Player p1 = new Player("Player 1", 2);
//			Player p2 = new Player("Player 2", 1);
//			Board board = new Board(p1, p2);
//
//			board.move(p1.getT1(), new Location(1, 1));
//			board.place(p1.getT1(), new Location(0, 2));
//
//			board.move(p2.getT1(), new Location(0, 2));
//			board.place(p2.getT1(), new Location(0, 1));
//
//			board.move(p1.getT1(), new Location(0, 0));
//			board.place(p1.getT1(), new Location(0, 1));
//
//			board.move(p2.getT1(), new Location(0, 1));
//			board.place(p2.getT1(), new Location(0, 2));
//
//			board.move(p1.getT1(), new Location(1, 1));
//			board.place(p1.getT1(), new Location(0, 2));
//
//			board.move(p2.getT1(), new Location(0, 2));
//			board.place(p2.getT1(), new Location(1, 2));
//			
//			System.out.println(board.isGameOver());
//		}
}