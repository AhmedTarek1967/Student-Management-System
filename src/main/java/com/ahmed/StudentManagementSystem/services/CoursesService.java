package com.ahmed.StudentManagementSystem.services;

import com.ahmed.StudentManagementSystem.dtos.CourseDto;
import com.ahmed.StudentManagementSystem.dtos.CourseResponseDto;
import com.ahmed.StudentManagementSystem.dtos.StudentResponseDto;
import com.ahmed.StudentManagementSystem.entities.Course;
import com.ahmed.StudentManagementSystem.entities.Student;
import com.ahmed.StudentManagementSystem.exceptions.courseexceptions.CourseNotFoundException;
import com.ahmed.StudentManagementSystem.mapper.CoursesMapper;
import com.ahmed.StudentManagementSystem.mapper.StudentMapper;
import com.ahmed.StudentManagementSystem.repos.CoursesRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

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

	public List<CourseResponseDto> getAllCourses(){
		return coursesRepo.findAll()
			.stream().map(coursesMapper::fromEntityToResponseDto)
			.toList();
	}

	public CourseResponseDto getCoursesById(int id){
		Course course = coursesRepo.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
		return coursesMapper.fromEntityToResponseDto(course);
	}

	public CourseResponseDto createCourse(CourseDto courseDto){
		Course course = coursesMapper.fromDtoToEntity(courseDto);
		Course savedCourse = coursesRepo.save(course);
		return coursesMapper.fromEntityToResponseDto(savedCourse);
	}

	public CourseResponseDto updateCourseById(int id, CourseDto courseDto){
		Course course = coursesRepo.findById(id).orElseThrow(() -> new CourseNotFoundException(id));
		course.setName(courseDto.name());
		course.setCode(courseDto.code());
		Course updatedCourse = coursesRepo.save(course);

		return coursesMapper.fromEntityToResponseDto(updatedCourse);
	}

	public Set<StudentResponseDto> getCourseStudents(int courseId) {
		Course course = coursesRepo.findById(courseId)
			.orElseThrow(() -> new CourseNotFoundException(courseId));

		return course.getStudents().stream()
				.map(studentMapper::fromEntityToDto)
				.collect(Collectors.toSet());
	}

	@Transactional
	public void deleteCourse(int courseId) {
		Course course = coursesRepo.findById(courseId)
			.orElseThrow(() -> new CourseNotFoundException(courseId));
		for (Student s : course.getStudents()) {
			s.getCourses().remove(course);
		}

		course.getStudents().clear();
		coursesRepo.delete(course);
	}

}
