 public class Card
 {
 
 private String description;
 private int height, weight, length,  ferocity, intelligence; 
 
 public Card(String details){
	String info = details;
	values[]= info.split(" ");
 
	description = info [0];
	height = Interger.parseInt(info[1]);	
	weight = Interger.parseInt(info[2]);			
	length = Interger.parseInt(info[3]);	
	ferocity = Interger.parseInt(info[4]);	
	intelligence = Interger.parseInt(info[5]);
 }
 
 public String getDescription(){
 return description;}
 
 public int getIntelligence() {
return intelligence;}
 
 public int getFerocity(){
 return feocity;}
 
 public int getLength() {
return length;}
 
 public int getHeight() {
return height;}
 
 public int getWeight() {
return weight;}
 
 public String toString {
return String card = String.format("The %s has %d height, %d weight, %d length, %d ferocity and %d intelligence", 
	description, height, weight, length,  ferocity, intelligence); }
 
}