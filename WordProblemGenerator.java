

public abstract class WordProblemGenerator {
	public final String SCOREFILEPATH = System.getProperty("user.dir")+"/src/scores.txt";
	
	public abstract void promptUserProfileInfo();
	
	public abstract void promptWorksheetSettings();

	public abstract void displayWorksheet();
	
	public abstract void displayScore();

	public abstract void saveScore();

	public abstract void exit();

}
