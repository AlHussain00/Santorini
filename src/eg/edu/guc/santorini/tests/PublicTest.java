package eg.edu.guc.santorini.tests;

import static org.junit.Assert.*;

import java.util.ArrayList;

import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.BoardInterface;
import eg.edu.guc.santorini.exceptions.InvalidMoveException;
import eg.edu.guc.santorini.exceptions.InvalidPlacementException;
import eg.edu.guc.santorini.players.Player;
import eg.edu.guc.santorini.utilities.Location;

public class PublicTest {

	@org.junit.Test(timeout = 1000)
	public void testBoardLayOut() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		String[][] s = new String[BoardInterface.SIDE][BoardInterface.SIDE];
		s[0][0] = "0C1";
		s[0][1] = "0";
		s[0][2] = "0";
		s[0][3] = "0P2";
		s[0][4] = "0";

		s[1][0] = "0";
		s[1][1] = "0";
		s[1][2] = "0";
		s[1][3] = "0";
		s[1][4] = "0";

		s[2][0] = "0";
		s[2][1] = "0";
		s[2][2] = "0";
		s[2][3] = "0";
		s[2][4] = "0";

		s[3][0] = "0";
		s[3][1] = "0";
		s[3][2] = "0";
		s[3][3] = "0";
		s[3][4] = "0";

