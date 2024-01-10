package Model;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.mysql.cj.x.protobuf.MysqlxSession.Reset;

public class OrderDB {
	
	Statement stmt= DBConnection.getStatement();

	public void addOrders(ArrayList<ArrayList<String>> a) throws SQLException {
		for(int i=0;i<a.size();i++) {
			int a1=Integer.parseInt(a.get(i).get(0));
			String a2=a.get(i).get(1);
			double a3=Double.parseDouble(a.get(i).get(2));
			stmt.execute("insert into orders(user_id,product_name,Tot_Amt) values ('"+a1+"','"+a2+"','"+a3+"')");
		}
		System.out.println("Orders Added!!");
		System.out.println();
	}

	public ResultSet getUser() throws SQLException {
		ResultSet r=stmt.executeQuery("select user_id,username from users where role='user'");
		return r;
	}
}
