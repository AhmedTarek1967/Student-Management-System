package com.ahmed.StudentMangmentSystem.mapper;

import com.ahmed.StudentMangmentSystem.dtos.CourseDto;
import com.ahmed.StudentMangmentSystem.entities.Course;
import org.springframework.stereotype.Component;

@Component
public class CoursesMapper {
	
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
