package com.ahmed.StudentManagementSystem.exceptions.booksexception;

public class BookNotFoundException extends RuntimeException {
	public BookNotFoundException(Integer id) {
		super("Book with id " + id + " was not found!");
	}
}
