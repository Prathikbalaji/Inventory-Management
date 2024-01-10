package Controller;

import java.sql.SQLException;
import java.util.Scanner;
import Model.AdminDB;
import Model.DBConnection;
import Model.ProductDB;
import View.view;

public class Admin {
	ProductDB item;
	AdminDB aa;
	public String aname;
	public String apass;
	public String arole;
	public Admin() {
		item=new ProductDB();
		aa=new AdminDB();
	}
	public Admin(String a,String b,String c) {
		this.aname=a;
		this.apass=b;
		this.arole=c;
	}
	Scanner in=new Scanner(System.in);
	public void controller() throws Exception {
		Admin admin=new Admin();
		System.out.println();
		System.out.println("ADMINS'S PANEL");
		System.out.println("\n1.Add Items\n2.Remove Items\n3.Display Items\n4.Add Admin\n5.Show Users\n6.Exit");
		int choise=in.nextInt();
		switch(choise) {
		case 1:
			admin.addItem();
			controller();
			break;
		case 2:
			admin.removeItem();
			controller();
			break;
		case 3:
			admin.displayItem();
			controller();
			break;
		case 4:
			admin.addAdmin();
			controller();
		case 5:
			admin.showUsers();
			controller();
			break;
		case 6:
			System.out.println("Done...");
			System.exit(0);
			break;
		default:
			System.out.println("Enter valid choise : ");
			controller();
		}
	}
	private void showUsers() throws SQLException {
		view.printUsers(aa.getSignup());
	}
	private void addAdmin() throws SQLException {
		Admin a1=view.addAdmin();
		aa.addAdmin(a1);
		
	}
	private void displayItem() throws SQLException {
		view.printItems(item.getProducts());
	}
	private void addItem() throws Exception{
		Products p1=view.addItems();
		item.addItem(p1);
	}
	private void removeItem() throws SQLException {
        view.printItems(item.getProducts());
        int pid=view.getItemId();
        item.removeItem(pid);
    }

}
