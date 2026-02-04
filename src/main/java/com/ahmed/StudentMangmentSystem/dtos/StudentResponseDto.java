package com.ahmed.StudentMangmentSystem.dtos;

import com.ahmed.StudentMangmentSystem.entities.Book;

public record StudentResponseDto(
	Integer id,
	String firstName,
	String lastName,
	Integer age,
	String email
) {}
