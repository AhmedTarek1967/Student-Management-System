package com.ahmed.StudentManagementSystem.services;

import com.ahmed.StudentManagementSystem.dtos.BookResponseDto;
import com.ahmed.StudentManagementSystem.dtos.BooksDto;
import com.ahmed.StudentManagementSystem.entities.Book;
import com.ahmed.StudentManagementSystem.exceptions.booksexception.BookNotFoundException;
import com.ahmed.StudentManagementSystem.mapper.BooksMapper;
import com.ahmed.StudentManagementSystem.repos.BooksRepo;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BooksService {

	private final BooksRepo booksRepo;
	private final BooksMapper booksMapper;

	public BooksService(BooksRepo booksRepo, BooksMapper booksMapper) {
		this.booksRepo = booksRepo;
		this.booksMapper = booksMapper;
	}

	public List<BookResponseDto> getAllBooks(){
		return booksRepo.findAll().stream().map(booksMapper::fromEntityToResponseDto).toList();
	}

	public BookResponseDto getBooksById(int id){
		Book book = booksRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
		return booksMapper.fromEntityToResponseDto(book);
	}

	public BookResponseDto createBook(BooksDto booksDto){
		Book book = booksMapper.fromDtoToEntity(booksDto);
		Book savedbook = booksRepo.save(book);
		return booksMapper.fromEntityToResponseDto(savedbook);
	}

	public BookResponseDto updateBookById(int id, BooksDto booksDto){
		Book book = booksRepo.findById(id).orElseThrow(() -> new BookNotFoundException(id));
		book.setTitle(booksDto.title());
		book.setAuthor(booksDto.author());
		Book updatedBook = booksRepo.save(book);

		return booksMapper.fromEntityToResponseDto(updatedBook);
	}

	public void deleteBookById(int id){
		if (!booksRepo.existsById(id)) {
			throw new BookNotFoundException(id);
		}
		booksRepo.deleteById(id);
	}
}
