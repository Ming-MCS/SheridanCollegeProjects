package CourseList.Controller;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * @author Chris Yeung
 * @author Ming
 */
public class CourseList extends Application{
	public static void main(String[] args) {
		launch(args);
	}
	
	public void start(Stage stage) throws Exception{
		Parent root = FXMLLoader.load(getClass().getResource("../View/FXMLCourseList.fxml"));
		stage.setTitle("Course List - Chris Yeung - Assignment 6");
		stage.setScene(new Scene(root));
		stage.show();
	}
}
