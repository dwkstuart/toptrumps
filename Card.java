/**
* Class that holds the descriptions and values of a single top trump card
*/
public class Card {
	private String[] category;
	private String description = "";
	private int height, weight, length, ferocity, intelligence;
	private String[] input = new String[6];
	private int[] cardvalues = new int[5];

	/**
	* @param String
	*            details, string passed in with description and values from
	*            text file sets the instance variables
	*/
	public Card(String[] categories, String details) {
		this.category = categories;
		String info = details;

		input = info.split(" +");

		description = input[0];
		cardvalues[0] = Integer.parseInt(input[1]);
		cardvalues[1] = Integer.parseInt(input[2]);
		cardvalues[2] = Integer.parseInt(input[3]);
		cardvalues[3] = Integer.parseInt(input[4]);
		cardvalues[4] = Integer.parseInt(input[5]);

	}


	/**
	* @param which
	*            index of which category vale to return
	* @return specific value from a card
	*/
	public int getValueatIndex(int index) {
		return cardvalues[index];
	}

	/**
	* Method to return the index position of the maximum value in a card
	*
	* @return index of highest characteristic
	*/
	public int getMaxCharacteristic() {
		int max = 0;
		int indexofValue = 0;

		for (int i = 0; i < cardvalues.length; i++) {
			if (cardvalues[i] > max) {
				max = cardvalues[i];
				indexofValue = i;
			}
		}

		return indexofValue;
	}

	/*
	* @return return the card description with values
	*/
	public String toString() {
		String card = String.format("%-20s %s: %2d  %s: %2d %s: %2d %s: %2d %s: %2d ",
		description, category[0], cardvalues[0], category[1], cardvalues[1], category[2], cardvalues[2], category[3], cardvalues[3], category[4], cardvalues[4]);//method for importing category list is decided
		return card;
	}


	/*
	* @return return the card description with values in columns
	*/
	public String formatCardText() {
		String display = null;
		StringBuilder build = new StringBuilder();
		String header = " ~ Dino Top Trumps ~\n\n";
		build.append(header);
		build.append(description + "\n\n");
		for (int i = 0; i < 5; i++) {
			display = String.format("%-15s: %4s\n", category[i], cardvalues[i]);
			build.append(display);
		}

		String out = build.toString();
		return out;
	}

	public String getDescription() {
		return description;
	}


	// returns characteristic at specific index
	public int getCharacteristicValueAt(int characteristic) {

		int charValue =cardvalues[characteristic];
		return charValue;
	}


}
