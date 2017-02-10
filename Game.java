import java.util.*;

public class Game {
	// total number of cards to store in the deck
	private final int DECKSIZE = 40;

	// cards in current round
	private Card[] roundCards;

	// number of players at the start of the game
	private int numPlayers;

	// array of all the current player objects
	private Player[] activePlayers;

	// player object that holds the cards in the communal pile
	private Player communalPile;

	// indicates which player's turn it is
	private int playerPointer;

	// constant to indicate when it is not the human user's turn to Pick
	// a category
	private int notUser =-1;

	// number of rounds passed in current game
	private int roundCount;

	// array of all cards
	private Card[] deck;

	// characteristics taken from input file
	private String[] categories;

	// the index of the current card being read in
	private int currentCardIndex;

	// number of rounds won by each player during the game
	private int[] roundsWon = new int[] { 0, 0, 0, 0, 0 };

	// overall winner of the game
	private int winnerIndex;

	// the characteristic chosen for the current round
	private int currentChosenCharacteristic;

	// tally of the number of draws in the game
	private int numDraws;

	// Checks if Game has been won
	private boolean isGameOver = false;

	// if round was a draw
	private boolean draw;

	/**
	* Constructor for the Game object.
	*
	* @param int
	*            numberOfPlayers Number of players at the start of the Game (as
	*            defined in startGui)
	* @return Game object
	*/
	public Game(int numberOfPlayers, String[] categories, String[] deckDetails) {
		this.categories = categories;
		numPlayers = numberOfPlayers + 1;
		activePlayers = new Player[numPlayers];
		communalPile = new Player();
		roundCards = new Card[activePlayers.length];

		// create, shuffle and deal an array of card objects amongst all players
		deck = new Card[DECKSIZE];
		// TESTING PRINT OUT
		System.out.println("CONTENTS OF UNSHUFFLED DECK");
		System.out.println("-------------------------------------------------");
		for (int i = 0; i < DECKSIZE; i++) {
			deck[i] = new Card(categories, deckDetails[i]);
			// Testing Print Out
			System.out.println(deck[i]);
		}
		System.out.println("-------------------------------------------------");
		// Initialize at 0 until cards are added to the array :)
		currentCardIndex = 0;
		System.out.println("CONTENTS OF SHUFFLED DECK");
		for (int i = 0; i < numPlayers; i++) {
			activePlayers[i] = new Player();
		}

		deck = shuffleDeck(deck);
		dealCards(deck);

		roundCount = 0;
		playerPointer = (int) (Math.random() * numPlayers); // randomise who starts
	}

	/**
	* @author Niall Method to create a card object from each line of text
	*         input, and add it to the deck array in the order it is read in.
	* @param String
	*            lineFromInput
	*/
	public void addCardToDeck(String lineFromInput) {
		deck[currentCardIndex] = new Card(categories, lineFromInput);
		currentCardIndex++;
	}

	/**
	* @author Niall Method that randomises the indexes of all elements of an
	*         array of Card objects
	* @param Card[]
	*            unshuffledDeck
	*/
	public Card[] shuffleDeck(Card[] deck) {
		Random rand = new Random();
		// cycle thru each card
		for (int i = 0; i < deck.length; i++) {
			int shuffledIndex = rand.nextInt(40);
			Card tempCardRef = deck[i];
			deck[i] = deck[shuffledIndex];
			deck[shuffledIndex] = tempCardRef;
			// TESTING PRINT OUT
			System.out.println(deck[i]);
		}
		System.out.println("-------------------------------------------------");
		return deck;
	}

