/**
* Class to store and update the current hand of cards held by a TopTrumps
* player. Also used to get the player's current card, for the purpose of
* knowing which card to play next.
*/

public class Player {

	private final int HANDSIZE = 40;
	private int playerNum;
	private Card[] hand;
	private Card currentCard;
	private int numCards;
	private boolean status=true;

	// constructor
	public Player() {
		hand = new Card[HANDSIZE];
	}

	// a method to check the correct player number is selected
	public void numPlayer(int playerNum) {
		if (playerNum < 1 || playerNum > 4) {
			throw new IllegalArgumentException();
		} else {
			this.playerNum = playerNum;
		}
	}


	// Boolean status - if a player is still in the game or not
	public void setStatus(){
		this.setNumCards();
		if(numCards==0)
		status=false;
	}

	// getCurrentCard - return the top card in their hand; at 0 in the array
	public Card getCurrentCard() {

		return hand[0];
	}
	
	// getCurrentCard - returns an array of strings representing the cards in a players hand used in testing
		public String[] displayPlayerHand() {
			this.setNumCards();
			String handList[]=new String[numCards];
				for(int i=0; i<numCards; i++){
				handList[i]=hand[i].toString();
				}
			return handList;
		}


	//Card; -add a card to the back of their hand, first null value.
	public void addCardToHand(Card cardToAdd) {
		int i=0;	// index in card array

		while (i<40) {
			if(hand[i]==null) {
				hand[i]=cardToAdd;
				break;
			}
			i++;
		}
	}

	public void setNumCards() {
		int cardCount = 0;
		try{
			while(hand[cardCount] != null){
				cardCount++;
			}
		} catch(ArrayIndexOutOfBoundsException e) {
			System.out.println("Player has 40 cards and wins");
		}
		numCards = cardCount;
	}


	// getNumCards - return the number of card objects in the array
	public int getNumCards() {
		return numCards;
	}


	// getStatus - return boolean if they are an active player
	public boolean getStatus() {
		return status;
	}

	public String returnCurrentCardStr() {
		Card cardToBeFormatted = this.getCurrentCard();
		if (cardToBeFormatted == null) {
			return "RIP my dude";
		} else {
			return cardToBeFormatted.formatCardText();
		}
	}

	/**Removes the first card in a players hand from the array and shifts array forward to fill space
	* @author David Stuart
	*/
	public void removeCard(){
		int i=0;
		while (hand[i]!=null){
			hand[i]=hand[i+1];
			i++;
		}
	}

}
