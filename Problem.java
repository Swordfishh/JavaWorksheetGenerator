

import java.util.List;

public abstract class Problem {
	protected List<Double> operands;
	protected boolean answeredCorrectly;
	
	
	public Problem() {
		
	}
	
	public static Problem create(ProblemType problemType, OperandRange operandRange) {
		switch (problemType) {
			case Addition:
				return new AdditionProblem(problemType, operandRange);
			case Subtraction:
				return new SubtractionProblem(problemType, operandRange);
			case Multiplication:
				return new MultiplicationProblem(problemType, operandRange);
			case Division:
				return new DivisionProblem(problemType, operandRange);
		}
		return null;
		
	}
	
	public abstract boolean checkAnswer(String user_answer);
	public abstract String getAnswer();
	public abstract boolean isAnsweredCorrectly();
	
	public abstract List<Double> getOperands();
	
	public abstract ProblemType getProblemType();
}
