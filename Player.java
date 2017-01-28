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

	// a method to get the number of players selected
	public int getPlayerNum() {
		return playerNum;
	}

	// a method to store the number of players selected by the user
	public void setPlayerNum(int newPlayerNum) {
		if (newPlayerNum < 0 || newPlayerNum > 100) {
			throw new IllegalArgumentException();
			} 
		else {
			playerNum = newPlayerNum;
			}
		}
  
	
	// Boolean status - if a player is still in the game or not 
	public void setStatus(){
		if(numCards==0)
		status=false;
	}
	
	// getCurrentCard - return the top card in their hand; at 0 in the array
	public Card getCurrentCard() {
		
		return hand[0];
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

	public void setNumCards()
	{
		int cardCount = 0;
		
		while(hand[cardCount] != null)
		{
			cardCount++;
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


}
