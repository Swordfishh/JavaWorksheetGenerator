

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Scanner;

public class CommandLineWPG extends WordProblemGenerator {	
	private Profile childProfile;
	private Worksheet worksheet;
	private List<Template> templates;
	
	private Scanner reader = new Scanner(System.in);
	
	private List<Person> promptForListOfPerson() {
		List<Person> peopleList = new ArrayList<Person>();
		
		List<String> genderList = new ArrayList<String>();
		genderList.add("male");
		genderList.add("female");
		
		String pronoun = "";
		for (String gender : genderList) {
			switch (gender) {
				case "male":
					pronoun = "He";
					break;
				case "female":
					pronoun = "She";
					break;
			}
			
			// Enter names
			System.out.println("Enter a "+gender+" name or (c)ontinue: ");
			String person_input = reader.nextLine();
			
			while (!person_input.equals("c")) {
				Person person = new Person(person_input, pronoun);
				peopleList.add(person);
				
				System.out.print("Names: ["+peopleList.get(0).getName());
				for (int i=1; i<peopleList.size(); i++) {
					System.out.print(", "+peopleList.get(i).getName());
				}
				System.out.println("]");
				
				System.out.println("Enter a "+gender+" name or (c)ontinue: ");
				person_input = reader.nextLine();
			}
		}
		
		return peopleList;
	}
	
	private List<Item> promptForListOfItems() {
		List<Item> itemList = new ArrayList<Item>();
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
		return itemList;
	}
	
	public void promptUserProfileInfo() {
		childProfile = new Profile(this.promptForListOfPerson(), this.promptForListOfItems());
	}
	
	private List<ProblemType> promptProblemType() {
		List<ProblemType> problemTypesList = new ArrayList<ProblemType>();
		
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
		return problemTypesList;
	}
	
	private int promptInt(String text) {
		System.out.println(text);
		return reader.nextInt();
	}
	
	private int promptNumberOfProblems() {
		return this.promptInt("Enter number of problems: ");
	}
	
	private OperandRange promptOperandRange() {		
		return new OperandRange(this.promptInt("Enter minimum operand range: "), 
								this.promptInt("Enter maximum operand range: "));
	}
	
	public void promptWorksheetSettings() {
		WorksheetSettings worksheetSettings = new WorksheetSettings(this.promptProblemType(), 
				this.promptNumberOfProblems(), 
				this.promptOperandRange());
		
		worksheet = Worksheet.generate(worksheetSettings);
	}
	
	public void displayWorksheet() {
		reader.nextLine();
		
		this.templates = new ArrayList<Template>();
		
		for (int i=0; i<worksheet.getNumProblems(); i++) {
			Problem this_problem = worksheet.getProblem(i);
			Template this_template = new Template(childProfile, this_problem);			
			this.templates.add(this_template);
			
			if (this_problem.checkAnswer(this.promptNextLine(this_template.getComplete()+"\n")) == true) {
				worksheet.incrementTotalCorrect();
			}
		}
	}
	private String promptNextLine(String text)  {
		System.out.println(text);		
		return reader.nextLine();
	}

	private int percentage() {
		return (int)(worksheet.getNumCorrect() * 100) / worksheet.getNumProblems();
	}
	
	public void displayScore() {
		System.out.println("Total Correct: "+worksheet.getNumCorrect()
						   +" out of "+worksheet.getNumProblems()
						   +" ("+this.percentage()+"%)");
	}
	
	public void saveScore() {
		String currentDate = new SimpleDateFormat("MM/dd/YYYY HH:mm:ss").format(Calendar.getInstance().getTime());
		File f = new File(SCOREFILEPATH);
		
		if(!f.exists()) {
			try {
				PrintWriter writer = new PrintWriter(SCOREFILEPATH, "UTF-8");
				writer.println(new Integer(this.percentage()).toString()+", "+currentDate);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		else {
			BufferedWriter bw = null;
			try {
			    bw = new BufferedWriter(new FileWriter(SCOREFILEPATH, true));
			    bw.write(new Integer(this.percentage()).toString()+", "+currentDate);
			    bw.newLine();
			    bw.flush();
			    bw.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
	
	public void exit() {
		reader.close();
	}
}
