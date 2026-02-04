package com.ahmed.StudentMangmentSystem.mapper;

import com.ahmed.StudentMangmentSystem.dtos.StudentDto;
import com.ahmed.StudentMangmentSystem.dtos.StudentResponseDto;
import com.ahmed.StudentMangmentSystem.entities.Book;
import com.ahmed.StudentMangmentSystem.entities.Course;
import com.ahmed.StudentMangmentSystem.entities.Student;
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
