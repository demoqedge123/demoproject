package com.orangehrm.tests;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MaxEmpId 
{

	
	public static void main(String[] args) throws InstantiationException, IllegalAccessException, ClassNotFoundException, SQLException 
	{
	
	Connection dbcon;
	String maxid=null;
	Class.forName("com.mysql.jdbc.Driver").newInstance();
	dbcon=DriverManager.getConnection("jdbc:mysql://192.168.1.100:3306/orangehrm_mysql","orangehrm","");
	Statement stmt=dbcon.createStatement();
	ResultSet rs=stmt.executeQuery("Select max(emp_number) from hs_hr_employee");
	while (rs.next()) 
	{
		maxid=rs.getString(1);
	}
		
	System.out.println(maxid);
	}

}
