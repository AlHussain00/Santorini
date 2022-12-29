package eg.edu.guc.gui;

import java.awt.Component;
import java.util.ArrayList;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

import eg.edu.guc.santorini.Board;
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
import eg.edu.guc.santorini.tiles.Piece;
import eg.edu.guc.santorini.utilities.Location;

public class Adapter {
	Player turn;
	Piece tempPiece;
	Board b;
	Tile[][] t;
	public Adapter(Board b) {
		this.b = b;
		turn = b.getP1();
	}
	public Tile[][] initialBoard() {
		t = new Tile[5][5];
		String[][] s = b.display();
		for (int i = 0;i <s.length;i++) {
			for (int j = 0 ;j<s[i].length;j++ ) {


				t[i][j] = new Tile(new ImageIcon(s[i][j] +".png")); 
				t[i][j].locations.setX( b.getBoard()[i][j].getX());
				t[i][j].locations.setY( b.getBoard()[i][j].getY());
				t[i][j].layer = b.getBoard()[i][j].getLevel();

			}

		}
		return t;
	}
	public Piece getPiece(Tile t) {

		Location l = new Location(t.locations.getY(),t.locations.getX());
		if (l.equals(b.getP1().getT1().getLocation())) {

			return b.getP1().getT1();
		} else 
			if (l.equals(b.getP1().getT2().getLocation())) {
				return b.getP1().getT2();
			} else 
				if (l.equals(b.getP2().getT1().getLocation())) {
					return b.getP2().getT1();
				} else 
					if (l.equals(b.getP2().getT2().getLocation())) {
						return b.getP2().getT2();
					}
					else {

						return null;
					}

	}

	

	public void highlightedMoves(Tile tile) {
		removeHighlightedMoves(tile.locations);
		tempPiece = getPiece(tile);
		ArrayList<Location>highLightedMoves = tempPiece.possibleMoves();
		for (int i =0;i<highLightedMoves.size();i++) {
			if(b.canMove(tempPiece, highLightedMoves.get(i))) {
				String s = b.display()[highLightedMoves.get(i).getY()][highLightedMoves.get(i).getX()];
				this.t[highLightedMoves.get(i).getY()][highLightedMoves.get(i).getX()].setIcon( new ImageIcon("H" + s + ".png"));
			}
		}
	}
	public void removeHighlightedMoves(Location location) {
		if (tempPiece != null) {
			ArrayList<Location>highLightedMovesPrev = tempPiece.possibleMoves();
			for (int i = 0;i<highLightedMovesPrev.size();i++) {
				String s = b.display()[highLightedMovesPrev.get(i).getY()][highLightedMovesPrev.get(i).getX()];
				this.t[highLightedMovesPrev.get(i).getY()][highLightedMovesPrev.get(i).getX()].setIcon(new ImageIcon(s + ".png"));
			}
		}
	}
	public boolean isPiece(Tile tile) {
		String s = b.display()[tile.getLocations().getY()][tile.getLocations().getX()];
		return (s.length()>=3);
	}

