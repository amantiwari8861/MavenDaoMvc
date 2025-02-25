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

<<<<<<< HEAD
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
=======
    private void createTableIfNotExists() {
        try (Connection con = ConnectionFactory.getMySqlConnection();
             Statement st = con.createStatement()) {
            st.execute("""
                    CREATE TABLE IF NOT EXISTS Students (
                        id BIGINT PRIMARY KEY AUTO_INCREMENT,
                        name VARCHAR(100),
                        mobileNo BIGINT,
                        gender CHAR(1),
                        gradPerc FLOAT,
                        fee DOUBLE,
                        isMember TINYINT(1),
                        dob DATE,
                        joinedAt DATETIME
                    );
                    """);
            System.out.println("Students table is ready.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long addStudent(Student student) {
        String sql = "INSERT INTO Students (name, mobileNo, gender, gradPerc, fee, isMember, dob, joinedAt) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionFactory.getMySqlConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, student.getName());
            ps.setLong(2, student.getMobileNo());
            ps.setString(3, String.valueOf(student.getGender()));
            ps.setFloat(4, student.getGradPerc());
            ps.setDouble(5, student.getFee());
            ps.setBoolean(6, student.isMember());
            ps.setDate(7, Date.valueOf(student.getDob()));
            ps.setTimestamp(8, Timestamp.valueOf(student.getJoinedAt()));

            int affectedRows = ps.executeUpdate();
            if (affectedRows > 0) {
                ResultSet rs = ps.getGeneratedKeys();
                if (rs.next()) {
                    return rs.getLong(1);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public long updateStudent(Student student) {
        String sql = "UPDATE Students SET name=?, mobileNo=?, gender=?, gradPerc=?, fee=?, isMember=?, dob=?, joinedAt=? WHERE id=?";
        try (Connection con = ConnectionFactory.getMySqlConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setLong(2, student.getMobileNo());
            ps.setString(3, String.valueOf(student.getGender()));
            ps.setFloat(4, student.getGradPerc());
            ps.setDouble(5, student.getFee());
            ps.setBoolean(6, student.isMember());
            ps.setDate(7, Date.valueOf(student.getDob()));
            ps.setTimestamp(8, Timestamp.valueOf(student.getJoinedAt()));
            ps.setLong(9, student.getId());

            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? student.getId() : -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }

    @Override
    public Student getStudentById(long id) {
        String sql = "SELECT * FROM Students WHERE id=?";
        try (Connection con = ConnectionFactory.getMySqlConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            ResultSet rs = ps.executeQuery();

            if (rs.next()) {
                return new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("mobileNo"),
                        rs.getString("gender").charAt(0),
                        rs.getFloat("gradPerc"),
                        rs.getDouble("fee"),
                        rs.getBoolean("isMember"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getTimestamp("joinedAt").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Student not found
>>>>>>> 5538ce6572100775b39ad98a15af9b2cd9041963
    }

    @Override
    public List<Student> getAllStudents() {
<<<<<<< HEAD
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
=======
    	System.out.println("StudentDaoImpl.getAllStudents()");
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection con = ConnectionFactory.getMySqlConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
        	System.out.println("StudentDaoImpl.getAllStudents() 2");
            while (rs.next()) {
                students.add(new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("mobileNo"),
                        rs.getString("gender").charAt(0),
                        rs.getFloat("gradPerc"),
                        rs.getDouble("fee"),
                        rs.getBoolean("isMember"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getTimestamp("joinedAt").toLocalDateTime()
                ));
                System.out.println(students);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
>>>>>>> 5538ce6572100775b39ad98a15af9b2cd9041963
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
<<<<<<< HEAD
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
=======

            return columns;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return new String[0]; // Return an empty array if an error occurs
    }    
    
>>>>>>> 5538ce6572100775b39ad98a15af9b2cd9041963
}
