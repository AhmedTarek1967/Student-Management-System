package com.ahmed.StudentManagementSystem.exceptions.booksexception;

public class BookAlreadyExistsException extends RuntimeException {
	public BookAlreadyExistsException(String title) {
		super("A book with title '" + title + "' already exists!");
	}
	
}
