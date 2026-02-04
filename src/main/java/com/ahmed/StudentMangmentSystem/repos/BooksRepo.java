package com.ahmed.StudentMangmentSystem.repos;

import com.ahmed.StudentMangmentSystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepo extends JpaRepository<Book,Integer> {
}
