package com.ahmed.StudentMangmentSystem.controller;

import com.ahmed.StudentMangmentSystem.dtos.*;
import com.ahmed.StudentMangmentSystem.entities.Course;
import com.ahmed.StudentMangmentSystem.services.StudentService;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/students")
public class StudentController {
	
	public StudentService studentService;
	
	public  StudentController(StudentService studentService) {
		this.studentService = studentService;
	}
	
	@GetMapping()
	public List<StudentResponseDto> getAllStudents(){
		return studentService.getAllStudents();
	}
	
	@GetMapping("/{id}")
	public StudentName getStudentById(@PathVariable int id){
		return studentService.getStudentById(id);
	}
	
	@PostMapping
	public StudentResponseDto createStudent(@RequestBody StudentDto studentDto){
		return studentService.createStudent(studentDto);
		
	}
	
	@PutMapping("/{id}")
	public StudentResponseDto updateStudentById(@PathVariable int id,@RequestBody StudentDto studentDto){
		
		return studentService.updateStudentById(id,studentDto);
		
	}
	@DeleteMapping("/{id}")
	public String deleteStudentById(@PathVariable int id){
		studentService.deleteStudentById(id);
		return "Deleted Successfully";
	}
	
	
	@PostMapping("/{studentId}/books/{bookId}")
	public String assignBookToStudent(@PathVariable int studentId, @PathVariable int bookId){
		return studentService.assignBookToStudent(studentId,bookId);
	}
	
	@GetMapping("/books/{bookId}/owner")
	public StudentResponseDto getStudentWhoOwnsABook(@PathVariable int bookId){
		return studentService.getStudentWhoOwnsABook(bookId);
	}
	
	@GetMapping("/{studentId}/books")
	public List<BooksDto> getBooksOfStudent(@PathVariable int studentId){
		return studentService.getBooksOfStudent(studentId);
	}
	
	@DeleteMapping("/{studentId}/books/{bookId}")
	public void deleteBookFromStudent(@PathVariable int studentId, @PathVariable int bookId){
		studentService.deleteBookFromStudent(studentId,bookId);
	}
	
	//Course Endpoints
	@PostMapping("/{studentId}/courses/{courseId}")
	public String enrollStudentInCourse(@PathVariable int studentId, @PathVariable int courseId){
		return studentService.enrollStudentInCourse(studentId,courseId);
	}
	
	@GetMapping("/students/{studentId}/courses")
	public Set<Course> getStudentCourses(@PathVariable int courseId){
		return studentService.getStudentCourses(courseId);
	}
	
	@DeleteMapping("/students/{studentId}/courses/{courseId}")
	public void deleteCourseFromStudent(@PathVariable int studentId, @PathVariable int courseId){
		studentService.removeStudentFromCourse(studentId,courseId);
	}
	
}
