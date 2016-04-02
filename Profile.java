package assignment2;

import java.util.List;

public class Profile {
//	private Person child; // unnecessary
	private List<Person> importantPeople;
	private List<Item> importantItems;
	
	public Profile(List<Person> people, List<Item> items) {
		this.importantPeople = people;
		this.importantItems = items;
	}
	
	public List<Person> getPeople() {
		return this.importantPeople;
	}
	
	public List<Item> getItem() {
		return this.importantItems;
	}
	
}
