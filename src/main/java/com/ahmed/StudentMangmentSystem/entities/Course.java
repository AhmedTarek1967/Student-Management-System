package com.ahmed.StudentMangmentSystem.entities;

import com.ahmed.StudentMangmentSystem.entities.Student;
import jakarta.persistence.*;
import lombok.Data;

import java.util.HashSet;
import java.util.Set;

@Data
@Entity
@Table(name = "courses")
public class Course {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String name;
	private String code;
	
	@ManyToMany(mappedBy = "courses")
	private Set<Student> students = new HashSet<>();
}
