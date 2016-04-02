package assignment2;

public class WPGMain {

	public static void main(String[] args) {
		WordProblemGenerator WPG = new CommandLineWPG();
			
		WPG.promptUserProfileInfo();
		WPG.promptWorksheetSettings();
		WPG.displayWorksheet();
		
		WPG.displayScore();
		
		WPG.exit();
		
		
	}

}
