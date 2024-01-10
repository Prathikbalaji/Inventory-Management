package Controller;

public class Products {
	
	public String itemName;
	public int stocksAvailable;
	public double itemPrice;
	public String itemType;
	

	public Products(String a, int b, double c, String d) {
		this.itemName=a;
		this.stocksAvailable=b;
		this.itemPrice=c;
		this.itemType=d;
	}
	
	public void setIName(String iName) {
		this.itemName=iName;
	}
	public void stockAvailable(int stocks) {
		this.stocksAvailable=stocks;
	}
	public void setPrice(double price) {
		this.itemPrice=price;
	}
	public void setIType(String type) {
		this.itemType=type;
	}
	public String getIName(){
		return itemName;
	}
	public int getStocks() {
		return stocksAvailable;
	}
	public double getPrice() {
		return itemPrice;
	}
	public String itemType() {
		return itemType();
	}
}
