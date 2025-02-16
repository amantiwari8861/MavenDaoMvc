package com.training.dao;

import java.util.List;

import com.training.modal.Student;

public interface StudentDao {
	
	int addStudentDetails(Student student);
	boolean updateStudent(Student student);
	boolean deleteStudent(int id);
	List<Student> getAllStudents();
	Student getStudentById(Student student);
    String[] getColumnsName();

}
