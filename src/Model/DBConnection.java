package Model;
import java.sql.*;
import java.util.*;
public class DBConnection {
    private static Connection con;
    private static Statement stmt;
    private DBConnection(){}
    public static Connection getConnection() {
    	return con;
    }
    static {
        try {
            con = DriverManager.getConnection("jdbc:mysql://localhost:3306/supermarketmanagement","root","Prathik@008");
            stmt= con.createStatement();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    public static Statement getStatement(){ 
    	return stmt;
    }
    public static boolean validate(String user, String password) throws SQLException {
        ResultSet r=stmt.executeQuery("SELECT username,password FROM users WHERE username='"+user+"' AND password='"+password+"'");
        if(r.next()){
            if (r.getString(1).equals(user) && r.getString(2).equals(password))
                return true;
            else return false;
        }
        return false;
    }
	public static boolean signup(String a1, String a2, long n) throws SQLException {
		if(stmt.execute("insert into users (username,password,mobilenumber) values ('"+a1+"','"+a2+"','"+n+"')"))
			return true;
		return false;
	}
	public static ResultSet getAccess(String userName) throws SQLException {
        ResultSet r=stmt.executeQuery("SELECT role FROM users WHERE username='"+userName+"'");
		return r;
	}
}
