import java.lang.reflect.Array;
import java.util.Random;

public class Shuffle {

	public static void main(String[] args) {
		
		//startGui test = new startGui();
		
		int [] shuffle = new int[40];
			for (int i = 0; i <40; i++)
			{shuffle[i]= i+1;
		//	System.err.println(shuffle[i]);
			}

			Random r = new Random();

			for (int i=0; i<40; i++)
			{int num = r.nextInt(40);
						int temp2 = shuffle[i];
			shuffle[i]=shuffle[num];
			shuffle[num] = temp2;

			}

//			for (int i=0; i<40; i++){
//				System.err
//				.print(" " + shuffle[i] + " ");}
			
			
			Card a = new Card("TRex 6 6 12 9 9");
			Card b = new Card("Barney 8 10 15 9 25");
			Player CPU1 = new Player(2);
			CPU1.addCard(a);
			CPU1.addCard(b);
			Card x = CPU1.getCurrentCard();
			System.out.println(CPU1.getPlayerID());
			System.out.println(CPU1.getStatus());
			System.err.println(x);
			
	 }
		
		
}
