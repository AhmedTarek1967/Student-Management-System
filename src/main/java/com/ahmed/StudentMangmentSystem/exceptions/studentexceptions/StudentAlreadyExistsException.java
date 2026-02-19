package com.ahmed.StudentMangmentSystem.exceptions.studentexceptions;

public class StudentAlreadyExistsException extends RuntimeException{
	public StudentAlreadyExistsException(String email) {
		super("A student with email '" + email + "' is already registered!");
	}
}
