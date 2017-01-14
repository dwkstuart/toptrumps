import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class DbCon {
	
private Connection connection = null;
	
	public DbCon()
	{
		
	}
	
	public void DbConnect()
	{
		String DBName = "TopTrumps";
		String DBUser = "";
		String DBPassword = "";
		
		try
		{
		connection =
		DriverManager.getConnection("jdbc:postgresql://localhost:5432/" + DBName,DBUser, DBPassword);
		}
		catch (SQLException e)
		{
		System.err.println("Connection Failed!");
		e.printStackTrace();
		return;
		}
		if (connection != null)
		{
		System.out.println("Connection successful");
		} else
		{
		System.err.println("Failed to make connection!");
		}
	}
	
	public void getNumGames()
	{
		Statement stmt = null;
		String numGamesQuery = "SELECT COUNT(GameID) AS gameCount FROM Game";
		
		try
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(numGamesQuery);
		
			while(rs.next())
			{
				System.out.println("Total number of games played: " + rs.getString("gameCount"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.err.println("error executing query " + numGamesQuery);
		}
		
		
		
	}
	
	public void getPlayerWins()
	{
		Statement stmt = null;
		String playerWinsQuery = "SELECT COUNT(Winner) AS playerWinCount FROM Game WHERE Game.Winner = 'Player1'";
		
		try
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(playerWinsQuery);
			
			while(rs.next())
			{
				System.out.println("Total number of games won by Player: " + rs.getString("playerWinCount"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.err.println("error executing query " + playerWinsQuery);
		}
		
	}
	
	public void getCPUWins()
	{
		Statement stmt = null;
		String CPUWinsQuery = "SELECT COUNT(Winner) AS cpuWinCount FROM Game WHERE Game.Winner = 'CPU1'"
				+ " OR Game.Winner = 'CPU2' OR Game.Winner = 'CPU3' OR Game.Winner = 'CPU4'";
		
		try
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(CPUWinsQuery);
			
			while(rs.next())
			{
				System.out.println("Total number of games won by CPU: " + rs.getString("cpuWinCount"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.err.println("error executing query " + CPUWinsQuery);
		}
		
	}
	
	public void getAvgDraws()
	{
		Statement stmt = null;
		String avgDrawsQuery = "SELECT AVG(numDraws) AS avgDraws FROM Game";
		
		try
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(avgDrawsQuery);
			
			while(rs.next())
			{
				System.out.println("Average number of draws: " + rs.getString("avgDraws"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.err.println("error executing query " + avgDrawsQuery);
		}
	}
	
	public void getLargestNumRounds()
	{
		Statement stmt = null;
		String avgDrawsQuery = "SELECT MAX(numRoundsPlayed) AS largestNumRounds FROM Game";
		
		try
		{
			stmt = connection.createStatement();
			ResultSet rs = stmt.executeQuery(avgDrawsQuery);
		
			while(rs.next())
			{
				System.out.println("Largest number of rounds played in a single game: " + rs.getString("largestNumRounds"));
			}
		}
		catch(SQLException e){
			e.printStackTrace();
			System.err.println("error executing query " + avgDrawsQuery);
		}
	}

}
