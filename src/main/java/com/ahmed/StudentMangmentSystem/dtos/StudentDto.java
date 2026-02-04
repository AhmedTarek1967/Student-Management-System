package com.ahmed.StudentMangmentSystem.dtos;

import com.ahmed.StudentMangmentSystem.entities.Book;
import com.ahmed.StudentMangmentSystem.entities.Course;
import jakarta.validation.constraints.*;

import java.util.List;


public record StudentDto(
	@NotEmpty String firstName,
	@NotEmpty String lastName,
	
	@NotNull
	@Min(1)
	@Max(120)
	Integer age,
	
	@Email
	@NotEmpty
	String email
) {}
