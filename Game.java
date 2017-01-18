
public class Game{
   private int numCurrentPlayers;
   private Player[] activePlayers[];   // array of all the current player objects

   private String category;
   private int playerPointer;          // indicates which player's turn it is
   private int roundCount;             // number of rounds passed in current game

   // constructor
   public Game(int numberOfPlayers) {
      Card[] deck = createDeck();
      dealCards[deck];
      numCurrentPlayers = numberOfPlayers;
      activePlayers = new Player[numberOfPlayers];
      roundCount++;
      playerPointer = 0;      // user goes first
   }


   /**
    * Method to create an array of card objects and subsequently randomise the order
    * of their indexes.
    */
   public Card[] createDeck() {

   }

   /**
    * Method to deal the deck out equally between the number of players.
    * @param Card[] deckToBeDealt [description]
    */
   public void dealCards(Card[] deckToBeDealt) {

   }

   /**
    * Method to instansiate a new round of Top Trumps, in which it is the user's
    * turn to select a category.
    */
   public void createUserRound(String chosenCategory) {
      // case that it is the user's turn to select a category.
      Round currentRound = new Round(activePlayers, chosenCategory);
      roundCount++;
      playerPointer++;
   }
   /**
    * Method to instansiate a new round of Top Trumps, in which it is the computer's
    * turn to select a category.
    */
   public void createCPURound() {
      // case that it is a CPU player's turn to select a category.
      Round currentRound = new Round(activePlayers, playerPointer);
      roundCount++;
      playerPointer++;
   }
}
