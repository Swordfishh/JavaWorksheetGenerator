package assignment2;

import java.util.ArrayList;
import java.util.List;

public class Worksheet {
	private static List<Problem> problems;
	private int total_correct;
	
	public static Worksheet generate(WorksheetSettings settings) {
		problems = new ArrayList<Problem>();
		
		int totalNumOfOps = settings.getProblemTypes().size();
		int numOfProbsEach = settings.getNumProblems() / totalNumOfOps;
		
		for (int numOfOps=0; numOfOps<totalNumOfOps; numOfOps++) {
			for (int numOfProbs=0; numOfProbs<numOfProbsEach; numOfProbs++) {
				ProblemType problem_type = settings.getProblemTypes().get(numOfOps);
				Problem p = Problem.create(problem_type, settings.getOperandRange());
				 
				addProblem(p);
			}
		}

		return new Worksheet();
	}
	
	public static void addProblem(Problem problem) {
		problems.add(problem);
	}
	
	public Problem getProblem(int index) {
		return problems.get(index);
	}
	
	public int getNumProblems() {
		return problems.size();
	}
	
	public void incrementTotalCorrect() {
		this.total_correct++;
	}
	
	public int getNumCorrect() {
		return total_correct;
	}
	
}
