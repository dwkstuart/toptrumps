import java.util.*;

public class Game {
	private final int DECKSIZE = 40; // total number of cards to store in the deck
	private Card [] roundCards;//cards in current round
	private boolean lastRoundDraw = false; //boolean tracking if last round was a draw or not
	private int numPlayers;
	private Player[] activePlayers; // array of all the current player objects
	private Player communalPile;
	private int playerPointer; // indicates which player's turn it is
	private int roundCount; // number of rounds passed in current game
	private Card[] deck; // array of all cards
	private String[] categories;
	private int currentCardIndex; // the index of the current card being read in
	//private String category;
	private int[] roundsWon = new int[] { 0, 0, 0, 0, 0 }; // number of rounds
	// won by each
	// player during the
	// game
	private int winnerIndex; //overall winner of the game
	private int currentChosenCharacteristic;
	private int drawCount;

	/**
	* Constructor for the Game object.
	*
	* @param int
	*            numberOfPlayers Number of players at the start of the Game (as
	*            defined in startGui)
	* @return Game object
	*/
	public Game(int numberOfPlayers, String[] categories, String [] deckDetails) {
		this.categories = categories;
		numPlayers = numberOfPlayers +1;
		activePlayers = new Player[numPlayers];
		communalPile = new Player();
		roundCards = new Card[activePlayers.length];

		// create, shuffle and deal an array of card objects amongst all players
		deck = new Card[DECKSIZE];
		for (int i=0; i<DECKSIZE; i++)
		{
			deck[i]= new Card (categories, deckDetails[i]);
		}
		currentCardIndex = 0; // Initialize at 0 until cards are added to the
		// array :)
		for (int i=0; i<numPlayers; i++){
			activePlayers[i]= new Player();
		}

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
	/**
	*
	* @param chosenCharacteristic takes in the index of the characteristic that the player has chosen
	* in the JComboBox or -1 if it is a user turn
	* @return outcome method that determines the highest scoring card
	*/

	public int playRound(int chosenCharacteristic) {
		this.populateRoundCards();
		currentChosenCharacteristic = chosenCharacteristic;

		if (currentChosenCharacteristic == -1) // -1 is default, when not human turn
		{
			currentChosenCharacteristic = setCharacteristic(playerPointer);
		}
		roundCount++;
		//test
		System.out.println("chosen characteristic is " + chosenCharacteristic);
		this.getPlayerPointer();
		return getOutcome(currentChosenCharacteristic);


	}

	/**Method that returns the index of the player currently choosing the characteristic
	*
	* @return playerPointer
	*/
	public int getPlayerPointer() {
		// System.err.println("Player pointer is " + playerPointer);
		return playerPointer;
	}

	/**
	* Finds the characteristic of highest value from the
	* current card of the player whose turn it is
	* @param playerPointer
	* @return the index of the chosen characteristic
	*/
	private int setCharacteristic(int playerPointer) {
		Card currentCard = activePlayers[playerPointer].getCurrentCard();
		int currentCharacteristic = currentCard.getMaxCharacteristic();
		return currentCharacteristic;
	}
	/**Method for creating array of current of cards in the round
	*
	*/
	public void populateRoundCards(){
		for (int i=0; i<activePlayers.length; i++) {
			if (activePlayers[i].getStatus()==true){
				roundCards[i]=activePlayers[i].getCurrentCard();
			}
		}
	}

	/**
	*
	* @param characteristic
	* @return the winner of the round or -1 if there is a draw
	*/
	public int getOutcome(int characteristic) {
		int[] characteristicValues = new int[activePlayers.length];

		int outcome = 0;
		int max = 0;
		int numMaxValue = 0;


		//sets the current characteristic for each player
		for(int i = 0; i < activePlayers.length; i++) {
			if (activePlayers[i].getStatus() == true)
			{
				Card currentCard = activePlayers[i].getCurrentCard();
				//test
				System.out.println(currentCard);
				characteristicValues[i] = currentCard.getCharacteristicValueAt(characteristic);
			}
			else
			{
				characteristicValues[i] = 0;
			}
		}

		//find the player whose card had the characteristic of greatest value
		for(int i = 0; i < characteristicValues.length; i++) {
			if (characteristicValues[i] > max) {
				max = characteristicValues[i];
				outcome = i;
			}
		}

		//determine whether there has been a draw by checking
		//if the max value appears more than once in the array
		for(int i = 0; i < characteristicValues.length; i++) {
			// System.err.println("Characteristic values array " + characteristicValues[i]);
			if (characteristicValues[i] == max) {

				numMaxValue++;
				if (numMaxValue > 1) {
					lastRoundDraw = true; //sets instance variable that last round is a draw
					drawCount++; //tracks number of draws in a game
					this.removeCardsFromHands();
					return -1;
				}
			}
		}

		//change whose turn it is if necessary
		if(outcome != playerPointer)
		{	lastRoundDraw = false;
			setPlayerPointer();
		}

		this.removeCardsFromHands();
		roundsWon[outcome]++; //increments the number of rounds won by the current winner


		//test
		System.out.println("Player index " + outcome + "  has the highest score");
		this.passCardsToWinner(outcome);
		return outcome;//index of winning player in round
	}

	/**Removes cards from players hands
	*
	*/
	public void removeCardsFromHands()
	{
		for (int i=0; i<activePlayers.length; i++)
		{
			if (activePlayers[i].getStatus()==true)
			activePlayers[i].removeCard();
		}
	}

	/**@param index of winning player in array or -1 if result was a draw
	* passes the round card pile to the winning player
	* if previous round was a draw adds the communal pile to the winning players hand
	*/
	private void passCardsToWinner(int index){
		//if round was a draw cards are passed to communalPile Player instance
		System.out.println("WINNER INDEX!!! : " + index);
		if (index == -1){//current round is a draw
			for(int i=1; i<roundCards.length; i++){
				communalPile.addCardToHand(roundCards[i]);
				break;}
			}
			if (communalPile.getCurrentCard()!=null){
				while(communalPile.getCurrentCard() != null){
					activePlayers[index].addCardToHand(communalPile.getCurrentCard());
					communalPile.removeCard();
				}

			}
			for(int i=0; i<roundCards.length; i++){
				activePlayers[index].addCardToHand(roundCards[i]);
			}
		}

		/**
		* sets who's turn it is
		*
		*/
		private void setPlayerPointer() {
			// if (playerPointer == activePlayers.length - 1) {
			// 	playerPointer = 0;
			// } else {
			// 	playerPointer++;
			// 	while (activePlayers[playerPointer].getStatus() == false) {
			// 		playerPointer++;
			// 	}
			// }
			System.err.println("Start of method: pp = " + playerPointer);
			playerPointer++;
			if (playerPointer >= activePlayers.length) {
				playerPointer = 0;
				if (activePlayers[playerPointer].getStatus() == false) {
					setPlayerPointer();
				}
			}
			System.err.println("After incremenent: pp = " + playerPointer);
			if (activePlayers[playerPointer].getStatus() == false) {
				System.err.println("found dead player: pp = " + playerPointer);
				playerPointer++;
			}
		}

		/**
		*
		* @param playerIndex
		* @return a player object
		*/
		public Player getActivePlayer(int playerIndex) {
			return activePlayers[playerIndex];
		}
	}
