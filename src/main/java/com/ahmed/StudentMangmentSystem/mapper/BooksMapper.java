package com.ahmed.StudentMangmentSystem.mapper;

import com.ahmed.StudentMangmentSystem.dtos.BooksDto;
import com.ahmed.StudentMangmentSystem.entities.Book;
import org.springframework.stereotype.Component;

@Component
public class BooksMapper {
	
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
