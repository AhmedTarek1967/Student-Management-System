package com.ahmed.StudentMangmentSystem.exceptions.booksexception;

public class BookAlreadyExistsException extends RuntimeException {
	public BookAlreadyExistsException(String title) {
		super("A book with title '" + title + "' already exists!");
	}
	
}
