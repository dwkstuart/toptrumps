
public class Game{
   private int numCurrentPlayers;
<<<<<<< HEAD
   private Player[] activePlayers;   // array of all the current player objects
=======
   private Player[] activePlayers[];   // array of all the current player objects
>>>>>>> 573a70dd7d2ff9096e91935e01fcf02123e4f4e7

   private String category;
   private int playerPointer;          // indicates which player's turn it is
   private int roundCount;             // number of rounds passed in current game

   // constructor
   public Game(int numberOfPlayers) {
      Card[] deck = createDeck();
<<<<<<< HEAD
      dealCards(deck);
=======
      dealCards[deck];
>>>>>>> 573a70dd7d2ff9096e91935e01fcf02123e4f4e7
      numCurrentPlayers = numberOfPlayers;
      activePlayers = new Player[numberOfPlayers];
      roundCount++;
      playerPointer = 0;      // user goes first
   }


   /**
<<<<<<< HEAD
   * Method to create an array of card objects and subsequently randomise the order
   * of their indexes.
   */
   public Card[] createDeck() {
      // UNFINISHED
      return null;
   }

   /**
   * Method to deal the deck out equally between the number of players.
   * @param Card[] deckToBeDealt [description]
   */
   public void dealCards(Card[] deckToBeDealt) {
      // UNFINISHED
   }

   /**
   * Method to instansiate a new round of Top Trumps, in which it is the user's
   * turn to select a category.
   */
=======
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
>>>>>>> 573a70dd7d2ff9096e91935e01fcf02123e4f4e7
   public void createUserRound(String chosenCategory) {
      // case that it is the user's turn to select a category.
      Round currentRound = new Round(activePlayers, chosenCategory);
      roundCount++;
      playerPointer++;
   }
   /**
<<<<<<< HEAD
   * Method to instansiate a new round of Top Trumps, in which it is the computer's
   * turn to select a category.
   */
=======
    * Method to instansiate a new round of Top Trumps, in which it is the computer's
    * turn to select a category.
    */
>>>>>>> 573a70dd7d2ff9096e91935e01fcf02123e4f4e7
   public void createCPURound() {
      // case that it is a CPU player's turn to select a category.
      Round currentRound = new Round(activePlayers, playerPointer);
      roundCount++;
      playerPointer++;
   }
}
