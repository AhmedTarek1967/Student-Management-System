package com.ahmed.StudentManagementSystem.controller;

import com.ahmed.StudentManagementSystem.dtos.*;
import com.ahmed.StudentManagementSystem.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {

	private final StudentService studentService;

	public StudentController(StudentService studentService) {
		this.studentService = studentService;
	}

	@GetMapping
	public ResponseEntity<List<StudentResponseDto>> getAllStudents(){
		return ResponseEntity.ok(studentService.getAllStudents());
	}

	@GetMapping("/{id}")
	public ResponseEntity<StudentResponseDto> getStudentById(@PathVariable int id) {
		return ResponseEntity.ok(studentService.getStudentById(id));
	}

	@PostMapping
	public ResponseEntity<StudentResponseDto> createStudent(@Valid @RequestBody StudentDto studentDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(studentService.createStudent(studentDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<StudentResponseDto> updateStudentById(@PathVariable int id, @Valid @RequestBody StudentDto studentDto) {
		return ResponseEntity.ok(studentService.updateStudentById(id, studentDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteStudentById(@PathVariable int id){
		studentService.deleteStudentById(id);
		return ResponseEntity.noContent().build();
	}

	@PostMapping("/{studentId}/books/{bookId}")
	public ResponseEntity<String> assignBookToStudent(@PathVariable int studentId, @PathVariable int bookId) {
		studentService.assignBookToStudent(studentId, bookId);
		return ResponseEntity.ok("Book assigned successfully");
	}

	@GetMapping("/books/{bookId}/owner")
	public ResponseEntity<StudentResponseDto> getStudentWhoOwnsABook(@PathVariable int bookId){
		return ResponseEntity.ok(studentService.getStudentWhoOwnsABook(bookId));
	}

	@GetMapping("/{studentId}/books")
	public ResponseEntity<List<BookResponseDto>> getBooksOfStudent(@PathVariable int studentId) {
		return ResponseEntity.ok(studentService.getBooksOfStudent(studentId));
	}

	@DeleteMapping("/{studentId}/books/{bookId}")
	public ResponseEntity<Void> deleteBookFromStudent(@PathVariable int studentId, @PathVariable int bookId) {
		studentService.deleteBookFromStudent(studentId, bookId);
		return ResponseEntity.noContent().build();
	}

	//Course Endpoints
	@PostMapping("/{studentId}/courses/{courseId}")
	public ResponseEntity<String> enrollStudentInCourse(@PathVariable int studentId, @PathVariable int courseId) {
		studentService.enrollStudentInCourse(studentId, courseId);
		return ResponseEntity.ok("Enrolled in course successfully");
	}

	@GetMapping("/{studentId}/courses")
	public ResponseEntity<Set<CourseResponseDto>> getStudentCourses(@PathVariable int studentId) {
		return ResponseEntity.ok(studentService.getStudentCourses(studentId));
	}

	@DeleteMapping("/{studentId}/courses/{courseId}")
	public ResponseEntity<Void> deleteCourseFromStudent(@PathVariable int studentId, @PathVariable int courseId) {
		studentService.removeStudentFromCourse(studentId, courseId);
		return ResponseEntity.noContent().build();
	}

}