	public void moveOnBoard(Piece piece , Location newLocation) throws NullPointerException  {
		removeHighlightedMoves(piece.getLocation());
		Location pieceLocation;
		try {
			pieceLocation = piece.getLocation();

		}
		catch (NullPointerException e) {
			throw new NullPointerException("you clicked only On tile !!");
		}
		try {
			b.move(piece, newLocation);
			String s1 = b.display()[pieceLocation.getY()][pieceLocation.getX()].charAt(0) + "";
			String s2 = b.display()[newLocation.getY()][newLocation.getX()];
			t[pieceLocation.getY()][pieceLocation.getX()].setIcon(new ImageIcon(s1 + ".png"));
			t[newLocation.getY()][newLocation.getX()].setIcon(new ImageIcon(s2 + ".png"));
			Tile tempTile = new Tile();
			tempTile.locations = piece.getLocation();
			this.highlightedPlacements(tempTile);
		}
		catch(GameOverMoveException e) {
			
			System.out.println("GAME IS OVER!!");
			System.out.println(b.getWinner().getName());
		}
		catch(DeadCellMoveException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "YOU ARE MOVING TO DEAD CELL !!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			System.out.println("DOOM!!");	
		}
		catch(InvalidLevelMoveException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "CANNOT MOVE TO HIGHER LEVEL !!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			System.out.println("LEVELL!!");
		}
		catch(MoveAfterMoveException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "CANNOT MOVE AGAIN !!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
		}
		catch (OutOfTurnMoveException e) {
			
			JOptionPane.showMessageDialog(new JFrame(),
				    "NOT YOUR TURN !!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			System.out.println("NOT YOUR TURN !!");
		}
		catch (PieceInMovementException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "PIECE IN YOUR WAY !!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			System.out.println("PIECE IN THE WAY ,KILL IT !!");
		}
		catch (InvalidMoveException e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(new JFrame(),
				    "CANNOT MOVE HERE !!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			System.out.println("NOW YOU PLACE !!");
			System.out.println("CANNOT MOVE HERE");
		}


	}

	public void highlightedPlacements(Tile tile) {
		removeHighlightedPlacements(tile.locations);
		tempPiece = getPiece(tile);
		ArrayList<Location>highLightedPlacements = tempPiece.possiblePlacements();
		if (turn.getT1().equals(tempPiece)||turn.getT2().equals(tempPiece)) {
		for (int i =0;i<highLightedPlacements.size();i++) {
			if(b.canPlace(tempPiece, highLightedPlacements.get(i))) {
				String s = b.display()[highLightedPlacements.get(i).getY()][highLightedPlacements.get(i).getX()];
				this.t[highLightedPlacements.get(i).getY()][highLightedPlacements.get(i).getX()].setIcon( new ImageIcon("H" + s + ".png"));
			}
		}}
		
	}
	public void removeHighlightedPlacements(Location location) {
		if (tempPiece != null) {
			ArrayList<Location>highLightedPlacementsPrev = tempPiece.possiblePlacements();
			for (int i = 0;i<highLightedPlacementsPrev.size();i++) {
				String s = b.display()[highLightedPlacementsPrev.get(i).getY()][highLightedPlacementsPrev.get(i).getX()];
				this.t[highLightedPlacementsPrev.get(i).getY()][highLightedPlacementsPrev.get(i).getX()].setIcon(new ImageIcon(s + ".png"));
			}
		}
	}

	public void placeOnBoard(Piece piece, Location newLocation) throws NullPointerException {
		removeHighlightedPlacements(piece.getLocation());
		@SuppressWarnings("unused")
		Location pieceLocation;
		try {
			pieceLocation = piece.getLocation();

		}
		catch (NullPointerException e) {
			throw new NullPointerException("you clicked only On tile !!");
		}
		try {
			
			b.place(piece, newLocation);
			String s = b.display()[newLocation.getY()][newLocation.getX()];
			t[newLocation.getY()][newLocation.getX()].setIcon(new ImageIcon(s + ".png"));
			if (turn == b.getP1()) {
				turn = b.getP2();}
			else {
				turn = b.getP1();
			}
		}
		catch(GameOverPlacementException e) {

		}
		catch(DeadCellPlacementException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "YOU ARE PLACING ON A DOOM !!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			System.out.println("DOOM!!");	
		}
		catch(PieceInPlacementException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "PIECE IN YOUR WAY !!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			System.out.println("PIECE IN THE WAY, NOW KILL IT!!");
		}
		catch(InvalidPlacementException e) {
			JOptionPane.showMessageDialog(new JFrame(),
				    "CANNOT PLACE HERE !!",
				    "Error",
				    JOptionPane.ERROR_MESSAGE);
			System.out.println("CANNOT PLACE !!");

		}
			}
	
	public Player showGameWinner() {
	return (b.isWinner(b.getP1()))?b.getP1():b.getP2();
	
	}
	
	public String showMessage(boolean b,String s) {
		return s;
	}
	
	public static void main (String[]args) {
		Adapter a1 = new Adapter(new Board(new Player("Player 1",1), new Player("Player 2",2))); 
		@SuppressWarnings("unused")
		Window w = new Window(a1);
	}
}

