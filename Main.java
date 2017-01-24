/**Main class that launches Top Trumps program
*
*/
public class Main {

	public static void main(String[] args) {

		startGui run =  new startGui();
		
	


		DbCon TrumpsDb = new DbCon();
		TrumpsDb.DbConnect();
		TrumpsDb.getNumGames();
		TrumpsDb.getPlayerWins();
		TrumpsDb.getCPUWins();
		TrumpsDb.getAvgDraws();
		TrumpsDb.getLargestNumRounds();
		
	
	}

	
}
