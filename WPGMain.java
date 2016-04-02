

import java.nio.file.Paths;
import java.util.ArrayList;

public class WPGMain {
	public static ArrayList<ArrayList<String>> graph_data = new ArrayList<ArrayList<String>>();
	
	public static void main(String[] args) {
		if (args.length > 0) {
			_display_graph(args[0]);
		}
		else {
			WordProblemGenerator WPG = new CommandLineWPG();
				
			WPG.promptUserProfileInfo();
			WPG.promptWorksheetSettings();
			WPG.displayWorksheet();
			
			WPG.displayScore();
			WPG.saveScore();
			WPG.exit();
			
			_display_graph(WPG.SCOREFILEPATH);
		}
	}
	
	public static void _display_graph(String filePath) {
		 try {
	    	ParseGraphData graphDataObject = new ParseGraphData(Paths.get(filePath));
	    	graph_data = graphDataObject.getGraphData();
			LineGraph line_graph = new LineGraph();
		    line_graph.draw();
	    } 
	    catch(Exception e) {
	    	System.out.println("Exception");
	    }
	}

}
