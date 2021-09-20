package CourseList.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import CourseList.Model.Course;
import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.IOException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import java.util.Scanner;
import java.util.ArrayList;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;

/**
 * FXML Controller class
 *
 * @author Chris Yeung
 * @author Ming
 */
public class FXMLCourseListController implements Initializable {

	@FXML
	private ComboBox<Course> lstCourse;
	@FXML
	private TextField txtCode;
	@FXML
	private TextField txtGrade;
	@FXML
	private TextField txtMaxGrade;
	@FXML
	private Label lblErrMsg;
	
	private ArrayList<Course> courseList;
	private Scanner input;
	private PrintWriter pw;
	private ObservableList obsCourses;
	private File courseFile;
	
	/**
	 * Initializes the controller class.
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		courseFile = null;
		courseList = new ArrayList<Course>();
		
		//Load file on startup, setup list
		try{
			courseFile = new File("./src/Resources/CourseList.txt");
			input = new Scanner(courseFile);
			setupList();
		}catch(FileNotFoundException e){
			System.out.println("File not found");
			lblErrMsg.setText("File not found");
			e.printStackTrace();
		}
		
		obsCourses = FXCollections.observableArrayList(courseList);
		lstCourse.setItems(obsCourses);
		lstCourse.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener(){
			public void invalidated (Observable o){
				//Make sure selected index is valid before attempting to assign fields
				if(lstCourse.getSelectionModel().getSelectedIndex() >= 0){
					Course selected = lstCourse.getSelectionModel().getSelectedItem();
					
					//Assign course values to fields
					txtCode.setText(selected.getCourseCode());
					txtGrade.setText(String.format("%.2f", selected.getGrade()));
					txtMaxGrade.setText(String.format("%.2f", selected.getMaxGrade()));
				}
			}
		});
		
		
	}

	@FXML
	private void edit(ActionEvent event) {
		
		//Update Course object with input values
		try{
			//Cache current index for list refresh later
			int index = lstCourse.getSelectionModel().getSelectedIndex();
			//Fetch input values
			String code = txtCode.getText();
			double grade = Double.parseDouble(txtGrade.getText());
			double maxGrade = Double.parseDouble(txtMaxGrade.getText());
			
			//Set values of backend list
			courseList.get(index).setCourseCode(code);
			courseList.get(index).setGrade(grade);
			courseList.get(index).setMaxGrade(maxGrade);
			
			//Refresh list
			lstCourse.getSelectionModel().clearSelection();
			lstCourse.getSelectionModel().select(index);
			lblErrMsg.setText("");
			
		}
		//Invalid grade inputs
		catch(NumberFormatException ex){
			System.out.println("Invalid grade values");
			lblErrMsg.setText("Invalid Grade Values");
		}
		//Invalid course code input
		catch(IllegalArgumentException ex){
			System.out.println("Wrong course code format");
			lblErrMsg.setText("Invalid Course Code");
		}
		
		
	}

	@FXML
	private void exit(ActionEvent event) {
		//Initialize printwriter
		//Will wipe entire file, since we won't be appending but rather replacing
		try{
			pw = new PrintWriter(new BufferedWriter(new FileWriter(courseFile)));
		}catch(IOException ex){
			System.out.println("No file to write to");
			ex.printStackTrace();
		}
		
		//Write courses to file
		//Bad for longer lists but it works for this
		for(Course c: courseList){
			pw.printf("%s,%.2f,%.2f\n", c.getCourseCode(),c.getGrade(),c.getMaxGrade());
		}
		//save
		pw.close();
		//shutdown
		System.exit(0);
	}
	
	private void setupList(){
		//Holds each record in its entirety
		ArrayList<String> records = new ArrayList<String>();
		while(input.hasNextLine()){
			records.add(input.nextLine());
		}

		//Iterate through each record
		for(String s:records){
			input = new Scanner(s);
			input.useDelimiter(",");

			//Parse record
			//Assign values to each field
			//Will assign invalid values if record fields are invalid to throw an error
			String code = input.hasNext() ? input.next():null;
			double grade = input.hasNextDouble() ? input.nextDouble():-1;
			double maxGrade = input.hasNextDouble() ? input.nextDouble():-1;

			//Don't add record to list if invalid
			try{
				//Create Course and add to list
				Course output = new Course(code, grade, maxGrade);
				courseList.add(output);
			}catch(IllegalArgumentException ex){
				System.out.println("Record is invalid");
			}
		}
		
	}
}
