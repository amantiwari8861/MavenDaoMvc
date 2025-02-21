package com.training.dao;

import java.util.List;

import com.training.model.Student;

public interface StudentDao {

	long addStudentDetails(Student student);
	boolean updateStudent(Student student);
	boolean deleteStudent(long id);
	List<Student> getAllStudents();
	Student getStudentById(Student student);
    String[] getColumnsName();

}
