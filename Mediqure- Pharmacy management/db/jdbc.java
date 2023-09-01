package db;

import java.sql.*;
public class jdbc {

	public static  Connection getCon() throws SQLException {
		String url = "jdbc:mysql://localhost:3306/jdbc";
		String username = "root";
		String pwd = "root";
		
//		String query = "select * from employee";
		try
		{
		Connection con = DriverManager.getConnection(url, username, pwd);//since connection is interface, drivermanager class will create object and return the object to connection
		return con;
		}
		catch(Exception e)
		{
			System.out.println(e);
			return null;
		}
		
		
		
		
		
		
//		Statement st = con.createStatement(); //to execute query
//		ResultSet rs = st.executeQuery(query); //execute and returns result set
//		
//		while(rs.next()) //to move to next line in table
//		{
//		System.out.println("ID is: "+rs.getInt(1));
//		System.out.println("name is: "+rs.getString(2));
//		System.out.println("company is: "+rs.getString(3));
//		}
//		con.close();
	}

}
