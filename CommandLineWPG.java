package assignment2;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CommandLineWPG extends WordProblemGenerator {
	private Profile childProfile;
	private Worksheet worksheet;
	private List<Template> templates;
	
	private Scanner reader = new Scanner(System.in);
	
	public void promptUserProfileInfo() {
		List<Person> peopleList = new ArrayList<Person>();
		List<Item> itemList = new ArrayList<Item>();
		
		// Enter male names
		System.out.println("Enter a male name or (c)ontinue: ");
		String person_input = reader.nextLine();
		
		while (!person_input.equals("c")) {
			Person person = new Person(person_input, "He");
			peopleList.add(person);
			
			System.out.print("Names: ["+peopleList.get(0).getName());
			for (int i=1; i<peopleList.size(); i++) {
				System.out.print(", "+peopleList.get(i).getName());
			}
			System.out.println("]");
			
			System.out.println("Enter a male name or (c)ontinue: ");
			person_input = reader.nextLine();
		}
		
		// Enter female names
		System.out.println("Enter a female name or (c)ontinue: ");
		person_input = reader.nextLine();
		
		while (!person_input.equals("c")) {
			Person person = new Person(person_input, "She");
			peopleList.add(person);
			
			System.out.print("Names: ["+peopleList.get(0).getName());
			for (int i=1; i<peopleList.size(); i++) {
				System.out.print(", "+peopleList.get(i).getName());
			}
			System.out.println("]");
			
			System.out.println("Enter a female name or (c)ontinue: ");
			person_input = reader.nextLine();
		}
				
		// Items
		System.out.println("Enter an item or (c)ontinue: ");
		String item_input = reader.nextLine();
		
		while (!item_input.equals("c")) {
			Item item = new Item(item_input);
			itemList.add(item);
			
			System.out.print("Items: ["+itemList.get(0).getName());
			for (int i=1; i<itemList.size(); i++) {
				System.out.print(", "+itemList.get(i).getName());
			}
			System.out.println("]");
			
			System.out.println("Enter an item or (c)ontinue: ");
			item_input = reader.nextLine();
		}
		
		childProfile = new Profile(peopleList, itemList);
	}
	
	public void promptWorksheetSettings() {
		List<ProblemType> problemTypesList = new ArrayList<ProblemType>();
		
		// Problem Types
		System.out.println("Enter problem type -- \n  (a)ddition, (s)ubtraction, (m)ultiplication, (d)ivision or (c)ontinue: ");
		String problem_type_input = reader.nextLine();
		
		while (!problem_type_input.equals("c")) {
			
			switch (problem_type_input) {
				case "a":
					problemTypesList.add(ProblemType.Addition);
					break;
				case "s":
					problemTypesList.add(ProblemType.Subtraction);
					break;
				case "m":
					problemTypesList.add(ProblemType.Multiplication);
					break;
				case "d":
					problemTypesList.add(ProblemType.Division);
					break;
			}
			
			System.out.print("ProblemTypes: ["+problemTypesList.get(0));
			for (int i=1; i<problemTypesList.size(); i++) {
				System.out.print(", "+problemTypesList.get(i));
			}
			System.out.println("]");
			
			System.out.println("Enter problem type (a)ddition, (s)ubtraction, (m)ultiplication, (d)ivision or (c)ontinue: ");
			problem_type_input = reader.nextLine();
		}
		
		// number of problems
		System.out.println("Enter number of problems: ");
		int numProblems = reader.nextInt();
		
		// operand range
		System.out.println("Enter minimum operand range: ");
		int minOperand =  reader.nextInt();
		System.out.println("Enter maximum operand range: ");
		int maxOperand =  reader.nextInt();
		OperandRange operandrange = new OperandRange(minOperand, maxOperand);
		
		WorksheetSettings worksheetSettings = new WorksheetSettings(problemTypesList, numProblems, operandrange);
		
		worksheet = Worksheet.generate(worksheetSettings);
	}
	
	public void displayWorksheet() {
		String user_answer = reader.nextLine();
		
		this.templates = new ArrayList<Template>();
		
		for (int i=0; i<worksheet.getNumProblems(); i++) {
			Problem this_problem = worksheet.getProblem(i);
			Template this_template = new Template(childProfile, this_problem);			
			this.templates.add(this_template);
			
			System.out.println(this_template.getComplete()+"\n");		
			user_answer = reader.nextLine();
			
			if (this_problem.checkAnswer(user_answer) == true) {
				worksheet.incrementTotalCorrect();
			}
		}
	}
	
	public void displayScore() {
		double percentage = (worksheet.getNumCorrect() * 100) / worksheet.getNumProblems();
		System.out.println("Total Correct: "+worksheet.getNumCorrect()+" out of "+worksheet.getNumProblems()
						   +" ("+(int)percentage+"%)");
	}
	
	public void exit() {
		reader.close();
	}
}
