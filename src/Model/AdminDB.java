package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import Controller.Admin;
import Controller.Products;

public class AdminDB {

	Statement stmt= DBConnection.getStatement();

	public void addAdmin(Admin a1) throws SQLException {
		stmt.execute("insert into signup(username,password,role) values ('"+a1.aname+"','"+a1.apass+"','"+a1.arole+"')");
		System.out.println("Added....!");
	}

	public ResultSet getSignup() throws SQLException {
		ResultSet r=stmt.executeQuery("select * from users");
		return r;
	}

}
