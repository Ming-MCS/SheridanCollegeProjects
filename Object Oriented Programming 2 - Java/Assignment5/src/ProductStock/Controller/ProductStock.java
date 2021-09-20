package ProductStock.Controller;

import javafx.application.Application;
import static javafx.application.Application.launch;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

/**
 * Runs the UI
 * @author Chris Yeung
 * @author Ming
 * @since 4/5/21
 */
public class ProductStock extends Application{

	/**
	 * Run program
	 * @param args
	 */
	public static void main(String[] args) {
        launch(args);
    }
 
	/**
	 * Sets up interface elements and displays them
	 * @param stage
	 * @throws Exception 
	 */
    public void start(Stage stage) throws Exception {
        Parent root = FXMLLoader.load(getClass().getResource("../View/FXMLProductStock.fxml"));
        stage.setTitle("Product Stock - Chris Yeung - Assignment 5");
        stage.setScene(new Scene(root));
        stage.show();
		
    }
	
}
