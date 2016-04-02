

import java.util.ArrayList;
import java.util.Random;

public class Template {
	private String template;
	private Profile profile;
	private Problem problem;
	private ArrayList<Integer> uniqueRandomNameIndex = new ArrayList<Integer>();
	
	public Template(Profile profile, Problem problem) {
		this.profile = profile;
		this.problem = problem;
		this._randomNameIndexes();
	}
	
	private void _randomNameIndexes() {
		Random randomInt = new Random();
		int index1 = randomInt.nextInt(profile.getPeople().size());
		int index2 = randomInt.nextInt(profile.getPeople().size());
		
		while (index1 == index2 && profile.getPeople().size() != 1) {
			index2 = randomInt.nextInt(profile.getPeople().size());
		}
		uniqueRandomNameIndex.add(index1);
		uniqueRandomNameIndex.add(index2);
		
	}
	
	private int _randomItemIndex() {
		Random randomInt = new Random();
		return randomInt.nextInt(profile.getItem().size());
	}
	
	private String name1() {
		return profile.getPeople().get(this.uniqueRandomNameIndex.get(0)).getName();
	}
	
	private String name2() {
		return profile.getPeople().get(this.uniqueRandomNameIndex.get(1)).getName();
	}
	
	private String pronoun1() {
		return profile.getPeople().get(this.uniqueRandomNameIndex.get(0)).getPronoun();
	}
	
	private String item1() {
		return profile.getItem().get(this._randomItemIndex()).getName();
	}
	
	private int operand1() {
		return problem.getOperands().get(0).intValue();
	}
	
	private int operand2() {
		return problem.getOperands().get(1).intValue();
	}
	
	public String getComplete() {
		ArrayList<String> templates = new ArrayList<String>();
		
		switch (problem.getProblemType()) {
			case Addition:
				String add_template = name1()+" has "+operand1()+" "+item1()+". "+name2()+
				" has "+operand2()+" "+item1()+". How many "+item1()+" do they have altogether?";
				templates.add(add_template);
				break;
			case Subtraction:
				String sub_template = name1()+" has "+operand1()+" "+item1()+". "+pronoun1()+" gives "+operand2()+" "
				+item1()+" to "+name2()+". How many "+item1()+" does "+name1()+" have left?";
				templates.add(sub_template);
				break;
			case Multiplication:
				String multi_template = name1()+" has "+operand1()+" groups of "+operand2()+" "+item1()+". How many "
				+item1()+" does "+name1()+" have altogether?";
				templates.add(multi_template);
				break;
			case Division:
				String div_template = name1()+" has "+operand1()+" "+item1()+". "+pronoun1()+" wants to divide them evenly among "
				+operand2()+" friends. How many "+item1()+" does each friend get?";
				templates.add(div_template);
				break;
				
		}
		
		this.template = templates.get(0);
		return template;
		
	}
}
