package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Controller.Products;

public class ProductDB {
	Statement stmt= DBConnection.getStatement();
	public void addItem(Products p1) throws SQLException {
		stmt.execute("insert into products (pname,stocks_available,item_price,item_type) values('"+p1.itemName+"','"+p1.stocksAvailable+"','"+p1.itemPrice+"','"+p1.itemType+"')");
		System.out.println("Inserted..");
		System.out.println();
	}
	public void removeItem(int pid) throws SQLException {
		stmt.execute("delete from products where pid = '"+pid+"'");
		System.out.println("Item Removed SuccessFully");
	}
	public ResultSet getProducts() throws SQLException {
		ResultSet r=stmt.executeQuery("select * from products");
		return r;
	}

}
