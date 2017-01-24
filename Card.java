/** Class that holds the decriptions and values of a single top trump card
*/
public class Card {
	private String [] category = new String[]{"description", "height", "weight", "length", "ferocity", "intelligence"};
	private String description = "";
	private int height, weight, length, ferocity, intelligence;
	private String[] values = new String[6];
	private int[] characteristicvalues;
	/**@param String details, string passed in with description and values 
	*	from text file
	*sets the instance variables
	*/
	public Card(String details) {
		String info = details;
		values = info.split(" +");
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

	/**Method to return the maximum value of a card
	 * used in round
	 * 
	 * @return highest vale of card
	 */
			
	public int getMaxCharacteristic()
	{ int max =0;
		
	characteristicvalues[0]=height;
	characteristicvalues[1]=weight;
	characteristicvalues[2]=length;
	characteristicvalues[3]=ferocity;
	characteristicvalues[4]=intelligence;
	
	for(int i=0; i<characteristicvalues.length; i++)
		{if (characteristicvalues[i]>max)
		max=characteristicvalues[i];
		}
		
		
		return max;
		
	}
	/*@return  return the card description with values
	*/
	public String toString()
	{String card = String.format("The %s has %d height, %d weight, %d length, %d ferocity and %d intelligence", 
	description, height, weight, length,  ferocity, intelligence);
		return card; }
	
	/*@return  return the card description with valuesn in columns
	*/
	public String displayCard()
	{	String display = null;
		StringBuilder build = new StringBuilder();
		String header ="DINO TOP TRUMPS!!!\n";
		build.append(header);
		build.append("\t" + description+"\n");
		for(int i=1; i<6; i++)
		{
		display = String.format("%-15s: %4s\n" , category[i], values[i]);
		build.append(display);
		}
		
		String out = build.toString();
		return out;
	}

	//returns characteristic at specific index
	public int getCharacteristicValueAt(int characteristic) {
		// TODO Auto-generated method stub
		return 0;
	}
}
