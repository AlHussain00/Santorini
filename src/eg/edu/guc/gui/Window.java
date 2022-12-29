package eg.edu.guc.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import eg.edu.guc.santorini.tiles.Piece;

@SuppressWarnings("serial")
public class Window extends JFrame implements ActionListener, MouseListener{
	Adapter adapter;
	Tile[][] labels;
	Tile tempTile;
	Piece savedPiece;
	Piece tempPiece;
	JPanel gridPanel;

	public Window(Adapter a)
	{
		super();
		setTitle("SANTORINI QUEST");
		setSize(1000, 700);
		setLocation(200, 100);
		Container content = getContentPane();
		content.setBackground(Color.orange);
		content.setLayout(new BorderLayout());
		adapter = a; 
		labels = adapter.initialBoard();

		InitPanels(5, 5);
		InitGridTiles();
		setVisible(true);

		WindowDestroyer wd = new WindowDestroyer();
		addWindowListener(wd);
	}

	public void InitPanels(int row, int column)
	{
		//Create Panel for the tiles grid
		gridPanel = new JPanel(new GridLayout(row, column));
		gridPanel.setBackground(Color.orange);
		add(gridPanel, BorderLayout.CENTER);

		//Create panel for the buttons vertical bar
		
	}

	public void InitGridTiles()
	{


		for(int i = 0; i < labels.length; i++)
		{
			for (int j =0;j<labels[i].length;j++) {
				labels[i][j].addMouseListener(this);
				gridPanel.add(labels[i][j]);
			}}
	}

	

	public void actionPerformed(ActionEvent e) 
	{	
		if (e.getActionCommand().equals("Build")) {
			if(tempTile != null && !adapter.isPiece(tempTile))
			{   
				adapter.placeOnBoard(savedPiece, tempTile.locations);
			}
		}
	}



	public void mouseClicked(MouseEvent e) {

	}

	public void mouseEntered(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mouseExited(MouseEvent arg0) {
		// TODO Auto-generated method stub

	}

	public void mousePressed(MouseEvent e) {
		tempTile = (Tile) e.getSource();
		tempTile.setBorder(BorderFactory.createLoweredSoftBevelBorder());
		if(adapter.isPiece(tempTile)) {
			if (!adapter.turn.isPlace()) {
				adapter.highlightedMoves(tempTile); }
			else {
				if (tempTile.getLocations().equals(savedPiece.getLocation())) {
				adapter.highlightedPlacements(tempTile); }
			}
			tempPiece = adapter.getPiece(tempTile);
		}

		else {if (adapter.turn.isMove()) {
			
			adapter.moveOnBoard(tempPiece, tempTile.locations);
			savedPiece = tempPiece;
		}
		else {
			if (adapter.turn.isPlace()) {
				adapter.placeOnBoard(savedPiece, tempTile.locations);
			}
		}

		}
		if (adapter.b.isGameOver()) {
			JOptionPane.showMessageDialog(this.getContentPane(),
				    adapter.showGameWinner().getName() + " is winner",
				    "CONGRATULATIONSS !!",
				    JOptionPane.PLAIN_MESSAGE);
		}
		tempTile.setBorder(BorderFactory.createEmptyBorder());
	}

	public void mouseReleased(MouseEvent arg0) {
		tempTile.setBorder(BorderFactory.createEmptyBorder());

	}

}
