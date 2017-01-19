import java.lang.reflect.Array;
import java.util.Random;

public class Shuffle {

	public static void main(String[] args) {

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
			System.err.println(a);	
			System.out.println(a.displayCard());
			 }
	
		
}
