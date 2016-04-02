package assignment2;

import java.util.Random;

public class OperandRange {
	private int minOperand;
	private int maxOperand;
	
	public OperandRange(int min, int max) {
		this.minOperand = min;
		this.maxOperand = max;
	}
	
	public double getRandomOperand() {
		// get random number in range
		Random randomInt = new Random();
		return randomInt.nextInt(maxOperand - minOperand + 1) + minOperand;
	}
}
