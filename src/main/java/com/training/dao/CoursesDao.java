package com.training.dao;

import java.util.List;

import com.training.model.Courses;

public interface CoursesDao {

	long addCourse(Courses course);
	boolean updateCourse(Courses course);
	boolean deleteCourseById(int id);
	List<Courses> getAllCourses();
    
}
