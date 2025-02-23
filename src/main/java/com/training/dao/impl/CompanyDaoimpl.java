package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.training.dao.util.CompanyDao;
import com.training.dao.util.ConnectionFactory;
import com.training.model.Company;
 

public class CompanyDaoimpl implements CompanyDao{

	public CompanyDaoimpl()
	{	
		createTableIfNotExists();
		
	}
	private void createTableIfNotExists() {
		try(Connection con=ConnectionFactory.getMySqlConnection(); 
				Statement st=con.createStatement()){
			
			st.execute("""
					create table IF NOT EXISTS Company(
					  branchId int primary key auto_increment,
						brancheName varchar(255),
						address varchar(255),
						ownerName varchar(255),
						ownerMobileno bigint,
						frontdeskNo bigint);

					""");
			System.out.println("Company is ready ");			
			
		}catch(SQLException e )
		{
			e.printStackTrace();
		}
		
	}
	
	
//	Here we add all the method of the interface class
	@Override
	public long addCompany(Company comp) {
		 	String sql="insert into Company (branchName,address,ownerName,ownerMobileno, frontdeskNo) values (?,?,?,?,?)";
		 	try(Connection con=ConnectionFactory.getMySqlConnection();
		 			PreparedStatement ps=con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS)	){
		 		ps.setString(1,comp.getbranchname());
		 		ps.setString(2,comp.getAddress());
		 		ps.setString(3,comp.getOwnerName());
		 		ps.setLong(4, comp.getMobileNo());
		 		ps.setLong(5,comp.getFrontdeskNo());
		 		
		 		int rowaffected=ps.executeUpdate();
		 		if(rowaffected>0) {
		 			ResultSet rs=ps.getGeneratedKeys(); 
		 			if(rs.next()) {
//		 				it will return the first generated key 
		 				return rs.getLong(1);
		 			}
		 		}
		 		
		 	}catch(SQLException e) {
		 		e.printStackTrace(); 
		 	}
		 	
//		 	when fail then return -1
		 	return -1 ; 
		 		
	}

	@Override
	public long updateCompany(Company comp) {
		 String sql ="update Company set branchName=?, address=?,ownerName=?, ownerMobileno=? , frontdeskNo=? where branchId=?";
		 try (Connection con=ConnectionFactory.getMySqlConnection();
				 PreparedStatement ps=con.prepareStatement(sql)){
			 
			 ps.setString(1,comp.getbranchname());
			 ps.setString(2, comp.getAddress());
			 ps.setString(3, comp.getOwnerName());
			 ps.setLong(4,comp.getMobileNo() );
			 ps.setLong(5, comp.getFrontdeskNo());
			 ps.setLong(6, comp.getBranchid());
			 
			 int rowaffected=ps.executeUpdate();
			 
			  if(rowaffected>0) { 
//				 research it 
				  return comp.getBranchid();
			  }
			  
			 

		 }catch(SQLException e) {
			 e.printStackTrace();
		 }
		 return -1; 
	}

	@Override
	public Company getCompanyById(long branchId) {
	 String sql="Select * from Company where branchId=?";
	 
	 try(Connection con=ConnectionFactory.getMySqlConnection();
			 PreparedStatement ps=con.prepareStatement(sql)){
		 ps.setLong(1, branchId);
		 
		 
		 
		 
		 ResultSet rs=ps.executeQuery();
		 if(rs.next()) {
//			 Here we set the value of the company with the help of company constructor
			 return new Company(
					 rs.getInt("branchID"),
					 rs.getString("brachName"),
					 rs.getString("address"),
					 rs.getString("ownerName"),
					 rs.getLong("ownerMobileno"),
					 rs.getLong(" frontdeskNo")
					 ); 
		 }
	 }
	 catch(SQLException e ) {
		 e.printStackTrace();
	 }
	 return null ; 
	}

	@Override
	public List<Company> getAllComapny() {
		 	List<Company> comp=new ArrayList<>();
		 	String sql="select * from Company ";
		 	try(Connection con=ConnectionFactory.getMySqlConnection();
		 			 Statement st=con.createStatement();
		 			ResultSet rs=st.executeQuery(sql)){
		 		while(rs.next()) {
		 			comp.add(new Company(
		 					rs.getInt("branchID"),
							 rs.getString("brachName"),
							 rs.getString("address"),
							 rs.getString("ownerName"),
							 rs.getLong("ownerMobileno"),
							 rs.getLong(" frontdeskNo")		 					
		 					));}
		 		
		 	 
		 		
		 		
		 	}
		 	catch(SQLException e) {
		 		e.printStackTrace();
		 	
		 	}
		 	return comp; 
	}
	 
	 @Override
	public long deleteCompanyById(long branchId) {
		 String sql="Delete from Company where branchId=?";
		 try(Connection con=ConnectionFactory.getMySqlConnection(); 
				 PreparedStatement ps=con.prepareStatement(sql)){
			 ps.setLong(1,branchId);
			 int rowaffected=ps.executeUpdate();
			 if(rowaffected>0) {
				 return branchId; 
			 }
			  
		 }catch(SQLException e) {
			 e.getStackTrace();
		 }
		 return -1; 
	}
 
	 
	
	
	@Override
	public String[] getAllColumns() {
		String sql = "SELECT * FROM Company LIMIT 1"; // Query to get metadata without retrieving all rows
        try (Connection con = ConnectionFactory.getMySqlConnection();
             PreparedStatement ps = con.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            ResultSetMetaData metaData = rs.getMetaData();
            int columnCount = metaData.getColumnCount();
            String[] columns = new String[columnCount];

            for (int i = 1; i <= columnCount; i++) {
                columns[i - 1] = metaData.getColumnName(i);
            }

            return columns; 
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0];
		 
	}
 

	 

}
