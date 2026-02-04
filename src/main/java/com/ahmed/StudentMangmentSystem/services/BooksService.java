package com.ahmed.StudentMangmentSystem.services;

import com.ahmed.StudentMangmentSystem.dtos.BooksDto;
import com.ahmed.StudentMangmentSystem.entities.Book;
import com.ahmed.StudentMangmentSystem.mapper.BooksMapper;
import com.ahmed.StudentMangmentSystem.repos.BooksRepo;
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
	
	public List<BooksDto> getAllBooks(){
		return booksRepo.findAll().stream().map(booksMapper::fromEntityToDto).toList();
	}
	
	public BooksDto getBooksById(int id){
		Book book = booksRepo.findById(id).orElse(null);
		return booksMapper.fromEntityToDto(book);
	}
	
	public BooksDto createBook(BooksDto booksDto){
		Book book = booksMapper.fromDtoToEntity(booksDto);
		Book savedbook = booksRepo.save(book);
		return booksMapper.fromEntityToDto(savedbook);
	}
	
	public BooksDto updateBookById(int id, BooksDto booksDto){
		Book book = booksRepo.findById(id).orElse(null);
		book.setTitle(booksDto.title());
		book.setAuthor(booksDto.author());
		Book updatedBook = booksRepo.save(book);
		
		return booksMapper.fromEntityToDto(updatedBook);
	}
	
	public String deleteBookById(int id){
		booksRepo.deleteById(id);
		return "Deleted Successfully";
	}
	
	
}
