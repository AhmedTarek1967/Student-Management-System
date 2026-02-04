package com.ahmed.StudentMangmentSystem.controller;

import com.ahmed.StudentMangmentSystem.dtos.BooksDto;
import com.ahmed.StudentMangmentSystem.dtos.CourseDto;
import com.ahmed.StudentMangmentSystem.services.BooksService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/books")
public class BooksController {
	
	private final BooksService booksService;
	
	public BooksController(BooksService booksService) {
		this.booksService = booksService;
	}
	
	
	@GetMapping
	public List<BooksDto> getAllCourses(){
		return booksService.getAllBooks();
	}
	
	@GetMapping("/{id}")
	public BooksDto getBooksById(@PathVariable int id){
		return booksService.getBooksById(id);
	}
	
	@PostMapping
	public BooksDto createBook(@RequestBody BooksDto booksDto){
		
		return booksService.createBook(booksDto);
	}
	
	@PutMapping("/{id}")
	public BooksDto updateBookById(@PathVariable int id,@RequestBody BooksDto booksDto){
		
		return booksService.updateBookById(id,booksDto);
		
	}
	@DeleteMapping("/{id}")
	public String deleteBookById(@PathVariable int id){
		booksService.deleteBookById(id);
		return "Deleted Successfully";
	}
	
}
