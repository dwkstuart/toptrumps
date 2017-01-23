/**
 * Class to store and update the current hand of cards held by a TopTrumps
 * player. Also used to get the player's current card, for the purpose of
 * knowing which card to play next.
 */

public class Player {

	// Instance variables- player id, an int between 0 and 4
	private int playerID;
	private int playerNum;
	private Card[] playerCards = new Card[40];// Is this what you meant here?
	private Card currentCard;
	private int numCards;
	private boolean active=true;

	// constructor
	public Player(int player_ID) {
		playerID = player_ID;
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
			playerNum = newPlayerNum;}
			}
  
	
	// Boolean active - if a player is still in the game or not ?not sure if
	// this is needed, could count number of cards in hand if =0 then out the game
	public void setActive(boolean ingame){
	active=ingame;
	
	if(active)

	{
		System.out.println("I'm in the game");
	}

	active=false;if(!active)
	{
		System.out.println("I'm out of the game");
	}
	}
	// getCurrentCard - return the top card in their hand; at 0 in the array
	public Card getCurrentCard() {
		return currentCard;
	}
	// addCard; -add a card to the back of their hand, first null value.
	public void addCard(Card a) {
	
	int i=0;
	
	while (i<playerCards.length) {
		
		if(playerCards[i]==null) {
			playerCards[i]=a;
			break;
		}
		i++;
	}
	}


	// removeCard? - remove top card from the hand and shift array to fill gap
	// at 0
	// (not sure if we need this as well as getCurrentCard)

	// getNumCards - return the number of card objects in the array
	public int getNumCards() {
		return numCards;
	}

	// getID

	public int getPlayerID() {
		return playerID;
	}

	// getStatus - return boolean if they are an active player or not
	public boolean getStatus() {
		return active;}
// setStatus ?might not be needed


}
