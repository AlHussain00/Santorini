package eg.edu.guc.gui;

import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;


import eg.edu.guc.santorini.Board;
import eg.edu.guc.santorini.players.Player;

@SuppressWarnings("serial")
public class StartWindow extends JFrame implements ActionListener {
	private CardLayout cardLayout;
	private int pop , ptp;
	private JPanel player1, player2, start , text, firstPanel, secondPanel;
	private Button cube1 , cube2 , pyramid1 , pyramid2;
	private JTextField player1TextField, player2TextField;
	private JButton start1, soundOnOff;


	public int getPop() {
		return pop;
	}


	public void setPop(int pop) {
		this.pop = pop;
	}


	public int getPtp() {
		return ptp;
	}


	public void setPtp(int ptp) {
		this.ptp = ptp;
	}


	public JPanel getPlayer1() {
		return player1;
	}


	public void setPlayer1(JPanel player1) {
		this.player1 = player1;
	}


	public JPanel getPlayer2() {
		return player2;
	}


	public void setPlayer2(JPanel player2) {
		this.player2 = player2;
	}


	public JPanel getStart() {
		return start;
	}


	public void setStart(JPanel start) {
		this.start = start;
	}


	public JPanel getText() {
		return text;
	}


	public void setText(JPanel text) {
		this.text = text;
	}


	public JPanel getFirstPanel() {
		return firstPanel;
	}


	public void setFirstPanel(JPanel firstPanel) {
		this.firstPanel = firstPanel;
	}


	public Button getCube1() {
		return cube1;
	}


	public void setCube1(Button cube1) {
		this.cube1 = cube1;
	}


	public Button getCube2() {
		return cube2;
	}


	public void setCube2(Button cube2) {
		this.cube2 = cube2;
	}


	public Button getPyramid1() {
		return pyramid1;
	}


	public void setPyramid1(Button pyramid1) {
		this.pyramid1 = pyramid1;
	}


	public Button getPyramid2() {
		return pyramid2;
	}


	public void setPyramid2(Button pyramid2) {
		this.pyramid2 = pyramid2;
	}


	public JTextField getPlayer1TextField() {
		return player1TextField;
	}


	public void setPlayer1TextField(JTextField player1TextField) {
		this.player1TextField = player1TextField;
	}


	public JTextField getPlayer2TextField() {
		return player2TextField;
	}


	public void setPlayer2TextField(JTextField player2TextField) {
		this.player2TextField = player2TextField;
	}


	public JButton getStart1() {
		return start1;
	}


	public void setStart1(JButton start1) {
		this.start1 = start1;
	}

	
	public StartWindow() {
		super();

     	setTitle("Start Window");
		setSize(700, 700);
		WindowDestroyer myListener = new WindowDestroyer();
	    cardLayout = new CardLayout();
	    getContentPane().setLayout(cardLayout);
	   // getContentPane().setBackground(Color.black);
	    

		//intiaPanelTwo();
		//creatButton();
		intializePanelOne();
		setVisible(true);
		addWindowListener(myListener);
		pop = 1;
		ptp = 2;
	}


	
	public void intializePanelOne() {

		firstPanel = new JPanel();
		firstPanel.setOpaque(false);
		JLabel imageLabel = new JLabel(new ImageIcon("SantoriniBeginSized.png"));
		JPanel bottomPanel = new JPanel();
		bottomPanel.setLayout(new GridBagLayout());
		bottomPanel.setOpaque(false);
		GridBagConstraints gbc = new GridBagConstraints();
		gbc.insets = new Insets(5, 5, 5, 5);
		Color brown = new Color(171, 128, 52);
		
		JButton instructions = new JButton("Instructions");
		instructions.setBackground(brown);
		instructions.setForeground(Color.WHITE);
		instructions.setActionCommand("Instructions");
		instructions.addActionListener(this);
		
		soundOnOff = new JButton("Sound On");
		soundOnOff.setActionCommand("SoundOnOff");
		soundOnOff.setBackground(brown);
		soundOnOff.setForeground(Color.WHITE);
		soundOnOff.addActionListener(this);
		
		JButton play = new JButton("Play");
		play.setActionCommand("play");
		play.setBackground(brown);
		play.setForeground(Color.WHITE);
		play.addActionListener(this);

		
		gbc.gridy = 0;
		bottomPanel.add(play, gbc);
		gbc.gridy = 1;
		bottomPanel.add(instructions, gbc);
		gbc.gridy = 2;
		bottomPanel.add(soundOnOff, gbc);


		imageLabel.setLayout(new BorderLayout());
		imageLabel.add(bottomPanel, BorderLayout.SOUTH);
		firstPanel.add(imageLabel);
        
		this.getContentPane().add(firstPanel);
		this.pack();
		this.setLocationByPlatform(true);
		this.setVisible(true);
	}

