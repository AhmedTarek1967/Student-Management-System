package com.ahmed.StudentManagementSystem.mapper;

import com.ahmed.StudentManagementSystem.dtos.BookResponseDto;
import com.ahmed.StudentManagementSystem.dtos.BooksDto;
import com.ahmed.StudentManagementSystem.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BooksMapper {

	public BookResponseDto fromEntityToResponseDto(Book book) {
		return new BookResponseDto(book.getId(), book.getTitle(), book.getAuthor());
	}
	
	public BooksDto fromEntityToDto(Book books){
		return new BooksDto(books.getTitle(), books.getAuthor());
	}
	
	public Book fromDtoToEntity(BooksDto booksDto){
		Book book = new Book();
		book.setTitle(booksDto.title());
		book.setAuthor(booksDto.author());
		return book;
	}
	
	
}
