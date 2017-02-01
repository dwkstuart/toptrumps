import java.util.*;

public class Game {
    // total number of cards to store in the deck
	private final int DECKSIZE = 40; 

    // cards in current round									
	private Card[] roundCards;
	
    //number of players at the start of the game
	private int numPlayers;

    // array of all the current player objects
	private Player[] activePlayers; 

    // player object that holds the cards in the communal pile
	private Player communalPile;

    // indicates which player's turn it is
	private int playerPointer; 

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
	private int drawCount;

    /**
	 * Constructor for the Game object.
	 *
	 * @param int numberOfPlayers Number of players at the start of the Game (as
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
		for (int i = 0; i < DECKSIZE; i++) 
        {
			deck[i] = new Card(categories, deckDetails[i]);
		}

        // Initialize at 0 until cards are added to the array :)
		currentCardIndex = 0; 

		for (int i = 0; i < numPlayers; i++) 
        {
			activePlayers[i] = new Player();
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
	 *               array of Card objects
	 * @param Card[] unshuffledDeck
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
	 * @author  Niall 
     *          Method to deal the deck out equally between the number of
	 *          players. N.B. The user / players with lower index numbers are
	 *          more likely to end up with more cards than those users with
	 *          higher index numbers Â¯\_(ãƒ„)_/Â¯
	 * @param Card[] deckToBeDealt The array of card objects that is to be split
	 *               amongst all players.
	 */
	public void dealCards(Card[] deckToBeDealt) {
		for (int i = 0; i < deckToBeDealt.length; i++) {
			// cycle thru the numbers 0 to (numberOfPlayers-1)
			// add the card at index 'i' to the back of each player's hand.
			activePlayers[i % numPlayers].addCardToHand(deckToBeDealt[i]);
		}
	}

    /**
     * takes in the index of the characteristic that the player has
	 * chosen in the JComboBox or -1 if it is a user turn
	 * @param chosenCharacteristic            
	 * @return outcome method that determines the highest scoring card
	 */

	public void playRound(int chosenCharacteristic) {
		this.populateRoundCards();
		currentChosenCharacteristic = chosenCharacteristic;

        // -1 is default, when not human turn
		if (currentChosenCharacteristic == -1) 				
            {
                currentChosenCharacteristic = setCharacteristic(playerPointer);
            }
		roundCount++;

		// test
		System.out.println("chosen characteristic is " + chosenCharacteristic);
        System.out.println(this.getPlayerPointer());
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
		System.out.println("WINNER INDEX!!! : " + index);
		System.out.println("pass cards to winner : " + index);
	
		if (communalPile.getNumCards() > 0) {
				while (communalPile.getCurrentCard() != null) {
					System.err.println("Passes cards from communal pile to winner");
					activePlayers[index].addCardToHand(communalPile.getCurrentCard());
					communalPile.removeCard();
				}
	
			}
			
			for (int i = 0; i < roundCards.length; i++) {
				activePlayers[index].addCardToHand(roundCards[i]);
			}
	
		
	}
/**Method to pass the current rounds card to the communal pile
 * 
 */
	private void passCardsToCommunalPile(){
		
		
			for (int i = 0; i < roundCards.length; i++) {
				communalPile.addCardToHand(roundCards[i]);
			}
			communalPile.setNumCards();
			System.out.println("Communal Pile cards: " + communalPile.getNumCards());
			//this.removeCardsFromHands();

		
	}

     /**
	 * Finds the characteristic of highest value from the current card of the
	 * player whose turn it is
	 * 
	 * @param playerPointer
	 * @return the index of the chosen characteristic
	 */
	private int setCharacteristic(int playerPointer) {
        // test
		System.err.println("Player Pointer = " + playerPointer);
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
        // test
		// System.err.println("Player pointer is " + playerPointer);
		return playerPointer;
	}

   /**
	 * Method for creating array of current of cards in the round
	 *
	 */
	public void populateRoundCards() {
		for (int i = 0; i < activePlayers.length; i++) {
			if (activePlayers[i].getStatus() == true) {
				roundCards[i] = activePlayers[i].getCurrentCard();
                // test
				//System.err.println("populate round cards  :" + roundCards[i]);
			}
		}
	}

    public void getOutcome(int characteristic) 
    {
        int[] characteristicValues = new int[activePlayers.length];

		int outcome = 0;
		int max = 0;
		int numMaxValue = 0;
        boolean draw = false;

        // sets the current characteristic for each player
		for (int i = 0; i < activePlayers.length; i++) 
        {
			activePlayers[i].setStatus();
			if (activePlayers[i].getStatus() == true) 
            {
				Card currentCard = activePlayers[i].getCurrentCard();
				// test
				//System.out.println(currentCard);
				characteristicValues[i] = currentCard.getCharacteristicValueAt(characteristic);
			} else 
            {
				characteristicValues[i] = 0;
			}
		}

        // find the player whose card had the characteristic of greatest value
		for (int i = 0; i < characteristicValues.length; i++) {
			if (characteristicValues[i] > max) {
				max = characteristicValues[i];
				outcome = i;
			}
		}

        // loops through the characteristics to determine whether the 
        // highest one appears more than once
        for (int i = 0; i < characteristicValues.length; i++) {
			// System.err.println("Characteristic values array " +
			// characteristicValues[i]);
			if (characteristicValues[i] == max) 
            {
				numMaxValue++;
            }
        }

        if(numMaxValue > 1) 
        {
            drawCount++;
            draw = true;
            this.passCardsToCommunalPile();
        }

        // change whose turn it is if necessary
        if (outcome != playerPointer) {
			setPlayerPointer();
		}    

        this.removeCardsFromHands();
        // increment the number of rounds won by the current winner
		roundsWon[outcome]++;

        if(draw == false)
        {
            //test
            System.out.println("Player index " + outcome + " has the highest score");
            this.passCardsToWinner(outcome);
        }
        
    } 

/**
	 * Removes cards from players hands
	 *
	 */
	public void removeCardsFromHands() {
		for (int i = 0; i < activePlayers.length; i++) {
			if (activePlayers[i].getStatus() == true)
				activePlayers[i].removeCard();
		}
	}


    





    /**
	 * sets whose turn it is
	 *
	 */
	private void setPlayerPointer() {
        // test
		System.err.println("Start of method: pp = " + playerPointer);
		playerPointer++;
		if (playerPointer >= activePlayers.length) {
			playerPointer = 0;
			if (activePlayers[playerPointer].getStatus() == false) {
				setPlayerPointer();
			}
		}
        // test
		System.err.println("After incremenent: pp = " + playerPointer);
		activePlayers[playerPointer].setStatus();
		if (activePlayers[playerPointer].getStatus() == false) {
            // test
			System.err.println("found dead player: pp = " + playerPointer);
			setPlayerPointer();
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