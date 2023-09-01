package db;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

import javax.swing.JOptionPane;

public class tables {
	public static void main(String args[]) throws Exception
	{
//		String url = "jdbc:mysql://localhost:3306/jdbc";
//		String username = "root";
//		String pwd = "root";
		try
		{
//			Connection con = DriverManager.getConnection(url, username, pwd);
			Connection con = jdbc.getCon();
			Statement st = con.createStatement();
			//st.executeUpdate("create table pharma(name varchar(50), mobileNumber varchar(20), email varchar(50), username varchar(30),password varchar(20))");
			//st.executeUpdate("insert into pharma values ('ADMIN','1112223334','admin@gmail.com','admin','admin')");
			//st.executeUpdate("create table suppliers(name varchar(50), mobileNumber varchar(10), email varchar(50), SupplierID int PRIMARY KEY AUTO_INCREMENT, MedicineName varchar(50), MedicineID int)");
//			st.executeUpdate("create table medicine (MedicineName varchar(50), MedicineID int PRIMARY KEY, Quantity int, CostPerUnit int, SupplierName varchar(50))");
			//st.executeUpdate("create table bill (SNO int AUTO_INCREMENT primary key, billID varchar(200), totalPaid int, generated varchar(50));");
			//st.executeUpdate("create table cart (SNO int AUTO_INCREMENT primary key, medicinename varchar(200), costperunit int, quantity int, total int);");
			//st.executeUpdate("create table saleshistory (SNO int AUTO_INCREMENT primary key, billID varchar(300), medicinename varchar(200), costperunit int, quantity int, total int);");
			JOptionPane.showMessageDialog(null, "table created successfully");
			
		}
		catch(Exception e)
		{
			JOptionPane.showMessageDialog(null, e);
		}
	}
}
