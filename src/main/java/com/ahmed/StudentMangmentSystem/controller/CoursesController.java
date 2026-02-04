package com.ahmed.StudentMangmentSystem.controller;

import com.ahmed.StudentMangmentSystem.dtos.CourseDto;
import com.ahmed.StudentMangmentSystem.entities.Student;
import com.ahmed.StudentMangmentSystem.services.CoursesService;
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
	public List<CourseDto> getAllCourses(){
		return coursesService.getAllCourses();
	}
	
	@GetMapping("/{id}")
	public CourseDto getCoursesById(@PathVariable int id){
		return coursesService.getCoursesById(id);
	}
	
	@PostMapping
	public CourseDto createCourse(@RequestBody CourseDto courseDto){
		
		return coursesService.createCourse(courseDto);
	}
	
	@PutMapping("/{id}")
	public CourseDto updateCourseById(@PathVariable int id,@RequestBody CourseDto courseDto){
		
		return coursesService.updateCourseById(id,courseDto);
		
	}
	@DeleteMapping("/{id}")
	public String deleteCourse(@PathVariable int id){
		coursesService.deleteCourse(id);
		return "Deleted Successfully";
	}
	
	@GetMapping("/courses/{courseId}/students")
	public Set<Student> getCourseStudents(@PathVariable int courseId){
		return coursesService.getCourseStudents(courseId);
	}
	
}
