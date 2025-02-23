package com.training.dao.util;

import java.util.List;

import com.training.model.Student;

public interface StudentDao {

	long addStudent(Student student);
	long updateStudent(Student student);
	Student getStudentById(long id);
	List<Student> getAllStudents();
	long deleteStudentById(long id);
    public String[] getAllColumns();

}
