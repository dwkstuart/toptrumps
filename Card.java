/** Class that holds the decriptions and values of a single top trump card
*/
public class Card {

	private String description = "";
	private int height, weight, length, ferocity, intelligence;
	
	/**@param String details, string passed in with description and values 
	*	from text file
	*sets the instance variables
	*/
	public Card(String details) {
		String info = details;
		String[] values = info.split(" +");
		// values[]= info.split(" ");

		description = values[0];
		height = Integer.parseInt(values[1]);
		weight = Integer.parseInt(values[2]);
		length = Integer.parseInt(values[3]);
		ferocity = Integer.parseInt(values[4]);
		intelligence = Integer.parseInt(values[5]);

	}
	
	/*@return  return the card description 
	*/
	public String getDescription() {
		return description;
	}

	/*@return  return the instance vairable intelligence
	*/
	public int getIntelligence() {
		return intelligence;
	}

	/*@return  return the the instance variable ferocity
	*/
	public int getFerocity() {
		return ferocity;
	}

	/*@return  return the instance variable length
	*/
	public int getLength() {
		return length;
	}

	/*@return  return the instance variable height
	*/
	public int getHeight() {
		return height;
	}

	/*@return  return the instance variable height
	*/
	public int getWeight() {
		return weight;
	}

	/*@return  return the card description with values
	*/
	public String toString()
	{String card = String.format("The %s has %d height, %d weight, %d length, %d ferocity and %d intelligence", 
	description, height, weight, length,  ferocity, intelligence);
		return card; }

}
