package com.ahmed.StudentManagementSystem.exceptions.courseexceptions;

public class CourseAlreadyExistsException extends RuntimeException{
	public CourseAlreadyExistsException(String courseName){
		super("A course with name '" + courseName + "' already exists!");
	}
}
