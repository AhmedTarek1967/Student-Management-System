package com.ahmed.StudentManagementSystem.mapper;

import com.ahmed.StudentManagementSystem.dtos.CourseDto;
import com.ahmed.StudentManagementSystem.dtos.CourseResponseDto;
import com.ahmed.StudentManagementSystem.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CoursesMapper {

	public CourseResponseDto fromEntityToResponseDto(Course course) {
		return new CourseResponseDto(course.getId(), course.getName(), course.getCode());
	}
	
	public CourseDto fromEntityToDto(Course course){
		return new CourseDto(course.getName(), course.getCode());
	}
	
	public Course fromDtoToEntity(CourseDto courseDto){
		Course course = new Course();
		course.setName(courseDto.name());
		course.setCode(courseDto.code());
		return course;
	}
	
}
