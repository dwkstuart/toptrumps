import java.awt.*;
import javax.swing.*;

public class ViewStatsGUI extends JFrame{
	private DbCon database;
	
	public ViewStatsGUI(DbCon data)
	{
		database = data;
		
		setTitle("GAME STATS");
		setSize(600, 200);
		
		JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT));
		
		
		JLabel numGames = new JLabel("Number of games played overall: " + database.getNumGames());
		numGames.setFont(new Font("Courier", Font.BOLD, 16));
		JLabel cpuWins = new JLabel("Number of times the computer has won: " + database.getCPUWins());
		cpuWins.setFont(new Font("Courier", Font.BOLD, 16));
		JLabel playerWins = new JLabel("Number of times the human has won: " + database.getPlayerWins());
		playerWins.setFont(new Font("Courier", Font.BOLD, 16));
		JLabel avgDraws = new JLabel(String.format("%s" + "%.2f", "Average number of draws: ", database.getAvgDraws()));
		avgDraws.setFont(new Font("Courier", Font.BOLD, 16));
		JLabel largestNumRounds = new JLabel("Largest number of rounds played in a single game: " + database.getLargestNumRounds());
		largestNumRounds.setFont(new Font("Courier", Font.BOLD, 16));
		
		panel.add(numGames);
		panel.add(cpuWins);
		panel.add(playerWins);
		panel.add(avgDraws);
		panel.add(largestNumRounds);
		
		add(panel);
		
		
		
		setVisible(true);
	}

}
