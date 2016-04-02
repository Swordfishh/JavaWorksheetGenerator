import java.util.ArrayList;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;

public class LineGraph extends Application {
	public LineGraph () {
	}
	
    public void start(Stage stage) {
    	final CategoryAxis xAxis = new CategoryAxis();
        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Date");
        yAxis.setLabel("Score");
              
        final LineChart<String,Number> lineChart = 
                new LineChart<String,Number>(xAxis,yAxis);
        
        lineChart.setTitle("Student Scores Over Time");
        
        // defining a series
        XYChart.Series<String, Number> series = new XYChart.Series<String, Number>();
        series.setName("Student");
        
        // populating the series with data
        for (ArrayList<String> line_item : WPGMain.graph_data) {
        	series.getData().add(new XYChart.Data<String, Number>(
        			line_item.get(1), 
        			Integer.parseInt(line_item.get(0))));
        }

        Scene scene  = new Scene(lineChart,800,600);
        lineChart.getData().add(series);
       
        stage.setScene(scene);
        stage.show();
    }
    
    public void draw() {
    	launch();
    }
}

