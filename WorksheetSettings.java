package assignment2;

import java.util.List;

public class WorksheetSettings {
	private List<ProblemType> problemTypes;
	private int numProblems;
	private OperandRange operandrange;
	
	public WorksheetSettings(List<ProblemType> problemTypesList, int numProblems, OperandRange operandrange) {
		this.problemTypes = problemTypesList;
		this.numProblems = numProblems;
		this.operandrange = operandrange;
	}
	
	public List<ProblemType> getProblemTypes() {
		return this.problemTypes;
	}
	
	public int getNumProblems() {
		return this.numProblems;
	}
	
	public OperandRange getOperandRange() {
		return this.operandrange;
	}
}
