package com.ahmed.StudentMangmentSystem.dtos;

import jakarta.validation.constraints.NotEmpty;
import org.springframework.stereotype.Component;

public record BooksDto(
	@NotEmpty
	String title,
	@NotEmpty
	String author
) {
}