	public void intiaPanelTwo() {
		secondPanel = new JPanel(new BorderLayout());

		player1 = new JPanel(new BorderLayout());
		secondPanel.add(player1, BorderLayout.WEST);

		player2 = new JPanel(new BorderLayout());
		secondPanel.add(player2, BorderLayout.EAST);

		text = new JPanel(new BorderLayout()); 
		secondPanel.add(text, BorderLayout.CENTER);

		start = new JPanel(new BorderLayout());
		secondPanel.add(start, BorderLayout.SOUTH);

		getContentPane().add(secondPanel);
	}

	public void creatButton() {
		JLabel label1 = new JLabel("cube");
		JLabel label2 = new JLabel("pyramid");
		JLabel label3 = new JLabel("Players");

		label1.setHorizontalAlignment(JLabel.CENTER);
		label2.setHorizontalAlignment(JLabel.CENTER);
		label3.setHorizontalAlignment(JLabel.CENTER);
		//JTextArea textForPlayer1 = new JTextArea("")

		player1TextField = new JTextField("");
		player2TextField = new JTextField("");
		player1TextField.setHorizontalAlignment(JTextField.CENTER);
		player2TextField.setHorizontalAlignment(JTextField.CENTER);

		text.add(label3, BorderLayout.NORTH);
		text.add(label1, BorderLayout.CENTER);
		text.add(label2, BorderLayout.SOUTH);


		cube1 = new Button(new ImageIcon("HCube.png"), "Cube1", this);
		cube1.setActionCommand("cube1");

		pyramid1 = new Button(new ImageIcon("Pyramid.png"), "pyramid1", this);
		pyramid1.setActionCommand("pyramid1");

		player1.add(cube1, BorderLayout.CENTER);
		player1.add(pyramid1, BorderLayout.SOUTH);
		player1.add(player1TextField, BorderLayout.NORTH);



		cube2 = new Button(new ImageIcon("BCube.png"), "Cube2", this);
		cube2.setActionCommand("cube2");

		pyramid2 = new Button(new ImageIcon("HBPyramid.png"), "pyramid2", this);
		pyramid2.setActionCommand("pyramid2");

		player2.add(player2TextField, BorderLayout.NORTH);
		player2.add(cube2, BorderLayout.CENTER);
		player2.add(pyramid2, BorderLayout.SOUTH);



		start1 = new JButton("start");
		start1.addActionListener(this);
		start.add(start1);

	}


	public void actionPerformed(ActionEvent e) {
       if (e.getActionCommand().equals("play")) {
    	   intiaPanelTwo();
      		creatButton();
      		cardLayout.addLayoutComponent(secondPanel, "Panel");
      		cardLayout.show(getContentPane(), "Panel");
      		
    	   ImageIcon switcher = new ImageIcon("switch.gif");
    	  
    	   JLabel interMediate = new JLabel(switcher);
    	 
    	   getContentPane().add(interMediate,"Image");
    	 
    	   //interMediate.setVisible(true);
       	cardLayout.show(getContentPane(),"Image");
       	cardLayout.show(getContentPane(),"Panel");
      		switcher.getImage().flush();
      		//secondPanel.setOpaque(true);
      
    	
    //	

   		
   		
   		  
    	 
       }
		
		
		if (e.getActionCommand().equals("pyramid1")) {
			cube1.setIcon(new ImageIcon("Cube.png"));
			pyramid1.setIcon(new ImageIcon("HPyramid.png"));
			pop = 2;
		}
		else {
			if (e.getActionCommand().equals("cube1")) {
				pyramid1.setIcon(new ImageIcon("Pyramid.png"));
				cube1.setIcon(new ImageIcon("HCube.png"));
				pop = 1;
			}

			else {
				if (e.getActionCommand().equals("pyramid2")) {
					cube2.setIcon(new ImageIcon("BCube.png"));
					pyramid2.setIcon(new ImageIcon("HBPyramid.png"));
					ptp = 2;
				}

				else {
					if (e.getActionCommand().equals("cube2")) {
						pyramid2.setIcon(new ImageIcon("BPyramid.png"));
						cube2.setIcon(new ImageIcon("HBCube.png"));
						ptp = 1;
					}

					else {
						if (e.getActionCommand().equals("start")) {
							System.out.println(
									player1TextField.getText() + "" + player2TextField.getText());
							Adapter a = new Adapter(new Board(
									new Player(player1TextField.getText() , pop) ,  
									new Player(player2TextField.getText(), ptp)));
							Window w = new Window(a);
							setVisible(false);
							w.setVisible(true);
					} } } } } }

	public static void main(String [] args) {
		@SuppressWarnings("unused")
		StartWindow w = new StartWindow();
	}


}
