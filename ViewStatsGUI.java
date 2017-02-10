import java.awt.*;
import java.awt.event.*;
import java.io.*;
import javax.swing.*;

public class ViewStatsGUI extends JFrame implements ActionListener{
	private DbCon database;
	private JTextArea statsArea;
	private JButton outputStatsButton;
	
	//variables to store stats to be output to file
	private int numGamesPlayed, numComputerWins, numHumanWins, largestNumberRounds;
	private double averageDraws;
	
	public ViewStatsGUI(DbCon data)
	{
		//initialise instance variables
		database = data;
		numGamesPlayed = database.getNumGames();
		numComputerWins = database.getCPUWins();
		numHumanWins = database.getPlayerWins();
		averageDraws = database.getAvgDraws();
		largestNumberRounds =  database.getLargestNumRounds();
		
		//setup GUI
		setTitle("GAME STATS");
		setSize(600, 220);				
		
		statsArea = new JTextArea(getStatsString());
		statsArea.setFont(new Font("Courier", Font.PLAIN, 14));
		statsArea.setEditable(false);
		add(statsArea, BorderLayout.CENTER);
		
		outputStatsButton = new JButton("Export Stats");
		outputStatsButton.addActionListener(this);
		
		JPanel panel = new JPanel();
		panel.add(outputStatsButton);
		add(panel, "South");
		
		setVisible(true);
	}
	
	/**
	 * Creates a formatted string to display the game's stats
	 * as retrieved from the database
	 *  
	 * @return formatted string of the stats in the database
	 */
	public String getStatsString()
	{
		String statsString = String.format("Number of games played overall: " + "%d\r\n" +
	"Number of times the computer has won: " + "%d\r\n"
	 + "Number of times the human has won: " + "%d\r\n" + "Average number of draws: " + "%.2f\r\n"
		+ "Largest number of rounds played in a single game: " + "%d\r\n"
	 , numGamesPlayed, numComputerWins, numHumanWins, averageDraws, largestNumberRounds);
		
		return statsString;
	}
	
	/**
	 * defines the action event attached to outputStatsButton
	 * which is used to output the database stats to a .txt file
	 */
	public void actionPerformed(ActionEvent e)
	{
		if (e.getSource() == outputStatsButton)
		{
			 try
			 {
				 FileWriter statsWriter = new FileWriter("GameStats.txt"); 
				 statsWriter.write(getStatsString());
				 statsWriter.close();
				 System.out.println("Stats exported to GameStats.txt");
			 }
			 catch(IOException ioe)
			 {
				 
			 }
			
		 }
	 }

         

}
