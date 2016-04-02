

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DivisionProblem extends Problem {
	
	protected List<Double> operands;	
	private ProblemType thisProblemType;
	
	
	public DivisionProblem (ProblemType problemType, OperandRange operandRange) {
		this.thisProblemType = problemType;
		double operand1 = operandRange.getRandomOperand();
		double operand2 = operandRange.getRandomOperand();

		operands = new ArrayList<>(Arrays.asList(operand1, operand2));
	}
	
	public boolean checkAnswer(String user_answer) {
		double user_answer_double = Double.parseDouble(user_answer);
		double this_answer_double = Double.parseDouble(this.getAnswer());
		this.answeredCorrectly = (user_answer_double-0.1 <= this_answer_double && 
								  user_answer_double+0.1 >= this_answer_double);
		
		return this.answeredCorrectly;
	}
	
	public String getAnswer() {
		double answer = operands.get(0)/operands.get(1);
		return String.valueOf(answer);
	}
	
	public boolean isAnsweredCorrectly() {
		return answeredCorrectly;
	}
	
	public List<Double> getOperands() {
		return operands;
	}
	
	public ProblemType getProblemType() {
		return thisProblemType;
	}
}
