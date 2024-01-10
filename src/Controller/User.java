package Controller;

import java.sql.SQLException;
import java.util.Scanner;

import Model.OrderDB;
import Model.ProductDB;
import View.view;


public class User {
	ProductDB a;
	OrderDB b;
	public User() {
		b=new OrderDB();
		a=new ProductDB();
	}
	Scanner in=new Scanner(System.in);
	public void controller() throws SQLException {
		System.out.println();
		User user=new User();
		System.out.println("User's Panel");
		System.out.println("\n1.View Items Available\n2.Add Items to Cart\n3.View My Orders\n4.View My Bill\n5.Exit");
		System.out.println();
		System.out.print("Enter your Choise : ");
		int ch=in.nextInt();
		System.out.println();
		switch(ch) {
		case 1:
			user.displayItem();
			controller();
			break;
		case 2:
			user.showUsers();
			user.displayItem();
			user.buyItem();
			System.out.println("1-Continue buying\n2-Move to Payment");
			int a=in.nextInt();
			if(a==1)
				controller();
			else if(a==2) {
				user.startPayment();
				user.viewBill();
				System.exit(0);
			}
			break;
		case 3:
			user.viewOrders();
			controller();			
			break;
		case 4:
			user.viewBill();
			controller();
			break;
		case 5:
			System.out.println("Exit.....!");
			System.exit(0);
		default:
			System.out.println("Enter Valid Choise");
		}	
		
	}
	private void viewBill() throws SQLException {
		view.showBill();
	}
	private void showUsers() throws SQLException {
		view.getUsers(b.getUser());
	}
	private void startPayment() throws SQLException {
		view.strtPayment();
	}
	private void viewOrders() throws SQLException {
		int uid=view.getUid();
		view.printOrders(uid);
	}
	private void buyItem() throws SQLException {
		b.addOrders(view.buyItems());
	}
	private void displayItem() throws SQLException {
		view.printItems(a.getProducts());
	}

}
