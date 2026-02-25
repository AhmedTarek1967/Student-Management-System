package com.ahmed.StudentManagementSystem.controller;

import com.ahmed.StudentManagementSystem.dtos.CourseDto;
import com.ahmed.StudentManagementSystem.dtos.CourseResponseDto;
import com.ahmed.StudentManagementSystem.dtos.StudentResponseDto;
import com.ahmed.StudentManagementSystem.services.CoursesService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("/api/v1/courses")
public class CoursesController {

	private final CoursesService coursesService;

	public CoursesController(CoursesService coursesService) {
		this.coursesService = coursesService;
	}

	@GetMapping
	public ResponseEntity<List<CourseResponseDto>> getAllCourses(){
		return ResponseEntity.ok(coursesService.getAllCourses());
	}

	@GetMapping("/{id}")
	public ResponseEntity<CourseResponseDto> getCoursesById(@PathVariable int id){
		return ResponseEntity.ok(coursesService.getCoursesById(id));
	}

	@PostMapping
	public ResponseEntity<CourseResponseDto> createCourse(@Valid @RequestBody CourseDto courseDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(coursesService.createCourse(courseDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<CourseResponseDto> updateCourseById(@PathVariable int id, @Valid @RequestBody CourseDto courseDto){
		return ResponseEntity.ok(coursesService.updateCourseById(id, courseDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteCourse(@PathVariable int id){
		coursesService.deleteCourse(id);
		return ResponseEntity.noContent().build();
	}

	@GetMapping("/{courseId}/students")
	public ResponseEntity<Set<StudentResponseDto>> getCourseStudents(@PathVariable int courseId){
		return ResponseEntity.ok(coursesService.getCourseStudents(courseId));
	}

}
