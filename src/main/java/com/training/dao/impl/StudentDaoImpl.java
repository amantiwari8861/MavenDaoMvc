package com.training.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.training.dao.StudentDao;
import com.training.dao.util.ConnectionFactory;
import com.training.model.Student;

public class StudentDaoImpl implements StudentDao {

	private String columns[];

    @Override
    public long addStudentDetails(Student student) {
        long rowsAffected = -1;
		try {
			Connection con = ConnectionFactory.getMySqlConnection();
			PreparedStatement ps = con.prepareStatement("insert into Students(id,name,mobileNo,gender,gradPerc,fee,isMember,dob,joinedAt)values (?,?,?,?,?,?,?,?,?);");
			ps.setLong(1, student.getId());
			ps.setString(2, student.getName());
			ps.setLong(3, student.getMobileNo());
			ps.setString(4, String.valueOf(student.getGender()));
			ps.setFloat(5,student.getGradPerc());
			ps.setDouble(6, student.getFee());
			ps.setBoolean(7, student.isMember());
			ps.setDate(8, student.getDob());
			ps.setDate(9, java.sql.Date.valueOf(student.getJoinedAt()));			
			System.out.println(ps);
			
		    rowsAffected = ps.executeUpdate();
		    ps.close();
		    con.close();
		    
			}
			catch(Exception e) {
				System.out.println(e.getLocalizedMessage());
			}
		          return rowsAffected;
    }

    @Override
    public boolean updateStudent(Student student) {
        boolean status =false;
		try {
		Connection con=	ConnectionFactory.getMySqlConnection();
		PreparedStatement ps = con.prepareStatement("update Students set name=?,mobileNo=?,gender=?,gradPerc=?,fee=?,isMember=?,dob=?,joinedAt=? where id=?;");
		ps.setString(1, student.getName());
		ps.setLong(2, student.getMobileNo());
		ps.setString(3, String.valueOf(student.getGender()));
		ps.setFloat(4,student.getGradPerc());
		ps.setDouble(5, student.getFee());
		ps.setBoolean(6, student.isMember());
		ps.setDate(7, student.getDob());
		ps.setDate(8, java.sql.Date.valueOf(student.getJoinedAt()));	
		ps.setLong(9, student.getId());
		System.out.println(ps);
		
	    status = ps.executeUpdate()>0?true:false;
	    ps.close();
	    con.close();
	    
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	          return status;
    }

    @Override
    public boolean deleteStudent(long id) {
        boolean status =false;
		try {
		Connection con=	ConnectionFactory.getMySqlConnection();
		Statement st = con.createStatement();
		long rowsAffected = st.executeUpdate("delete from students where id="+id+";");
	    status = rowsAffected>0?true:false;
	    st.close();
	    con.close();
	    
		}
		catch(Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
	          return status;
    }

    @Override
    public List<Student> getAllStudents() {
        ArrayList<Student> al = new ArrayList<>();
			try {
				Connection con=	ConnectionFactory.getMySqlConnection();
				Statement st = con.createStatement();
			    ResultSet rs = st.executeQuery("select * from Students;");
			    
			    ResultSetMetaData rsmd = rs.getMetaData();
			    columns = new String[rsmd.getColumnCount()-1];
			    for(int i=0; i<rsmd.getColumnCount()-1; i++)
			    {
			    	columns[i] = rsmd.getColumnName(i+1);
			    }
			    while(rs.next()) {
			    	Student temp = new Student();
			    	temp.setId(rs.getLong(1));
			    	temp.setName(rs.getString(2));
			    	temp.setMobileNo(rs.getLong(3));
			    	temp.setGender(rs.getString(4).charAt(0));
			    	temp.setGradPerc(rs.getFloat(5));
			    	temp.setFee(rs.getDouble(6));
			    	temp.setMember(rs.getBoolean(7));
			    	temp.setDob(rs.getDate(8));
			    	temp.setJoinedAt(rs.getDate(9).toLocalDate());			
			    	
			    	al.add(temp);
			    }
			    rs.close();
			    st.close();
			    con.close();
			    
				}
				catch(Exception e) {
					System.out.println(e.getLocalizedMessage());
				}
			        return al;
    }

    @Override
    public Student getStudentById(Student student) {
        try {
            Connection con=	ConnectionFactory.getMySqlConnection();
            PreparedStatement ps =  con.prepareStatement("select * from students where id=?;");
            ps.setLong(1, student.getId());
            ResultSet rs = ps.executeQuery();
            
            if(rs.next()) {
                student.setId(rs.getLong(1));
                student.setName(rs.getString(2));
                student.setMobileNo(rs.getLong(3));
                student.setGender(rs.getString(4).charAt(0));
                student.setGradPerc(rs.getFloat(5));
                student.setFee(rs.getDouble(6));
                student.setMember(rs.getBoolean(7));
                student.setDob(rs.getDate(8));
                student.setJoinedAt(rs.getDate(9).toLocalDate());			
                
            }
            else {
                student=null;
            }
           
            rs.close();
            ps.close();
            con.close();
            
            }
            catch(Exception e) {
                System.out.println(e.getLocalizedMessage());
            }
                return student;
    }

    @Override
    public String[] getColumnsName() {
        return columns;
     }
}
