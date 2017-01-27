import java.util.*;

public class Game {
	private int numPlayers;
	private Player[] activePlayers; // array of all the current player objects
	private Player communalPile;
	private int playerPointer; // indicates which player's turn it is
	private int roundCount; // number of rounds passed in current game
	private Card[] deck; // array of all cards
	private int currentCardIndex; // the index of the current card being read in
	private int deckSize = 40; // total number of cards to store in the deck
	private int[] roundsWon = new int[] { 0, 0, 0, 0, 0 }; // number of rounds
															// won by each
															// player during the
															// game
	private int winnerIndex;


	/**
	 * Constructor for the Game object.
	 *
	 * @param int
	 *            numberOfPlayers Number of players at the start of the Game (as
	 *            defined in startGui)
	 * @return Game object
	 */
	public Game(int numberOfPlayers) {
		numPlayers = numberOfPlayers;
		activePlayers = new Player[numberOfPlayers];
		communalPile = new Player();

		// create, shuffle and deal an array of card objects amongst all players
		deck = new Card[deckSize];
		currentCardIndex = 0; // Initialize at 0 until cards are added to the
								// array :)

		// IMPORTANT: These methods shouldn't be called here!
		// Will need to shuffle / deal deck once it has been created...
		// so we will have to call em from the GUI?
		// or maybe I am overthinking it...
		// can test proper once the GUI is up and running
		// -Niall x
		deck = shuffleDeck(deck);
		dealCards(deck);

		roundCount = 0;
		playerPointer = 0; // user goes first
	}

	/**
	 * @author Niall Method to create a card object from each line of text
	 *         input, and add it to the deck array in the order it is read in.
	 * @param String
	 *            lineFromInput
	 */
	public void addCardToDeck(String lineFromInput) {
		deck[currentCardIndex] = new Card(lineFromInput);
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
			Card tempCardRef = deck[i]; // ?? will this be ok or will it have to
										// be 'new' ??
			deck[i] = deck[shuffledIndex];
			deck[shuffledIndex] = tempCardRef;
		}
		return deck;
	}

	/**
	 * @author Niall Method to deal the deck out equally between the number of
	 *         players. N.B. The user / players with lower index numbers are
	 *         more likely to end up with more cards than those users with
	 *         higher index numbers ¯\_(ツ)_/¯
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


	public int playRound(int chosenCharacteristic) {
		int value = chosenCharacteristic;

		if (value == 0) // 0 is default, when not human turn
		{
			value = setCharacteristic(playerPointer);
		}
		this.setPlayerPointer();
		return getOutcome(value);
	}

	private int setCharacteristic(int playerPointer) {
		Card currentCard = activePlayers[playerPointer].getCurrentCard();
		int currentCharacteristic = currentCard.getMaxCharacteristic();
		return currentCharacteristic;
	}

	/**
	 *
	 * @param characteristic
	 * @return the winner of the round
	 */
	public int getOutcome(int characteristic) {
		int[] characteristicValues = new int[activePlayers.length];

		int outcome = 0;
		int max = 0;
		int numMaxValue = 0;

		for (int i = 0; i < activePlayers.length; i++) {
			if (activePlayers[i] != null) // This may have to be adjusted to
											// refer to a variable in Player
			{
				Card currentCard = activePlayers[i].getCurrentCard();
				characteristicValues[i] = currentCard.getCharacteristicValueAt(characteristic);
			}
		}

		for (int i = 0; i < characteristicValues.length; i++) {
			if (characteristicValues[i] > max) {
				max = characteristicValues[i];
				outcome = i;
			}

		}
		//when round is a draw
		for (int i = 0; i < characteristicValues.length; i++) {
			if (characteristicValues[i] == max) {
				numMaxValue++;
				if (numMaxValue > 1) {
					return -1;
				}
			}
		}

		roundsWon[outcome]++; //adds
		return outcome;//index of winning player in round
	}

	/**sets who's turn it is
	 *
	 */
	private void setPlayerPointer(){
		if (playerPointer==activePlayers.length-1)
		playerPointer=0;
		else
		{playerPointer++;
		while(activePlayers[playerPointer].getStatus()==false)
			{playerPointer++;

			}
		}
	}
}
