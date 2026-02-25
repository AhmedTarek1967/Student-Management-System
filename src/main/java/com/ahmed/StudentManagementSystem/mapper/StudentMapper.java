package com.ahmed.StudentManagementSystem.mapper;

import com.ahmed.StudentManagementSystem.dtos.StudentDto;
import com.ahmed.StudentManagementSystem.dtos.StudentResponseDto;
import com.ahmed.StudentManagementSystem.entities.Book;
import com.ahmed.StudentManagementSystem.entities.Course;
import com.ahmed.StudentManagementSystem.entities.Student;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

@Component
public class StudentMapper {
	
	public StudentResponseDto fromEntityToDto(Student student) {
		return new StudentResponseDto(
			student.getId(),
			student.getFirstName(),
			student.getLastName(),
			student.getAge(),
			student.getEmail()
		);
	}
	
	public Student fromDtoToEntity(StudentDto dto) {
		Student student = new Student();
		student.setFirstName(dto.firstName());
		student.setLastName(dto.lastName());
		student.setAge(dto.age());
		student.setEmail(dto.email());
		return student;
	}
	
}
