package booK;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conn{
	Connection con;
	String JDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String connectDB="jdbc:sqlserver://localhost:1433;DatabaseName=Manage";
	String user="sa";
	String password="19961113";
	public Connection getConnection(){
		try{
			Class.forName(JDriver);

		}catch(ClassNotFoundException e){
			e.printStackTrace();
		}
		System.out.println("数据库驱动加载成功");
		try{
			
			con=DriverManager.getConnection(connectDB,user,password);
			System.out.println("数据库连接成功");

		}catch(SQLException e){
			e.printStackTrace();
		}
		
		return con;
	}
	}

	
	