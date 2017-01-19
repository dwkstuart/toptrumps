/**
* Class to process a single round of the TopTrumps game, including processing
* what cards have been played and working out who won the round.
*/
public class Round {
   /**
   * Constructor for the instances when it is a CPU player's turn to select
   * a category
   * @param  Player[] activePlayers Array of all the current active players, ie. everyone who is taking part in the round.
   * @param  int      currentPlayer Identifier for the player whose turn it currently is.
   */
   public Round(Player[] activePlayers, int currentPlayer) {

   }

   /**
   * Constructor for the instance that it is the user's turn to select a category.
   * N.B. note the different parameters, in particular the lack of a need to know
   * who the current active player is, and the fact we need to know what category the user has selected.
   * @param  Player[] activePlayers Array of all the current active players, ie. everyone who is taking part in the round.
   * @param  String   categoryName  Name of the user's chosen category.
   */
   public Round(Player[] activePlayers, String categoryName) {

   }
}
