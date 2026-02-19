package com.ahmed.StudentMangmentSystem.exceptions.studentexceptions;

public class StudentNotFoundException extends RuntimeException{
	public StudentNotFoundException(Integer id) {
		super("Student with id " + id + " not found");
	}
}
