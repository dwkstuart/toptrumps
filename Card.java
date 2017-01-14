public class Card {

	private String description = "";
	private int height, weight, length, ferocity, intelligence;

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

	public String getDescription() {
		return description;
	}

	public int getIntelligence() {
		return intelligence;
	}

	public int getFerocity() {
		return ferocity;
	}

	public int getLength() {
		return length;
	}

	public int getHeight() {
		return height;
	}

	public int getWeight() {
		return weight;
	}

	public String toString()
	{String card = String.format("The %s has %d height, %d weight, %d length, %d ferocity and %d intelligence", 
	description, height, weight, length,  ferocity, intelligence);
		return card; }

}