package com.ahmed.StudentManagementSystem.repos;

import com.ahmed.StudentManagementSystem.entities.Book;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BooksRepo extends JpaRepository<Book,Integer> {
}
