package View;
import java.util.*;
import Model.DBConnection;
import Controller.*;
import java.sql.*;
public class Main {
	static Scanner in=new Scanner(System.in);

	public static void main(String[] args) {
		Main a=new Main();
		while(true) {
			System.out.println("1-signup");
			System.out.println("2-Login");
			int choice=in.nextInt();
			switch(choice) {
			case 1:
				a.signup();
				break;
			case 2:
				a.login();
				break;
			default :
				continue;
			}
		}
	}
	public void signup() {
		in.nextLine();
		System.out.print("Enter UserName : ");
		String a1=in.nextLine();
		System.out.println();
		System.out.print("Enter Password : ");
		String a2=in.nextLine();
		System.out.println();
		System.out.print("Confirm Password : ");
		String a3=in.nextLine();
		System.out.println();
		if(!a2.equals(a3)) {
			System.out.println("Password and Confirm Password must be same");
			signup();
		}
		System.out.println("Enter your Mobile Number:");
		long n=in.nextLong();
		Main signup = new Main();
		try {
			boolean b=DBConnection.signup(a1,a2,n);
			if(!b)
				login();
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
	public void login() {
		System.out.println();
		System.out.println("*****LOGIN*****");
		System.out.print("Enter user name: ");
		String userName=in.next();
		System.out.print("Password : ");
		String password=in.next();
		Main login=new Main();
		try {
			if(DBConnection.validate(userName, password)) {
				login.gainAccess(userName);
			}
			else {
				System.out.println("Enter valid user name and password.");
				login();
			}
		}catch (Exception e){
			e.printStackTrace();
		}
	}
	void gainAccess(String userName) throws Exception {
        ResultSet r=DBConnection.getAccess(userName);
        if(r.next()){
            String role=r.getString(1);
            switch (role){
                case "admin":
                	Admin a=new Admin();
                    a.controller();
                    break;
                case "user":
                	User b=new User();
                    b.controller();
                    break;
            }
        }
	}
}
