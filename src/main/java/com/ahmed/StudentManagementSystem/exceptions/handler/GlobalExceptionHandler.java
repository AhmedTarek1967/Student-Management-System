package com.ahmed.StudentManagementSystem.exceptions.handler;

import com.ahmed.StudentManagementSystem.exceptions.ErrorResponse;
import com.ahmed.StudentManagementSystem.exceptions.booksexception.BookAlreadyExistsException;
import com.ahmed.StudentManagementSystem.exceptions.booksexception.BookNotFoundException;
import com.ahmed.StudentManagementSystem.exceptions.courseexceptions.CourseAlreadyExistsException;
import com.ahmed.StudentManagementSystem.exceptions.courseexceptions.CourseNotFoundException;
import com.ahmed.StudentManagementSystem.exceptions.studentexceptions.StudentAlreadyExistsException;
import com.ahmed.StudentManagementSystem.exceptions.studentexceptions.StudentNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<Map<String, String>> handleValidationExceptions(MethodArgumentNotValidException ex) {
		Map<String, String> errors = new HashMap<>();
		ex.getBindingResult().getAllErrors().forEach((error) -> {
			String fieldName = ((FieldError) error).getField();
			String errorMessage = error.getDefaultMessage();
			errors.put(fieldName, errorMessage);
		});
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(errors);
	}
	
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
