package com.training.dao.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionProvider {
	
	 private static Connection con=null;
	   public static Connection getMySqlConnection()
	        { 
		          final String URL ="jdbc:mysql://localhost:3306/";
		          final String DB ="mydbducat?createDatabaseIfNotExist=true";
		          final String USERNAME ="root";
		          final String PASSWORD ="1234";
		          try 
		          {
					Class.forName("com.mysql.cj.jdbc.Driver");
				    con = DriverManager.getConnection(URL+DB,USERNAME,PASSWORD);
				    System.out.println("connected to mysql successfully! "+con);
				  } 
		          catch (ClassNotFoundException e) 
		          {
			        System.out.println("unable to load driver "+e.getLocalizedMessage());
				  }
		          catch (SQLException e)
		          {
		        	  System.out.println("sql error "+e.getLocalizedMessage());
		          }
		          return con;
	        }

}
