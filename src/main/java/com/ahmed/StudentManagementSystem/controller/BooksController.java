package com.ahmed.StudentManagementSystem.controller;

import com.ahmed.StudentManagementSystem.dtos.BookResponseDto;
import com.ahmed.StudentManagementSystem.dtos.BooksDto;
import com.ahmed.StudentManagementSystem.services.BooksService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
	public ResponseEntity<List<BookResponseDto>> getAllBooks(){
		return ResponseEntity.ok(booksService.getAllBooks());
	}

	@GetMapping("/{id}")
	public ResponseEntity<BookResponseDto> getBooksById(@PathVariable int id){
		return ResponseEntity.ok(booksService.getBooksById(id));
	}

	@PostMapping
	public ResponseEntity<BookResponseDto> createBook(@Valid @RequestBody BooksDto booksDto){
		return ResponseEntity.status(HttpStatus.CREATED).body(booksService.createBook(booksDto));
	}

	@PutMapping("/{id}")
	public ResponseEntity<BookResponseDto> updateBookById(@PathVariable int id, @Valid @RequestBody BooksDto booksDto){
		return ResponseEntity.ok(booksService.updateBookById(id, booksDto));
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteBookById(@PathVariable int id){
		booksService.deleteBookById(id);
		return ResponseEntity.noContent().build();
	}

}
