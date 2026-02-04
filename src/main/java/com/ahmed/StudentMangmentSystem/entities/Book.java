package com.ahmed.StudentMangmentSystem.entities;

import com.ahmed.StudentMangmentSystem.entities.Student;
import jakarta.persistence.*;
import lombok.Data;
import org.springframework.boot.convert.DataSizeUnit;

@Data
@Entity
@Table(name = "books")
public class Book {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String title;
	private String author;
	
	@ManyToOne
	@JoinColumn(name = "student_id")
	private Student owner;
}
