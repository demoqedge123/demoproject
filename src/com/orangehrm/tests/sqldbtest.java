package com.orangehrm.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class sqldbtest {

	/**
	 * @param args
	 * @throws ClassNotFoundException 
	 * @throws SQLException 
	 */
	public static void main(String[] args) throws ClassNotFoundException, SQLException
	{
	
		Connection dbcon;
		Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
		dbcon=DriverManager.getConnection("jdbc:sqlserver://192.168.1.100:1433;database=bankdb;", "test","test");
		Statement stmt=dbcon.createStatement();
		ResultSet rs=stmt.executeQuery("Select * from bankbranches");
		while (rs.next()) 
		{
			System.out.println(rs.getString("branchid"));
		}
		

	}

}
