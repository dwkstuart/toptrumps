/**
* This class manages all connections and queries to the toptrumps database
*/

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;

public class DbCon {

	private int primaryKey; //used to store the next sequential primary key
	private Connection connection = null;

	/**
	* default constructor
	*/
	public DbCon(){
		DbConnect();
		primaryKey = getNumGames()+1; //set primary key to the next number in the sequence
	}

	/**
	* database connection method
	*/
	public void DbConnect(){
		String DBName = "m_16_0207805s";
		String DBUser = "m_16_0207805s";
		String DBPassword = "0207805s";

		try {
			connection = DriverManager.getConnection("jdbc:postgresql://yacata.dcs.gla.ac.uk:5432/" + DBName,DBUser, DBPassword);
		}
		catch (SQLException e) {
			System.err.println("Connection Failed!");
			e.printStackTrace();
			return;
		}
		if (connection != null) {
			System.out.println("Connection successful");
		} else {
			System.err.println("Failed to make connection!");
		}
	}

	/**
	* @return the total number of games played.
	*/
	public int getNumGames(){
		Statement stmt = null;
		String numGamesQuery = "SELECT COUNT(GameID) AS gameCount FROM Game";
		int numGames = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(numGamesQuery);

			while(rs.next()) {
				numGames = rs.getInt("gameCount");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}

		return numGames;
	}

	/**
	* @return the total number of games won by the human player
	*/
	public int getPlayerWins() {
		Statement stmt = null;
		String playerWinsQuery = "SELECT COUNT(Winner) AS playerWinCount "
		+ "FROM Game WHERE Game.Winner = 'Player 1'";
		int numPlayerWins = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(playerWinsQuery);

			while(rs.next()) {
				numPlayerWins = rs.getInt("playerWinCount");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + playerWinsQuery);
		}

		return numPlayerWins;
	}

	/**
	* @return the total number of games one by the virtual players
	*/
	public int getCPUWins() {
		Statement stmt = null;
		String CPUWinsQuery = "SELECT COUNT(Winner) AS cpuWinCount FROM Game WHERE Game.Winner = 'CPU 1'"
		+ " OR Game.Winner = 'CPU 2' OR Game.Winner = 'CPU 3' OR Game.Winner = 'CPU 4'";
		int numCpuWins = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(CPUWinsQuery);

			while(rs.next()) {
				numCpuWins = rs.getInt("cpuWinCount");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + CPUWinsQuery);
		}

		return numCpuWins;
	}

	/**
	* @return the total amount of draws recorded
	*/
	public double getAvgDraws() {
		Statement stmt = null;
		String avgDrawsQuery = "SELECT AVG(numDraws) AS avgDraws FROM Game";
		double avgDraws = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(avgDrawsQuery);

			while(rs.next()) {
				avgDraws = rs.getDouble("avgDraws");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + avgDrawsQuery);
		}

		return avgDraws;
	}

	/**
	* @return the largest number of rounds played in a single game
	*/
	public int getLargestNumRounds() {
		Statement stmt = null;
		String largestNumRoundsQuery = "SELECT MAX(numRoundsPlayed) AS largestNumRounds FROM Game";
		int largestNumRounds = 0;

		try {
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(largestNumRoundsQuery);

			while(rs.next()) {
				largestNumRounds = rs.getInt("largestNumRounds");
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
			System.err.println("error executing query " + largestNumRoundsQuery);
		}

		return largestNumRounds;
	}

	public int getPK() {
		return primaryKey;
	}

	/**
	* Posts relevant game statistics to the database at the end of a game
	* if the user chooses to do so
	*
	* @param winner		the winner of the game
	* @param numDraws		the number of draws in the game
	* @param numRounds		the number of rounds played in the game
	* @param p1RoundsWon	the number of rounds won by the player
	* @param cpu1RoundsWon	the number of rounds won by CPU1
	* @param cpu2RoundsWon	the number of rounds won by CPU2
	* @param cpu3RoundsWon	the number of rounds won by CPU3
	* @param cpu4RoundsWon	the number of rounds won by CPU4
	*/
	public void postStats(String winner, int numDraws, int numRounds, int p1RoundsWon,
	int cpu1RoundsWon, int cpu2RoundsWon, int cpu3RoundsWon, int cpu4RoundsWon) {
		Statement stmt = null;

		String postQuery = "INSERT INTO game (gameid, winner, numdraws, numroundsplayed, playerroundswon,"
		+ " cpu1roundswon, cpu2roundswon, cpu3roundswon, cpu4roundswon) VALUES ('" + primaryKey+ "','" + winner
		+ "', '" + numDraws + "', '" + numRounds + "', '" + p1RoundsWon + "', '" + cpu1RoundsWon + "', '" +
		cpu2RoundsWon + "', '" + cpu3RoundsWon + "', '" + cpu4RoundsWon + "');";

		try {
			stmt = connection.createStatement();
			stmt.executeUpdate(postQuery);
		}
		catch (SQLException e){
			e.printStackTrace();
		}
	}

}
