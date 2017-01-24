/**Main class that launches Top Trumps program
*
*/
public class Main {

	public static void main(String[] args) {

		GameGUI game  = new GameGUI();
		game.SetOpponentsView(1);
		game.UpdatePlayer(0);
		game.getDeck();


		DbCon TrumpsDb = new DbCon();
		TrumpsDb.DbConnect();
		TrumpsDb.getNumGames();
		TrumpsDb.getPlayerWins();
		TrumpsDb.getCPUWins();
		TrumpsDb.getAvgDraws();
		TrumpsDb.getLargestNumRounds();
		
	
	}

	
}
