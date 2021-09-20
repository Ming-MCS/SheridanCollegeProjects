package ProductStock.Model;

import java.util.Scanner;

/**
 * Contains attributes and functions for stock management
 * @author Chris Yeung
 * @version 1.0
 * @since 2021/01/26
 */
public class Stock {
    
    private String productID;
    private String productName;
    private int qoh;
    private int rsp;
    private double sellPrice;
    private double buyPrice;
    
    
    /**
     * Default Constructor
     */
    public Stock(){
        productID = "AAAA_000";
        productName = "Unknown";
        qoh = 0;
        rsp = 25;
        sellPrice = 0;
        buyPrice = 0;
    }
    
    /**
     * Constructor that specifies Product ID, Product Name, and Retail Price
     * @param productID Unique ID for product
     * @param productName Name of product
     * @param sellPrice Retail price
     */
    public Stock(String productID, String productName, double sellPrice){
        this.productID = productID;
        this.productName = productName;
        this.qoh = 0;
        this.rsp = 25;
        this.sellPrice = 0f;
        this.buyPrice = 0f;
        
    }
    
    /**
     * Constructor that specifies Product ID, Product Name, Quantity on Hand, Restock Threshold, Retail Price, and Restock Price
     * @param productID Unique ID for product
     * @param productName Name of product
     * @param qoh Quantity of the product on hand
     * @param rsp Threshold for product restock
     * @param sellPrice Retail price
     * @param buyPrice Purchase price for restock
     */
    public Stock(String productID, String productName, int qoh, int rsp, double sellPrice, double buyPrice){
        setProductID(productID);
		setProductName(productName);
		setQoh(qoh);
        setRsp(rsp);
        setSellPrice(sellPrice);
        setBuyPrice(buyPrice);
        
    }

    /**
     *
     * @return Product ID of the current product
     */
    public String getProductID() { 
        return productID;
    }

    /**
     *
     * @param productID Input of the product ID
     * @throws IllegalArgumentException Throws if input ID does not consist of exactly 7 characters
     */
    public void setProductID(String productID) throws IllegalArgumentException {
        if(productID.length() != 7)
            throw new IllegalArgumentException("ID must contain 7 characters");
        
        this.productID = productID;
    }

    /**
     *
     * @return Product name of the current product
     */
    public String getProductName() {
        return productName;
    }

    /**
     *
     * @param productName Input of the product name
     */
    public void setProductName(String productName) {
        this.productName = productName;
    }

    /**
     *
     * @return Quantity on Hand of the current product
     */
    public int getQoh() {
        return qoh;
    }

    /**
     *
     * @param qoh Input of the Quantity on Hand of the current product
     * @throws IllegalArgumentException Throws if the input quantity is negative
     */
    public void setQoh(int qoh) throws IllegalArgumentException {
        if(qoh < 0)
            throw new IllegalArgumentException("Quantity cannnot be negative");
        
        this.qoh = qoh;
    }

    /**
     *
     * @return Restocking threshold of the current product
     */
    public int getRsp() {
        return rsp;
    }

    /**
     *
     * @param rsp Input of the restocking threshold of the current product
     * @throws IllegalArgumentException Throws if the input threshold is negative
     */
    public void setRsp(int rsp) throws IllegalArgumentException {
        if(rsp < 0)
            throw new IllegalArgumentException("Restock point cannot be negative");
        
        this.rsp = rsp;
    }

    /**
     *
     * @return Retail price of the current product
     */
    public double getSellPrice() {
        return sellPrice;
    }

    /**
     *
     * @param sellPrice Input of the retail price of the current product
     * @throws IllegalArgumentException Throws if the input price is negative
     */
    public void setSellPrice(double sellPrice) throws IllegalArgumentException {
        if(sellPrice < 0)
            throw new IllegalArgumentException("Sell price cannot be negative");
        
        this.sellPrice = sellPrice;
    }

    /**
     *
     * @return Restocking price of the current product
     */
    public double getBuyPrice() {
        return buyPrice;
    }

    /**
     *
     * @param buyPrice Input of the restocking price of the current product
     * @throws IllegalArgumentException Throws if the input price is negative or less than retail price
     */
    public void setBuyPrice(double buyPrice) throws IllegalArgumentException {
        if(buyPrice < 0)
            throw new IllegalArgumentException("Buy price cannot be negative");
        else if(buyPrice > this.sellPrice)
            throw new IllegalArgumentException("Buy price cannot be more than sell price");
        
        this.buyPrice = buyPrice;
    }
    
    /**
     * Calculates the total cost of restocking a specified quantity of the product
     * @param quantity Input of the quantity of the current product to be restocked
     * @return Total price of the amount of items to be restocked based off of buyPrice
     */
    public double reStockFee(int quantity){        
        return (this.buyPrice * quantity);
    }
    
    /**
     *
     * @return Formatted information about the current product
     */
    public String toString(){
        String output = String.format("%s (%s), QOH: %d, Buying Price: $%.2f\n", productName, productID, qoh, buyPrice);
        
		//Disabled for A5
        //Display restock message if quantity on hand is less than the restock point
//        if(this.qoh < this.rsp)
//            output += String.format("You should order at least %d units\n", ((rsp-qoh) + 1));        
        
        return output;
    }
    
}