		s[4][0] = "0";
		s[4][1] = "0C1";
		s[4][2] = "0";
		s[4][3] = "0";
		s[4][4] = "0P2";
		assertTrue("Board should have the initial setup.",
				stringArrayEquals(s, board.display()));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1PossibleMovesT1() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		new Board(p1, p2);
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		possibleMoves.add(new Location(0, 1));
		possibleMoves.add(new Location(1, 0));
		assertTrue("Player 1 1st piece could play to the left and down!",
				arrayListsEqual(possibleMoves, p1.getT1().possibleMoves()));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1PossibleMovesT2() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		new Board(p1, p2);
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		possibleMoves.add(new Location(3, 1));
		possibleMoves.add(new Location(4, 0));
		possibleMoves.add(new Location(4, 2));
		assertTrue("Player 1 2nd piece could play to the left, right, and up!",
				arrayListsEqual(possibleMoves, p1.getT2().possibleMoves()));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1canMoveRight() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertTrue("Player 1 1st piece can move to the right!",
				(board.canMove(p1.getT1(), new Location(0, 1))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1canMoveWrongDiagonal() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertFalse("Player 1 1st piece can not move diagonally!",
				(board.canMove(p1.getT1(), new Location(1, 1))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1canMoveWrongFar() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertFalse("Player 1 1st piece can not move 2 steps!",
				(board.canMove(p1.getT1(), new Location(0, 2))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1MoveRight() throws InvalidMoveException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(0, 1));
		String[][] s = new String[BoardInterface.SIDE][BoardInterface.SIDE];
		s[0][0] = "0";
		s[0][1] = "0C1";
		s[0][2] = "0";
		s[0][3] = "0P2";
		s[0][4] = "0";

		s[1][0] = "0";
		s[1][1] = "0";
		s[1][2] = "0";
		s[1][3] = "0";
		s[1][4] = "0";

		s[2][0] = "0";
		s[2][1] = "0";
		s[2][2] = "0";
		s[2][3] = "0";
		s[2][4] = "0";

		s[3][0] = "0";
		s[3][1] = "0";
		s[3][2] = "0";
		s[3][3] = "0";
		s[3][4] = "0";

		s[4][0] = "0";
		s[4][1] = "0C1";
		s[4][2] = "0";
		s[4][3] = "0";
		s[4][4] = "0P2";
		assertTrue("Player 1 1st piece should have moved to the right!",
				stringArrayEquals(s, board.display()));
	}
	
	@org.junit.Test(timeout = 1000, expected = InvalidMoveException.class)
	public void testPlayer1MoveAfterMove() throws InvalidMoveException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(0, 1));
		board.move(p1.getT1(), new Location(0, 2));
	}
	
	@org.junit.Test(timeout = 1000, expected = InvalidMoveException.class)
	public void testPlayer1MoveAfterPlace() throws InvalidMoveException, InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(0, 1));
		board.place(p1.getT1(), new Location(0, 2));
		board.move(p1.getT1(), new Location(0, 2));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidMoveException.class)
	public void testPlayer1MoveWrongDiagonal() throws InvalidMoveException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 1));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidMoveException.class)
	public void testPlayer1moveWrongFar() throws InvalidMoveException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(0, 2));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2PossibleMovesT1() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		new Board(p1, p2);
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		possibleMoves.add(new Location(1, 2));
		possibleMoves.add(new Location(1, 4));
		assertTrue(
				"Player 2 1st piece could play to the left down, right down !",
				arrayListsEqual(possibleMoves, p2.getT1().possibleMoves()));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2PossibleMovesT2() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		new Board(p1, p2);
		ArrayList<Location> possibleMoves = new ArrayList<Location>();
		possibleMoves.add(new Location(3, 3));
		assertTrue("Player 2 2nd piece could play to the left up!",
				arrayListsEqual(possibleMoves, p2.getT2().possibleMoves()));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2canMoveWrongRect() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertFalse("Player 2 2nd piece could play up!",
				(board.canMove(p2.getT2(), new Location(3, 4))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2canMoveDiagonal() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertTrue("Player 2 2nd piece can not move diagonally!",
				(board.canMove(p2.getT2(), new Location(3, 3))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2canMoveWrongFar() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertFalse("Player 2 2nd piece can not move two steps!!",
				(board.canMove(p2.getT2(), new Location(2, 2))));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidMoveException.class)
	public void testPlayer2MoveWrongTurn() throws InvalidMoveException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p2.getT2(), new Location(3, 3));
	}
	
	@org.junit.Test(timeout = 1000, expected = InvalidMoveException.class)
	public void testPlayer2MoveWrongRect() throws InvalidMoveException, InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(1, 1));
		board.move(p2.getT1(), new Location(0, 4));
	}
	
	@org.junit.Test(timeout = 1000, expected = InvalidMoveException.class)
	public void testPlayer2MoveAfterMove() throws InvalidMoveException, InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(1, 1));
		board.move(p2.getT1(), new Location(1, 2));
		board.move(p2.getT1(), new Location(2, 3));
	}
	
	@org.junit.Test(timeout = 1000, expected = InvalidMoveException.class)
	public void testPlayer2MoveAfterPlace() throws InvalidMoveException, InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(1, 1));
		board.move(p2.getT1(), new Location(1, 4));
		board.place(p2.getT1(), new Location(0, 3));
		board.move(p2.getT1(), new Location(0, 3));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2MoveDiagonal() throws InvalidMoveException, InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(1, 1));
		board.move(p2.getT1(), new Location(1, 2));
		String[][] s = new String[BoardInterface.SIDE][BoardInterface.SIDE];
		s[0][0] = "0";
		s[0][1] = "0";
		s[0][2] = "0";
		s[0][3] = "0";
		s[0][4] = "0";

		s[1][0] = "0C1";
		s[1][1] = "1";
		s[1][2] = "0P2";
		s[1][3] = "0";
		s[1][4] = "0";

		s[2][0] = "0";
		s[2][1] = "0";
		s[2][2] = "0";
		s[2][3] = "0";
		s[2][4] = "0";

		s[3][0] = "0";
		s[3][1] = "0";
		s[3][2] = "0";
		s[3][3] = "0";
		s[3][4] = "0";

		s[4][0] = "0";
		s[4][1] = "0C1";
		s[4][2] = "0";
		s[4][3] = "0";
		s[4][4] = "0P2";
		assertTrue("Player 1 1st piece should have moved to the right down!",
				stringArrayEquals(s, board.display()));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidMoveException.class)
	public void testPlayer2moveWrongFar() throws InvalidMoveException, InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(1, 1));
		board.move(p1.getT1(), new Location(2, 1));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1PossiblePlacesT1() throws InvalidMoveException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(0, 1));
		ArrayList<Location> possiblePlaces = new ArrayList<Location>();
		possiblePlaces.add(new Location(0, 0));
		possiblePlaces.add(new Location(0, 2));
		possiblePlaces.add(new Location(1, 0));
		possiblePlaces.add(new Location(1, 1));
		possiblePlaces.add(new Location(1, 2));
		assertTrue(
				"Player 1 1st tile could place to the left, right, down diagonals, and down!",
				arrayListsEqual(possiblePlaces, p1.getT1().possiblePlacements()));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1PossiblePlacesT2() throws InvalidMoveException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT2(), new Location(3, 1));
		ArrayList<Location> possiblePlaces = new ArrayList<Location>();
		possiblePlaces.add(new Location(2, 0));
		possiblePlaces.add(new Location(2, 1));
		possiblePlaces.add(new Location(2, 2));
		possiblePlaces.add(new Location(3, 0));
		possiblePlaces.add(new Location(3, 2));
		possiblePlaces.add(new Location(4, 0));
		possiblePlaces.add(new Location(4, 1));
		possiblePlaces.add(new Location(4, 2));
		assertTrue(
				"Player 1 1st tile could place to the left, right, down diagonals, and down!",
				arrayListsEqual(possiblePlaces, p1.getT2().possiblePlacements()));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1canPlaceRight() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertTrue("Player 1 1st tile could place to the right!",
				(board.canPlace(p1.getT1(), new Location(0, 1))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1canPlaceRightDiagonal() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertTrue("Player 1 1st tile can palce diagonally!",
				(board.canPlace(p1.getT1(), new Location(1, 1))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1canPlaceWrongFar() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertFalse("Player 1 1st tile can not move 2 steps!",
				(board.canPlace(p1.getT1(), new Location(2, 2))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1PlaceRight() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(0, 1));
		board.place(p1.getT1(), new Location(0, 0));
		String[][] s = new String[BoardInterface.SIDE][BoardInterface.SIDE];
		s[0][0] = "1";
		s[0][1] = "0C1";
		s[0][2] = "0";
		s[0][3] = "0P2";
		s[0][4] = "0";

		s[1][0] = "0";
		s[1][1] = "0";
		s[1][2] = "0";
		s[1][3] = "0";
		s[1][4] = "0";

		s[2][0] = "0";
		s[2][1] = "0";
		s[2][2] = "0";
		s[2][3] = "0";
		s[2][4] = "0";

		s[3][0] = "0";
		s[3][1] = "0";
		s[3][2] = "0";
		s[3][3] = "0";
		s[3][4] = "0";

		s[4][0] = "0";
		s[4][1] = "0C1";
		s[4][2] = "0";
		s[4][3] = "0";
		s[4][4] = "0P2";
		assertTrue("Player 1 1st tile should have moved to the right!",
				stringArrayEquals(s, board.display()));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer1PlaceRightDiagonal() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(0, 1));
		board.place(p1.getT1(), new Location(1, 2));
		String[][] s = new String[BoardInterface.SIDE][BoardInterface.SIDE];
		s[0][0] = "0";
		s[0][1] = "0C1";
		s[0][2] = "0";
		s[0][3] = "0P2";
		s[0][4] = "0";

		s[1][0] = "0";
		s[1][1] = "0";
		s[1][2] = "1";
		s[1][3] = "0";
		s[1][4] = "0";

		s[2][0] = "0";
		s[2][1] = "0";
		s[2][2] = "0";
		s[2][3] = "0";
		s[2][4] = "0";

		s[3][0] = "0";
		s[3][1] = "0";
		s[3][2] = "0";
		s[3][3] = "0";
		s[3][4] = "0";

		s[4][0] = "0";
		s[4][1] = "0C1";
		s[4][2] = "0";
		s[4][3] = "0";
		s[4][4] = "0P2";
		assertTrue("Player 1 1st tile should have moved to the right!",
				stringArrayEquals(s, board.display()));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidPlacementException.class)
	public void testPlayer1PlaceBeforeMove() throws InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.place(p1.getT1(), new Location(0, 1));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidPlacementException.class)
	public void testPlayer1PlaceWrongFar() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(0, 2));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2canPlaceRight() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertTrue("Player 2 2nd tile can place up!",
				(board.canPlace(p2.getT2(), new Location(3, 4))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2canPlaceDiagonal() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertTrue("Player 2 2nd tile can place diagonally!",
				(board.canPlace(p2.getT2(), new Location(3, 3))));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2canPlaceWrongFar() {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		assertFalse("Player 2 2nd tile can not place two steps far!",
				(board.canPlace(p2.getT2(), new Location(2, 4))));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidPlacementException.class)
	public void testPlayer2PlaceWrongTurn() throws InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.place(p2.getT2(), new Location(3, 4));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidPlacementException.class)
	public void testPlayer2PlaceWrongStep() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(0, 1));
		board.place(p1.getT1(), new Location(1, 1));
		board.place(p2.getT2(), new Location(3, 4));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2PlaceRight() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(1, 1));
		board.move(p2.getT1(), new Location(1, 2));
		board.place(p2.getT1(), new Location(2, 2));
		String[][] s = new String[BoardInterface.SIDE][BoardInterface.SIDE];
		s[0][0] = "0";
		s[0][1] = "0";
		s[0][2] = "0";
		s[0][3] = "0";
		s[0][4] = "0";

		s[1][0] = "0C1";
		s[1][1] = "1";
		s[1][2] = "0P2";
		s[1][3] = "0";
		s[1][4] = "0";

		s[2][0] = "0";
		s[2][1] = "0";
		s[2][2] = "1";
		s[2][3] = "0";
		s[2][4] = "0";

		s[3][0] = "0";
		s[3][1] = "0";
		s[3][2] = "0";
		s[3][3] = "0";
		s[3][4] = "0";

		s[4][0] = "0";
		s[4][1] = "0C1";
		s[4][2] = "0";
		s[4][3] = "0";
		s[4][4] = "0P2";
		assertTrue(
				"Player 1 1st tile should have moved diagonally, and placed to the left!",
				stringArrayEquals(s, board.display()));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2PlaceRightDiagonal() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(1, 1));
		board.move(p2.getT1(), new Location(1, 2));
		board.place(p2.getT1(), new Location(2, 3));
		String[][] s = new String[BoardInterface.SIDE][BoardInterface.SIDE];
		s[0][0] = "0";
		s[0][1] = "0";
		s[0][2] = "0";
		s[0][3] = "0";
		s[0][4] = "0";

		s[1][0] = "0C1";
		s[1][1] = "1";
		s[1][2] = "0P2";
		s[1][3] = "0";
		s[1][4] = "0";

		s[2][0] = "0";
		s[2][1] = "0";
		s[2][2] = "0";
		s[2][3] = "1";
		s[2][4] = "0";

		s[3][0] = "0";
		s[3][1] = "0";
		s[3][2] = "0";
		s[3][3] = "0";
		s[3][4] = "0";

		s[4][0] = "0";
		s[4][1] = "0C1";
		s[4][2] = "0";
		s[4][3] = "0";
		s[4][4] = "0P2";
		assertTrue(
				"Player 1 1st tile should have moved diagonally, and placed diagonally!",
				stringArrayEquals(s, board.display()));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidPlacementException.class)
	public void testPlayer2PlaceBeforeMove() throws InvalidPlacementException,
			InvalidMoveException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(1, 1));
		board.place(p2.getT1(), new Location(2, 3));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidPlacementException.class)
	public void testPlayer2PlaceWrongFar() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 1);
		Player p2 = new Player("P2", 2);
		BoardInterface board = new Board(p1, p2);
		board.move(p1.getT1(), new Location(1, 0));
		board.place(p1.getT1(), new Location(1, 1));
		board.move(p2.getT1(), new Location(1, 4));
		board.place(p2.getT1(), new Location(3, 2));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2Wins1() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 2);
		Player p2 = new Player("P2", 1);
		BoardInterface board = new Board(p1, p2);

		board.move(p1.getT1(), new Location(1, 1));
		board.place(p1.getT1(), new Location(0, 2));

		board.move(p2.getT1(), new Location(0, 2));
		board.place(p2.getT1(), new Location(0, 1));

		board.move(p1.getT1(), new Location(0, 0));
		board.place(p1.getT1(), new Location(0, 1));

		board.move(p2.getT1(), new Location(0, 1));
		board.place(p2.getT1(), new Location(0, 2));

		board.move(p1.getT1(), new Location(1, 1));
		board.place(p1.getT1(), new Location(0, 2));

		board.move(p2.getT1(), new Location(0, 2));

		assertTrue("Player 2 should have won the game", board.isGameOver());
	}

	@org.junit.Test(timeout = 1000)
	public void testDome() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 2);
		Player p2 = new Player("P2", 1);
		BoardInterface board = new Board(p1, p2);

		board.move(p1.getT1(), new Location(1, 1));
		board.place(p1.getT1(), new Location(0, 2));

		board.move(p2.getT1(), new Location(0, 2));
		board.place(p2.getT1(), new Location(0, 1));

		board.move(p1.getT1(), new Location(0, 0));
		board.place(p1.getT1(), new Location(0, 1));

		board.move(p2.getT1(), new Location(0, 1));
		board.place(p2.getT1(), new Location(0, 2));

		board.move(p1.getT1(), new Location(1, 1));
		board.place(p1.getT1(), new Location(0, 2));

		board.move(p2.getT2(), new Location(3, 4));
		board.place(p2.getT2(), new Location(4, 4));

		board.move(p1.getT1(), new Location(2, 2));
		board.place(p1.getT1(), new Location(1, 2));

		board.move(p2.getT2(), new Location(3, 3));
		board.place(p2.getT2(), new Location(3, 4));

		board.move(p1.getT1(), new Location(1, 3));
		board.place(p1.getT1(), new Location(0, 2));

		assertFalse("This location is a dome. you can not move to it",
				board.canMove(p1.getT1(), new Location(0, 2)));
	}

	@org.junit.Test(timeout = 1000, expected = InvalidPlacementException.class)
	public void testDomePlace() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 2);
		Player p2 = new Player("P2", 1);
		BoardInterface board = new Board(p1, p2);

		board.move(p1.getT1(), new Location(1, 1));
		board.place(p1.getT1(), new Location(0, 2));

		board.move(p2.getT1(), new Location(0, 2));
		board.place(p2.getT1(), new Location(0, 1));

		board.move(p1.getT1(), new Location(0, 0));
		board.place(p1.getT1(), new Location(0, 1));

		board.move(p2.getT1(), new Location(0, 1));
		board.place(p2.getT1(), new Location(0, 2));

		board.move(p1.getT1(), new Location(1, 1));
		board.place(p1.getT1(), new Location(0, 2));

		board.move(p2.getT2(), new Location(3, 4));
		board.place(p2.getT2(), new Location(4, 4));

		board.move(p1.getT1(), new Location(2, 2));
		board.place(p1.getT1(), new Location(1, 2));

		board.move(p2.getT2(), new Location(3, 3));
		board.place(p2.getT2(), new Location(3, 4));

		board.move(p1.getT1(), new Location(1, 3));
		board.place(p1.getT1(), new Location(0, 2));

		board.move(p2.getT1(), new Location(1, 1));
		board.place(p2.getT1(), new Location(0, 2));
		assertFalse("This location is a dome. you can not move to it",
				board.canMove(p1.getT1(), new Location(0, 2)));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2Wins2() throws InvalidMoveException,
			InvalidPlacementException {
		Player p1 = new Player("P1", 2);
		Player p2 = new Player("P2", 1);
		BoardInterface board = new Board(p1, p2);

		board.move(p1.getT1(), new Location(1, 1));
		board.place(p1.getT1(), new Location(0, 2));

		board.move(p2.getT1(), new Location(0, 2));
		board.place(p2.getT1(), new Location(0, 1));

		board.move(p1.getT1(), new Location(0, 0));
		board.place(p1.getT1(), new Location(0, 1));

		board.move(p2.getT1(), new Location(0, 1));
		board.place(p2.getT1(), new Location(0, 2));

		board.move(p1.getT1(), new Location(1, 1));
		board.place(p1.getT1(), new Location(0, 2));

		board.move(p2.getT1(), new Location(0, 2));

		assertTrue("Player 2 should have won the game", board.isWinner(p2));
	}

	@org.junit.Test(timeout = 1000)
	public void testPlayer2HasNoMoves() throws InvalidMoveException,
			InvalidPlacementException {

		Player p1 = new Player("P1", 2);
		Player p2 = new Player("P2", 1);

		BoardInterface board = new Board(p1, p2);

		board.move(p1.getT1(), new Location(1, 1));
		board.place(p1.getT1(), new Location(1, 0));

		board.move(p2.getT1(), new Location(0, 2));
		board.place(p2.getT1(), new Location(1, 2));

		board.move(p1.getT1(), new Location(0, 0));
		board.place(p1.getT1(), new Location(1, 1));

		board.move(p2.getT1(), new Location(1, 2));
		board.place(p2.getT1(), new Location(1, 1));

		board.move(p1.getT2(), new Location(3, 0));
		board.place(p1.getT2(), new Location(4, 0));

		board.move(p2.getT2(), new Location(4, 3));
		board.place(p2.getT2(), new Location(3, 2));

		board.move(p1.getT2(), new Location(4, 1));
		board.place(p1.getT2(), new Location(3, 2));

		board.move(p2.getT1(), new Location(2, 2));
		board.place(p2.getT1(), new Location(3, 2));

		board.move(p1.getT2(), new Location(3, 0));
		board.place(p1.getT2(), new Location(4, 0));

		board.move(p2.getT1(), new Location(2, 1));
		board.place(p2.getT1(), new Location(2, 2));

		board.move(p1.getT2(), new Location(4, 1));
		board.place(p1.getT2(), new Location(3, 0));

		board.move(p2.getT1(), new Location(2, 0));
		board.place(p2.getT1(), new Location(3, 0));

		assertTrue("Player 1 should have no moves", board.hasNoMoves(p1));
	}
	
	private boolean arrayListsEqual(ArrayList<Location> a, ArrayList<Location> b) {
		if (a.size() != b.size()) {
			return false;
		}
		int size = a.size();
		boolean thisOneFound = false;
		for (int i = 0; i < size; i++) {
			thisOneFound = false;
			for (int j = 0; j < size; j++) {
				if (a.get(i).equals(b.get(j))) {
					thisOneFound = true;
					break;
				}
			}
			if (!thisOneFound) {
				return false;
			}
		}
		return true;
	}

	private boolean stringArrayEquals(String[][] a, String[][] b) {
		if (a.length != b.length) {
			return false;
		}
		for (int i = 0; i < a.length; i++) {
			if (a[i].length != b[i].length) {
				return false;
			}
			for (int j = 0; j < a[i].length; j++) {
				if (!a[i][j].equals(b[i][j])) {
					return false;
				}
			}
		}
		return true;
	}
}
