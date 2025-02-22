package com.training.dao.impl;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import com.training.dao.util.ConnectionFactory;
import com.training.dao.util.StudentDao;
import com.training.model.Student;

public class StudentDaoImpl implements StudentDao {

    public StudentDaoImpl() {
        createTableIfNotExists();
    }

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
                        salary DOUBLE,
                        isMember BOOLEAN,
                        dob DATE,
                        joining DATETIME
                    );
                    """);
            System.out.println("Students table is ready.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    @Override
    public long addStudent(Student student) {
        String sql = "INSERT INTO Students (name, mobileNo, gender, gradPerc, salary, isMember, dob, joining) VALUES (?, ?, ?, ?, ?, ?, ?, ?)";
        try (Connection con = ConnectionFactory.getMySqlConnection();
             PreparedStatement ps = con.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            ps.setString(1, student.getName());
            ps.setLong(2, student.getMobileNo());
            ps.setString(3, String.valueOf(student.getGender()));
            ps.setFloat(4, student.getGradPerc());
            ps.setDouble(5, student.getSalary());
            ps.setBoolean(6, student.isMember());
            ps.setDate(7, Date.valueOf(student.getDob()));
            ps.setTimestamp(8, Timestamp.valueOf(student.getJoining()));

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
        return -1; // Indicates failure
    }

    @Override
    public long updateStudent(Student student) {
        String sql = "UPDATE Students SET name=?, mobileNo=?, gender=?, gradPerc=?, salary=?, isMember=?, dob=?, joining=? WHERE id=?";
        try (Connection con = ConnectionFactory.getMySqlConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {

            ps.setString(1, student.getName());
            ps.setLong(2, student.getMobileNo());
            ps.setString(3, String.valueOf(student.getGender()));
            ps.setFloat(4, student.getGradPerc());
            ps.setDouble(5, student.getSalary());
            ps.setBoolean(6, student.isMember());
            ps.setDate(7, Date.valueOf(student.getDob()));
            ps.setTimestamp(8, Timestamp.valueOf(student.getJoining()));
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
                        rs.getDouble("salary"),
                        rs.getBoolean("isMember"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getTimestamp("joining").toLocalDateTime()
                );
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null; // Student not found
    }

    @Override
    public List<Student> getAllStudents() {
        List<Student> students = new ArrayList<>();
        String sql = "SELECT * FROM Students";
        try (Connection con = ConnectionFactory.getMySqlConnection();
             Statement st = con.createStatement();
             ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                students.add(new Student(
                        rs.getLong("id"),
                        rs.getString("name"),
                        rs.getLong("mobileNo"),
                        rs.getString("gender").charAt(0),
                        rs.getFloat("gradPerc"),
                        rs.getDouble("salary"),
                        rs.getBoolean("isMember"),
                        rs.getDate("dob").toLocalDate(),
                        rs.getTimestamp("joining").toLocalDateTime()
                ));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return students;
    }

    @Override
    public long deleteStudentById(long id) {
        String sql = "DELETE FROM Students WHERE id=?";
        try (Connection con = ConnectionFactory.getMySqlConnection();
             PreparedStatement ps = con.prepareStatement(sql)) {
            ps.setLong(1, id);
            int affectedRows = ps.executeUpdate();
            return affectedRows > 0 ? id : -1;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return -1;
    }
    
    @Override
    public String[] getAllColumns() {
        String sql = "SELECT * FROM Students LIMIT 1"; // Query to get metadata without retrieving all rows
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
        return new String[0]; // Return an empty array if an error occurs
    }

    
    
}
