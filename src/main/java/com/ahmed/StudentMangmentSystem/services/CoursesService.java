package com.ahmed.StudentMangmentSystem.services;

import com.ahmed.StudentMangmentSystem.dtos.CourseDto;
import com.ahmed.StudentMangmentSystem.dtos.StudentResponseDto;
import com.ahmed.StudentMangmentSystem.entities.Course;
import com.ahmed.StudentMangmentSystem.entities.Student;
import com.ahmed.StudentMangmentSystem.mapper.CoursesMapper;
import com.ahmed.StudentMangmentSystem.mapper.StudentMapper;
import com.ahmed.StudentMangmentSystem.repos.CoursesRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class CoursesService {
	
	private final CoursesRepo coursesRepo;
	private  final CoursesMapper coursesMapper;
	private final StudentMapper studentMapper;
	
	public CoursesService(CoursesRepo coursesRepo, CoursesMapper coursesMapper, StudentMapper studentMapper) {
		this.coursesRepo = coursesRepo;
		this.coursesMapper = coursesMapper;
		this.studentMapper = studentMapper;
	}
	
	public List<CourseDto> getAllCourses(){
		return coursesRepo.findAll()
			.stream().map(coursesMapper::fromEntityToDto)
			.toList();
	}
	
	public CourseDto getCoursesById(int id){
		Course course = coursesRepo.findById(id).orElse(null);
		return coursesMapper.fromEntityToDto(course);
	}
	
	public CourseDto createCourse(CourseDto courseDto){
		Course course = coursesMapper.fromDtoToEntity(courseDto);
		Course savedCourse = coursesRepo.save(course);
		return coursesMapper.fromEntityToDto(savedCourse);
	}
	
	public CourseDto updateCourseById(int id, CourseDto courseDto){
		Course course = coursesRepo.findById(id).orElse(null);
		course.setName(courseDto.name());
		Course updatedCourse = coursesRepo.save(course);
		
		return coursesMapper.fromEntityToDto(updatedCourse);
	}
	
	public String deleteCourseById(int id){
		coursesRepo.deleteById(id);
		return "Deleted Successfully";
	}
	
	public Set<Student> getCourseStudents(int courseId) {
		Course course = coursesRepo.findById(courseId)
			.orElseThrow(() -> new RuntimeException("Course not found"));
		
		return course.getStudents();
	}
	
	@Transactional
	public void deleteCourse(int courseId) {
		
		Course course = coursesRepo.findById(courseId)
			.orElseThrow(() -> new RuntimeException("Course not found"));
		for (Student s : course.getStudents()) {
			s.getCourses().remove(course);
		}
		
		course.getStudents().clear();
		
		coursesRepo.delete(course);
	}
	
}