	/**
	* @author Niall Method to deal the deck out equally between the number of
	*         players. N.B. The user / players with lower index numbers are
	*         more likely to end up with more cards than those users with
	*         higher index numbers Â¯\_(ãƒ„)_/Â¯
	* @param Card[]
	*            deckToBeDealt The array of card objects that is to be split
	*            amongst all players.
	*/
	public void dealCards(Card[] deckToBeDealt) {
		for (int i = 0; i < deckToBeDealt.length; i++) {
			// cycle thru the numbers 0 to (numberOfPlayers-1)
			// add the card at index 'i' to the back of each player's hand.
			activePlayers[i % numPlayers].addCardToHand(deckToBeDealt[i]);
		}
	}

	/**
	* takes in the index of the characteristic that the player has chosen in
	* the JComboBox or -1 if it is a user turn
	*
	* @param chosenCharacteristic
	* @return outcome method that determines the highest scoring card
	*/

	public void playRound(int chosenCharacteristic) {
		this.populateRoundCards();
		currentChosenCharacteristic = chosenCharacteristic;

		// -1 is default, when not human turn
		if (currentChosenCharacteristic == notUser) {
			currentChosenCharacteristic = setCharacteristic(playerPointer);
		}
		roundCount++;
		this.getOutcome(currentChosenCharacteristic);

	}

	/**
	* @param index
	*            of winning player in array or -1 if result was a draw passes
	*            the round card pile to the winning player if previous round
	*            was a draw adds the communal pile to the winning players hand
	*/
	private void passCardsToWinner(int index) {

		// if round was a draw cards are passed to communalPile Player instance

		if (communalPile.getNumCards() > 0) {
			while (communalPile.getCurrentCard() != null) {
				activePlayers[index].addCardToHand(communalPile.getCurrentCard());
				communalPile.removeCard();

			}

		}

		for (int i = 0; i < roundCards.length; i++) {
			activePlayers[index].addCardToHand(roundCards[i]);
		}

	}

	/**
	* Method to pass the current rounds card to the communal pile
	*
	*/
	private void passCardsToCommunalPile() {

		for (int i = 0; i < roundCards.length; i++) {
			communalPile.addCardToHand(roundCards[i]);
		}
		communalPile.setNumCards();

		// Testing system out
		System.out.println("\nCards in Communal Pile");
		// passes -1 as variable to indicate it is the communal pile
		this.printHand(-1);

	}

	/**
	* Finds the characteristic of highest value from the current card of the
	* player whose turn it is
	*
	* @param playerPointer
	* @return the index of the chosen characteristic
	*/
	private int setCharacteristic(int playerPointer) {
		Card currentCard = activePlayers[playerPointer].getCurrentCard();
		int currentCharacteristic = currentCard.getMaxCharacteristic();
		return currentCharacteristic;
	}

	/**
	* Method that returns the index of the player currently choosing the
	* characteristic
	*
	* @return playerPointer
	*/
	public int getPlayerPointer() {
		return playerPointer;
	}

	/**
	* Method for creating array of current of cards in the round
	*
	*/
	public void populateRoundCards() {
		System.out.println("-----------------------------");
		System.out.println("Cards in current round");
		for (int i = 0; i < activePlayers.length; i++) {
			if (activePlayers[i].getStatus() == true) {
				roundCards[i] = activePlayers[i].getCurrentCard();
				// Testing
				System.out.println(roundCards[i]);
			}
		}
	}

