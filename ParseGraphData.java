

import java.io.IOException;
import java.nio.file.Path;
import java.util.ArrayList;
import java.util.Scanner;

public class ParseGraphData {
	
	private Path filePath;
	private ArrayList<ArrayList<String>> graph_data;
	
	public ParseGraphData(Path filePath) {
		this.filePath = filePath;
		this._parseGraphData();  	
	}
	
	private void _parseGraphData() {
		Scanner scanner;
		try {
			scanner = new Scanner(this.filePath);
			graph_data = new ArrayList<ArrayList<String>>();
			ArrayList<String> single_graph_data;
			 while(scanner.hasNextLine()) {
				 single_graph_data = new ArrayList<String>();
				 String[] tokens = scanner.nextLine().split("[,]");
				 for (String t: tokens) {
					 single_graph_data.add(t.trim());
				 }
				 graph_data.add(single_graph_data);
			 }
		     
	    	scanner.close();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<ArrayList<String>> getGraphData() {
		return graph_data;
	}
}
