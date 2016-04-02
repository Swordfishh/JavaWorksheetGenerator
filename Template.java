package assignment2;

import java.util.ArrayList;
import java.util.Random;

public class Template {
	private String template;
	private Profile profile;
	private Problem problem;
	
	public Template(Profile profile, Problem problem) {
		this.profile = profile;
		this.problem = problem;
	}
	
	public String getComplete() {
		ArrayList<String> templates = new ArrayList<String>();
		
		// get random name
		Random randomInt = new Random();
		int index1 = randomInt.nextInt(profile.getPeople().size());
		int index2 = randomInt.nextInt(profile.getPeople().size());
		
		while (index1 == index2 && profile.getPeople().size() != 1) {
			index2 = randomInt.nextInt(profile.getPeople().size());
		}
		
		int randomItemIndex = randomInt.nextInt(profile.getItem().size());
		
		String name1 = profile.getPeople().get(index1).getName();
		String name2 = profile.getPeople().get(index2).getName();
		
		String pronoun1 = profile.getPeople().get(index1).getPronoun();
		
		String item = profile.getItem().get(randomItemIndex).getName();
		
		
		// double to int for display purposes
		int number1 = problem.getOperands().get(0).intValue();
		int number2 = problem.getOperands().get(1).intValue();
		
		
		switch (problem.getProblemType()) {
			case Addition:
				String add_template = name1+" has "+number1+" "+item+". "+name2+
				" has "+number2+" "+item+". How many "+item+" do they have altogether?";
				templates.add(add_template);
				break;
			case Subtraction:
				String sub_template = name1+" has "+number1+" "+item+". "+pronoun1+" gives "+number2+" "
				+item+" to "+name2+". How many "+item+" does "+name1+" have left?";
				templates.add(sub_template);
				break;
			case Multiplication:
				String multi_template = name1+" has "+number1+" groups of "+number2+" "+item+". How many "
				+item+" does "+name1+" have altogether?";
				templates.add(multi_template);
				break;
			case Division:
				String div_template = name1+" has "+number1+" "+item+". "+pronoun1+" wants to divide them evenly among "
				+number2+" friends. How many "+item+" does each friend get?";
				templates.add(div_template);
				break;
				
		}
		
		this.template = templates.get(0);
		return template;
		
	}
}
