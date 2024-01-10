package View;

import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

import com.mysql.cj.x.protobuf.MysqlxCrud.Order;

import Controller.Admin;
import Controller.Products;
import Model.DBConnection;

public class view {
	static Statement stmt= DBConnection.getStatement();
	static Scanner in=new Scanner(System.in);
	public static Products addItems() {
		System.out.println("Enter Item Name : ");
		String a=in.nextLine();
		System.out.println("Stocks Available : ");
		int b=in.nextInt();
		System.out.println("Item Price : ");
		double c=in.nextDouble();
		in.nextLine();
		System.out.println("Item Type : ");
		String d=in.nextLine();
		return new Products(a,b,c,d);
	}
	public static int getItemId() {
		System.out.println("Enter Product Id to br Removed   : ");
		return in.nextInt();
	}
	public static void printItems(ResultSet r) throws SQLException {
		ResultSetMetaData rmd=r.getMetaData();
        int col=rmd.getColumnCount();
        System.out.println("             -----------------------------------------------------------------------------");
        for (int i = 1; i <= col; i++) {
            System.out.printf("%17s|",rmd.getColumnName(i).toUpperCase()+" ");
        }
        System.out.println();
        System.out.println("             -----------------------------------------------------------------------------");
        while(r.next()){
            for (int i = 1; i <= col; i++) {
                System.out.printf("%17s|",r.getString(i)+" ");
            }
            System.out.println();
        }
        System.out.println("             -----------------------------------------------------------------------------");
        System.out.println();
		
	}
	public static void printUsers(ResultSet r) throws SQLException {
		ResultSetMetaData rmd=r.getMetaData();
        int col=rmd.getColumnCount();
        System.out.println("       -------------------------------------------------------------------------");
        for (int i = 1; i <= col; i++) {
            System.out.printf("%15s|",rmd.getColumnName(i).toUpperCase()+" ");
        }
        System.out.println();
        System.out.println("       -------------------------------------------------------------------------");
        while(r.next()){
            for (int i = 1; i <= col; i++) {
                System.out.printf("%15s|",r.getString(i)+" ");
            }
            System.out.println();
        }
        System.out.println("       -------------------------------------------------------------------------");
        System.out.println();
		
	}
	public static Admin addAdmin() {
		System.out.println("Enter Admin Name : ");
		String a=in.nextLine();
		System.out.println("Enter Admin Password : ");
		String b=in.nextLine();
		System.out.println("Enter Role : ");
		String c=in.nextLine();
		return new Admin(a,b,c);
	}
	static Map<Integer,Integer> emp=new HashMap<>();
	public static ArrayList<ArrayList<String>> buyItems() throws SQLException {
		ArrayList<ArrayList<String>> li=new ArrayList<>();
		System.out.println("Enter Your registered user Id : ");
		int user_id=in.nextInt();
		System.out.println("Enter No of items you want to buy : ");
		int no=in.nextInt();
		
		for(int i=0;i<no;i++)
			li.add(new ArrayList<>());
		int sum=0;
		for(int i=0;i<no;i++) {
			System.out.println("Enter Product("+(i+1)+") id : ");
			int prdId=in.nextInt();
			System.out.println("Enter the Quantity : ");
			int qnty=in.nextInt();
			ResultSet r=stmt.executeQuery("select * from products where pid='"+prdId+"'");
			String a1="";
			int a2=0;
			if(r.next()) {
				a1=r.getString(2);
				a2=r.getInt(4);
			}
			li.get(i).add(user_id+"");
			li.get(i).add(a1);
			li.get(i).add((a2*qnty)+" ");
			sum+=qnty;
			emp.put(prdId,sum);
		}
		sum=0;
		System.out.println();
		return li;
	}
	public static int getUid() {
		System.out.println("Enter you user_id to view Ordered items : ");
		return in.nextInt();
	}
	public static void printOrders(int uid) throws SQLException {
		ResultSet r=stmt.executeQuery("select product_name from orders where status='not paid'");
		ResultSetMetaData rmd=r.getMetaData();
        int col=rmd.getColumnCount();
		while(r.next()){
            for (int i = 1; i <= col; i++) {
                System.out.print("             "+r.getString(i)+" ");
            }
            System.out.println();
        }
	}
	public static void strtPayment() throws SQLException {
		System.out.println("Enter user_id : ");
		int uid=in.nextInt();
		ResultSet r=stmt.executeQuery("select user_id,sum(Tot_Amt) AS 'Amount To Be Paid' from orders where status='not paid' and user_id='"+uid+"' group by user_id");
		ResultSetMetaData rmd=r.getMetaData();
        int col=rmd.getColumnCount();
        System.out.println("             ------------------------------");
        for (int i = 1; i <= col; i++) {
            System.out.printf("%20s|",rmd.getColumnName(i).toUpperCase()+" ");
        }
        System.out.println();
        System.out.println("            ------------------------------");
        while(r.next()){
            for (int i = 1; i <= col; i++) {
                System.out.printf("%20s|",r.getString(i)+" ");
            }
            System.out.println();
        }
        System.out.println("            -------------------------------");
        System.out.println();
		System.out.println("Enter Amt to Pay : ");
		double pay=in.nextDouble();
		ResultSet r1=stmt.executeQuery("select sum(Tot_Amt) from orders where user_id='"+uid+"' and status='not paid' group by user_id");
		String rs="";
		while(r1.next()){
		rs=r1.getString(1);
		}
		if(pay == Double.parseDouble(rs)) {
			System.out.println("Payment Successfull");
			stmt.executeUpdate("update orders set status = 'paid' where user_id = '"+uid+"'");
			System.out.println("Order finished..!");
		}
		else {
			strtPayment();
		}

	}
	public static void getUsers(ResultSet r) throws SQLException {
		ResultSetMetaData rmd=r.getMetaData();
        int col=rmd.getColumnCount();
        System.out.println("       -------------------------");
        for (int i = 1; i <= col; i++) {
            System.out.printf("%15s|",rmd.getColumnName(i).toUpperCase()+" ");
        }
        System.out.println();
        System.out.println("       -------------------------");
        while(r.next()){
            for (int i = 1; i <= col; i++) {
                System.out.printf("%15s|",r.getString(i)+" ");
            }
            System.out.println();
        }
        System.out.println("       --------------------------");
        System.out.println();
		
	}
	public static void showBill() throws SQLException {
		System.out.println();
		System.out.println("             Bill          ");
		System.out.println();
		for(var v:emp.entrySet()) {
			ResultSet r=stmt.executeQuery("select pname from products where pid='"+v.getKey()+"'");	
			String rs="";
			while(r.next()){
			rs=r.getString(1);
			System.out.printf("%15s",rs);
			System.out.println("       "+v.getValue());
			System.out.println();
			}
		}
	}

}
