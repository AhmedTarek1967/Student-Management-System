package com.ahmed.StudentManagementSystem.entities;

import com.ahmed.StudentManagementSystem.entities.Student;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "courses")
@Getter
@Setter
@NoArgsConstructor
public class Course {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer id;

	private String name;
	private String code;

	@ManyToMany(mappedBy = "courses")
	private Set<Student> students = new HashSet<>();

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;
		Course course = (Course) o;
		return Objects.equals(id, course.id);
	}

	@Override
	public int hashCode() {
		return Objects.hash(id);
	}
}
