package com.ahmed.StudentMangmentSystem.exceptions.handler;

import com.ahmed.StudentMangmentSystem.exceptions.ErrorResponse;
import com.ahmed.StudentMangmentSystem.exceptions.booksexception.BookAlreadyExistsException;
import com.ahmed.StudentMangmentSystem.exceptions.booksexception.BookNotFoundException;
import com.ahmed.StudentMangmentSystem.exceptions.courseexceptions.CourseAlreadyExistsException;
import com.ahmed.StudentMangmentSystem.exceptions.courseexceptions.CourseNotFoundException;
import com.ahmed.StudentMangmentSystem.exceptions.studentexceptions.StudentAlreadyExistsException;
import com.ahmed.StudentMangmentSystem.exceptions.studentexceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
	// ===== STUDENT =====
	@ExceptionHandler(StudentNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleStudentNotFound(StudentNotFoundException e) {
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorResponse(e.getMessage(), 404));
	}
	
	@ExceptionHandler(StudentAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleStudentAlreadyExists(StudentAlreadyExistsException e) {
		return ResponseEntity
			.status(HttpStatus.CONFLICT)
			.body(new ErrorResponse(e.getMessage(), 409));
	}
	
	// ===== COURSE =====
	@ExceptionHandler(CourseNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleCourseNotFound(CourseNotFoundException e) {
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorResponse(e.getMessage(), 404));
	}
	
	@ExceptionHandler(CourseAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleCourseAlreadyExists(CourseAlreadyExistsException e) {
		return ResponseEntity
			.status(HttpStatus.CONFLICT)
			.body(new ErrorResponse(e.getMessage(), 409));
	}
	
	// ===== BOOK =====
	@ExceptionHandler(BookNotFoundException.class)
	public ResponseEntity<ErrorResponse> handleBookNotFound(BookNotFoundException e) {
		return ResponseEntity
			.status(HttpStatus.NOT_FOUND)
			.body(new ErrorResponse(e.getMessage(), 404));
	}
	
	@ExceptionHandler(BookAlreadyExistsException.class)
	public ResponseEntity<ErrorResponse> handleBookAlreadyExists(BookAlreadyExistsException e) {
		return ResponseEntity
			.status(HttpStatus.CONFLICT)
			.body(new ErrorResponse(e.getMessage(), 409));
	}
	
	// ===== CATCH ALL - always keep this last =====
	@ExceptionHandler(Exception.class)
	public ResponseEntity<ErrorResponse> handleEverythingElse(Exception e) {
		return ResponseEntity
			.status(HttpStatus.INTERNAL_SERVER_ERROR)
			.body(new ErrorResponse("Something went wrong on our side! 🔧", 500));
	}
}
