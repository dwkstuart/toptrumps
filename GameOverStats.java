import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;

public class GameOverStats extends JFrame implements ActionListener{
	
	private JButton saveAndExitBut; // button to optionally save the stats from the game that just ended
	private JButton exitBut; // button to exit without saving
	private String statsString; //formatted string that represents the stats from the game that just ended
	private String winner;
	
	private Game lastGame; // game object
	private DbCon trumpsDb;
	
	public GameOverStats(Game game)
	{
		lastGame = game;
		setWinnerName();
		trumpsDb = new DbCon();
		
		//layout GUI components
		setTitle("GAME OVER");
		setSize(400, 150);		
		layoutTextArea();
		layoutButtonArea();
		
		setVisible(true);			
	}
	
	private void layoutTextArea()
	{
		JPanel topPanel = new JPanel(new FlowLayout());
		topPanel.setPreferredSize(new Dimension(100, 50));
		JLabel winnerLabel = new JLabel("The winner is: " + /*lastGame.getWinner()*/  "!!!");
		winnerLabel.setFont(new Font("Courier", Font.BOLD, 24));
		topPanel.add(winnerLabel);
		add(topPanel, "North");
	}
	
	private void layoutButtonArea()
	{
		JPanel bottomPanel = new JPanel(new FlowLayout());
		bottomPanel.setPreferredSize(new Dimension(100, 50));
		saveAndExitBut = new JButton("Save & Exit");
		saveAndExitBut.addActionListener(this);
		exitBut = new JButton("Exit");
		exitBut.addActionListener(this);
		bottomPanel.add(exitBut);
		bottomPanel.add(saveAndExitBut);		
		add(bottomPanel, "South");
	}
	
	private void setWinnerName()
	{
		int winnerInt = lastGame.getWinner();
		switch(winnerInt)
		{
		case 0: winner = "Player 1";
				break;
		case 1: winner = "CPU 1";
				break;
		case 2: winner = "CPU 2";
				break;
		case 3: winner = "CPU 3";
				break;
		case 4: winner = "CPU 4";
				break;
		}
	}
	
	public void actionPerformed(ActionEvent ae) 
	{
	    if(ae.getSource() == exitBut)
	    {
	    	setVisible(false);
	    }
	    
	    if(ae.getSource() == saveAndExitBut)
	    {
	    	trumpsDb.DbConnect();
	    	trumpsDb.postStats(winner, lastGame.getNumDraws(), lastGame.getNumRounds(), 
	    			lastGame.getRoundsWon(0), lastGame.getRoundsWon(1),lastGame.getRoundsWon(2), 
	    			lastGame.getRoundsWon(3), lastGame.getRoundsWon(4));
	    	setVisible(false);
	    }
	    
	}
	
}