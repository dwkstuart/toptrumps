/**
 * Class to store and update the current hand of cards held by a TopTrumps
 * player. Also used to get the player's current card, for the purpose of
 * knowing which card to play next.
 */

public class Player {
//Instance Variables
	
	private final int HANDSIZE = 40;
	private Card[] hand;
	private Card currentCard;
	private int numCards;
	private boolean status = true;

	// constructor
	public Player() {
		hand = new Card[HANDSIZE];
	}


	/**Sets the status of the player within a game
	 * 
	 *Checks if they have any cards 
	 */
	public void setStatus() {
		this.setNumCards();
		if (numCards == 0)
			status = false;
	}

	/**getCurrentCard - return the top card in their hand; 
	 * at 0 in the array of players deck
	 * @return Card
	 */
	public Card getCurrentCard() {
		return hand[0];
	}

	/**Method that return an array of strings with the 
	 * details of all the cards in a player's deck. Used for testing printouts
	 * 
	 * @return String array
	 */
	public String[] displayPlayerHand() {
		this.setNumCards();
		String handList[] = new String[numCards];
		for (int i = 0; i < numCards; i++) {
			handList[i] = hand[i].toString();
		}
		return handList;
	}

	/**Method that adds a card to the back 
	 * a players deck, replaces first null element
	 * in the array
	 * @param cardToAdd
	 */
	public void addCardToHand(Card cardToAdd) {
		int i = 0; // index in card array

		while (i < 40) {
			if (hand[i] == null) {
				hand[i] = cardToAdd;
				break;
			}
			i++;
		}
	}

	/**Method that counts the number of cards in a players hand
	 * updates the instance variable
	 */
	public void setNumCards() {
		int cardCount = 0;
		try {
			while (hand[cardCount] != null) {
				cardCount++;
			}
		} catch (ArrayIndexOutOfBoundsException e) {
			// Exception thrown when a player has 40 cards and has won the game
		}
		numCards = cardCount;
	}

	/**Method to return the instance variable of number 
	 * of cards in a players hand 
	 * @return int of number of cards
	 */
	public int getNumCards() {
		return numCards;
	}

	/**Method that returns the boolean 
	 * of if player is active in a game or not 
	 * @return boolean value
	 */
	public boolean getStatus() {
		return status;
	}

	/**
	 * Method returns a formatted string with details of card
	 * 
	 * @return String
	 */
	public String returnCurrentCardStr() {
		Card cardToBeFormatted = this.getCurrentCard();
		if (cardToBeFormatted == null) {
			return "RIP my dude";
		} else {
			return cardToBeFormatted.formatCardText();
		}
	}

	/**
	 * Removes the first card in a players hand from the array and shifts array
	 * forward to fill space
	 */
	public void removeCard() {
		int i = 0;
		while (hand[i] != null) {
			hand[i] = hand[i + 1];
			i++;
		}
	}

}
