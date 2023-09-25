package tictactoe;

import java.util.Random;

class RandomGenerator {
	/**
	 * creates a random number ranging between low and high,  
	 * @param lo
	 * @param hi
	 * @return
	 */
	int discrete(int lo, int hi){
		if(lo >= hi){
			System.out.println("Error discrete, lo >= hi");
			System.exit(0);
		}
		
		Random r = new Random();
		int d = r.nextInt(hi - lo + 1) + lo;
		return d;
	}
	
}