	public void getOutcome(int characteristic) {
		int[] characteristicValues = new int[activePlayers.length];

		int outcome = this.getPlayerPointer();
		int max = 0;
		int numMaxValue = 0;
		draw = false;

		//TESTING SYSTEM OUT
		System.out.println("\n The choosen characteristic for the round was " + this.categories[characteristic]);
		// sets the current characteristic for each player
		for (int i = 0; i < activePlayers.length; i++) {
			activePlayers[i].setStatus();
			if (activePlayers[i].getStatus() == true) {
				Card currentCard = activePlayers[i].getCurrentCard();
				characteristicValues[i] = currentCard.getCharacteristicValueAt(characteristic);
			} else {
				characteristicValues[i] = 0;
			}
		}

		// find the player whose card had the characteristic of greatest value
		for (int i = 0; i < characteristicValues.length; i++) {

			//TESTING OUT
			System.out.println("\n Player " + i + " value for choosen characteristic was " +characteristicValues[i]);

			if (characteristicValues[i] > max) {
				max = characteristicValues[i];
				outcome = i;

			}
		}

		// loops through the characteristics to determine whether the
		// highest one appears more than once
		for (int i = 0; i < characteristicValues.length; i++) {
			if (characteristicValues[i] == max) {
				numMaxValue++;
			}
		}

		if (numMaxValue > 1) {
			numDraws++;
			draw = true;
			this.passCardsToCommunalPile();

		}

		// change whose turn it is if necessary
		if (outcome != playerPointer) {
			playerPointer = outcome;
		}

		this.removeCardsFromHands();
		
		
				

		if (draw == false) {
			this.passCardsToWinner(outcome);
			// increment the number of rounds won by the current winner
			roundsWon[outcome]++;
		}
		this.getGameOver();

	}

	/**
	* Removes cards from players hands
	*
	*/
	public void removeCardsFromHands() {
		// Testing printouts
		System.out.println("-----------------------------");
		System.out.println("Player Decks after round");
		for (int i = 0; i < activePlayers.length; i++) {
			if (activePlayers[i].getStatus() == true) {
				activePlayers[i].removeCard();
			}
			// Testing Print out
			System.out.println("\nPlayer's " + i + " Deck is ");
			this.printHand(i);

		}

	}

	/**
	* Method to print out the cards in a players hand
	*
	* @param index
	*            of player in activePLayer array or -1 when communalPile
	*/
	private void printHand(int playerIndex) {
		String displayHand[];
		if (playerIndex == -1) {
			displayHand = communalPile.displayPlayerHand();

		} else {
			displayHand = activePlayers[playerIndex].displayPlayerHand();
		}

		for (int k = 0; k < displayHand.length; k++) {
			System.out.println(displayHand[k]);
		}

	}

	/**
	* Returns the card at an index of the round cards array
	*
	* @param i
	* @return
	*/
	public Card roundCardatIndex(int i) {
		return roundCards[i];
	}

	/**
	* Method to check if someone has won
	*
	*/

	public void gameOver() {
		int total = 0;
		for (int i = 0; i < numPlayers; i++) {
			activePlayers[i].setNumCards();
			total = activePlayers[i].getNumCards();
			if (total == 40) {
				winnerIndex = i;
				isGameOver = true;
				//Testing System Out
				System.out.println("The WINNER IS " + "Player " + winnerIndex);
			}
		}
	}

	/**
	* Method to return the array of cards
	*
	* @return the array of cards from the round
	*/
	public Card[] getRoundCards() {
		return roundCards;
	}

	public int getCommunalCount() {
		communalPile.setNumCards();
		int commCount = communalPile.getNumCards();
		return commCount;
	}

	public boolean getGameOver() {
		this.gameOver();
		return isGameOver;
	}

	/**
	 * In the case that the user player dies, this method plays through the
	 * computer-only rounds to prevent a user having to click through each
	 * game play.
	 */
	public void playToEnd() {
		// while this game ISN'T over
		while(!(this.getGameOver())) {
			// notUser signals it is not user's turn
			this.playRound(notUser);
		}
		new GameOverStats(this);

	}

	/**
	*
	* @param playerIndex
	* @return a player object
	*/
	public Player getActivePlayer(int playerIndex) {
		return activePlayers[playerIndex];
	}

	public int getNumRounds() {
		return roundCount;
	}

	public int getRoundsWon(int index) {
		return roundsWon[index];
	}

	public int getNumDraws() {
		return numDraws;
	}

	public int getWinnerIndex() {
		return winnerIndex;

	}

	public int getCurrentChosenCharacteristic() {
		return currentChosenCharacteristic;
	}

	public boolean roundWasDraw() {
		return draw;
	}

}
