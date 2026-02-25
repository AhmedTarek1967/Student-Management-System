package com.ahmed.StudentManagementSystem.entities;

import com.ahmed.StudentManagementSystem.entities.Book;
import com.ahmed.StudentManagementSystem.entities.Course;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "students")
@Getter
@Setter
@NoArgsConstructor
public class Student {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;
	
	private String firstName;
	private String lastName;
	private Integer age;
	private String email;
	
	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Book> books = new ArrayList<>();
	
	@ManyToMany
	@JoinTable(
		name = "students_courses",
		joinColumns = @JoinColumn(name = "student_id"),
		inverseJoinColumns = @JoinColumn(name = "course_id")
	)
	private Set<Course> courses = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Student student = (Student) o;
		return Objects.equals(id, student.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
