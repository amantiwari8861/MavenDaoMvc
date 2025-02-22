package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.dao.util.ConnectionFactory;
import com.training.model.Courses;
import com.training.dao.CoursesDao;

public class Courses_Impl implements CoursesDao{
 
	private static boolean isTableCreated=false;
	
	private void createTable() {
		try {
			
			Connection con=ConnectionFactory.getMySqlConnection();
			Statement st=con.createStatement();
			st.execute("""
					create table IF NOT EXISTS  courses(
					course_id  int  primary key auto_increment,
					name varchar(255),
                    price double,
                    duration double,
                    technologies text,
                    affiliatedBy varchar(255));
					""");
			isTableCreated=true;
			con.close();
			st.close();
			
			}catch(Exception e) {
				
			e.printStackTrace();
		}
	}
	public Courses_Impl(){
		if(!isTableCreated) {
		createTable();
		}
	}

	public long addCourse(Courses course) {
		
		int rowsEffected=0;
		try {
			Connection con=ConnectionFactory.getMySqlConnection();
			PreparedStatement ps=con.prepareStatement("insert into courses(name,price,duration,technologies,affiliatedBy) values(?,?,?,?,?);");
			ps.setString(1, course.getName());
			ps.setDouble(2, course.getPrice());
			ps.setDouble(3, course.getDuration());
			ps.setString(4, course.getTechnologies());
			ps.setString(5, course.getAffiliatedBy());
			

			System.out.println(ps);
			rowsEffected=ps.executeUpdate();
			ps.close();
			con.close();
			
			
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return rowsEffected;

	}
	
	public boolean updateCourse(Courses course) {
		boolean status=false;
		try {
			Connection con=ConnectionFactory.getMySqlConnection();
			PreparedStatement ps=con.prepareStatement("Update courses set name=?,price=?,duration=?,technologies=?,affiliatedBy=? where course_id=?;");
			ps.setString(1, course.getName());
			ps.setDouble(2, course.getPrice());
			ps.setDouble(3, course.getDuration());
			ps.setString(4, course.getTechnologies());
			ps.setString(5, course.getAffiliatedBy());
			ps.setInt(6, course.getCourse_id());
			
			status=ps.executeUpdate()>0?true:false;
			ps.close();
			con.close();
			
			
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return status;
		
	}
	
	public boolean deleteCourseById(int id) {
		boolean status=false;
		try {
			Connection con=ConnectionFactory.getMySqlConnection();
			Statement st=con.createStatement();
			int rowsAffected=st.executeUpdate("delete from courses where course_id="+id+";");
	
			status=rowsAffected>0?true:false;
			st.close();
			con.close();
			
			
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return status;
	}
	
	public List<Courses> getAllCourses(){
		ArrayList<Courses> courses=new ArrayList<>();
		
		try {
			Connection con=ConnectionFactory.getMySqlConnection();
			Statement st=con.createStatement();
			ResultSet rs=st.executeQuery("select * from courses;");
		
				
			while(rs.next()) {
				Courses temp=new Courses();
				temp.setCourse_id(rs.getInt(1));
				temp.setName(rs.getString(2));
				temp.setPrice(rs.getDouble(3));
				temp.setDuration(rs.getDouble(4));
				temp.setTechnologies(rs.getString(5));
				temp.setAffiliatedBy(rs.getString(6));
				
				courses.add(temp);
			}
			rs.close();
			st.close();
			con.close();
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		return courses;
		
	}
	
}


