import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class AdditionProblem extends Problem {
	
	protected List<Double> operands;	
	private ProblemType thisProblemType;
	
	
	public AdditionProblem (ProblemType problemType, OperandRange operandRange) {
		this.thisProblemType = problemType;
		double operand1 = operandRange.getRandomOperand();
		double operand2 = operandRange.getRandomOperand();

		operands = new ArrayList<>(Arrays.asList(operand1, operand2));
	}
	
	private double toDouble(String s) {
		return Double.parseDouble(s);
	}
	
	public boolean checkAnswer(String user_answer) {
		this.answeredCorrectly = (this.toDouble(user_answer) == this.toDouble(this.getAnswer()));
		
		return this.answeredCorrectly;
	}
	
	private double answer() {
		return operands.get(0)+operands.get(1);
	}
	
	public String getAnswer() {
		return String.valueOf(this.answer());
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
