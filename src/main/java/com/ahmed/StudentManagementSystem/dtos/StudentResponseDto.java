package com.ahmed.StudentManagementSystem.dtos;

import com.ahmed.StudentManagementSystem.entities.Book;

public record StudentResponseDto(
	Integer id,
	String firstName,
	String lastName,
	Integer age,
	String email
) {}
