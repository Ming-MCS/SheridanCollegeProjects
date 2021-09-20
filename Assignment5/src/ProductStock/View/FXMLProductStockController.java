/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ProductStock.View;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.collections.ObservableList;

import ProductStock.Model.Stock;
import javafx.beans.InvalidationListener;
import javafx.beans.Observable;

/**
 * FXML Controller class
 *
 * @author Chris Yeung
 * @author Ming
 * @since 4/5/21
 */
public class FXMLProductStockController implements Initializable {

	@FXML
	private ListView<Stock> lstStock;
	@FXML
	private Label lblSelected;
	@FXML
	private TextField txtProductID;
	@FXML
	private TextField txtProductName;
	@FXML
	private TextField txtQuantity;
	@FXML
	private TextField txtRestockThresh;
	@FXML
	private TextField txtSalePrice;
	@FXML
	private TextField txtRestockAmount;
	@FXML
	private TextField txtBuyPrice;
	@FXML
	private Label lblRestockFee;
	
	private ObservableList<Stock> obList = FXCollections.observableArrayList();

	/**
	 * Initializes the controller class.
	 * @param url
	 * @param rb
	 */
	@Override
	public void initialize(URL url, ResourceBundle rb) {
		//test objects
		obList.add(new Stock("ABC_123", "Cheese", 42, 50, 23.47, 5.63));
		obList.add(new Stock("DEF_456", "Red Tea", 7, 15, 43.97, 15.23));
		lstStock.setItems(obList);
		
		//Select first by default
		lstStock.getSelectionModel().select(0);
		
		//If different item in list is selected
		lstStock.getSelectionModel().selectedItemProperty().addListener(new InvalidationListener() {
			@Override
			public void invalidated(Observable o) {
				Stock selected = lstStock.getSelectionModel().getSelectedItem();
				lblSelected.setText(selected.toString());
			}
		});
		
		
	}	

	@FXML
	private void AddItem(ActionEvent event) {
		boolean valid = true;
		
		//Check for null values
		if(txtProductName.getText().equals(""))
			valid = false;
		
		if(txtProductID.getText().equals(""))
			valid = false;
		
		if(txtQuantity.getText().equals(""))
			valid = false;
		
		if(txtRestockThresh.getText().equals(""))
			valid = false;
		
		if(txtSalePrice.getText().equals(""))
			valid = false;
		
		if(txtBuyPrice.getText().equals(""))
			valid = false;
		
		
		//bootleg exception handling
		if(valid){
			try{
				//Create new Stock object
				obList.add(new Stock(txtProductID.getText(), 
						txtProductName.getText(), 
						Integer.parseInt(txtQuantity.getText()), 
						Integer.parseInt(txtRestockThresh.getText()), 
						Double.parseDouble(txtSalePrice.getText()), 
						Double.parseDouble(txtBuyPrice.getText())
				));
			
				//Select new item
				lstStock.getSelectionModel().select(obList.size() - 1);
				
			}catch (Exception e){
				lblSelected.setText("Invalid Values");
				e.printStackTrace();
			}
		}
		else
			lblSelected.setText("Invalid Values");
		
	}

	@FXML
	private void BuyItem(ActionEvent event) {
		//Cache selected item
		Stock selected = lstStock.getSelectionModel().getSelectedItem();
		
		//Calculate restock price and output
		if(!lstStock.getSelectionModel().isEmpty()){
			String output = String.format("%s x $%.2f = $%.2f", txtRestockAmount.getText(), selected.getBuyPrice(),
					(Integer.parseInt(txtRestockAmount.getText()) * selected.getBuyPrice()));
			
			lblRestockFee.setText(output);
		}
		//If nothing is selected
		else{
			lblSelected.setText("No item currently selected");
		}
	}
	
}
