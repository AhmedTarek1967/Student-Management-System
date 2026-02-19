package com.ahmed.StudentMangmentSystem.services;

import com.ahmed.StudentMangmentSystem.dtos.*;
import com.ahmed.StudentMangmentSystem.entities.Book;
import com.ahmed.StudentMangmentSystem.entities.Course;
import com.ahmed.StudentMangmentSystem.entities.Student;
import com.ahmed.StudentMangmentSystem.exceptions.studentexceptions.StudentNotFoundException;
import com.ahmed.StudentMangmentSystem.mapper.CoursesMapper;
import com.ahmed.StudentMangmentSystem.mapper.StudentMapper;
import com.ahmed.StudentMangmentSystem.repos.BooksRepo;
import com.ahmed.StudentMangmentSystem.repos.CoursesRepo;
import com.ahmed.StudentMangmentSystem.repos.StudentRepo;
import jakarta.transaction.Transactional;
import org.jspecify.annotations.NonNull;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class StudentService {
	
	private final StudentRepo studentRepo;
	private final StudentMapper studentMapper;
	private final BooksRepo booksRepo;
	private final CoursesRepo coursesRepo;
	private final CoursesMapper coursesMapper;
	
	
	public StudentService(StudentRepo studentRepo, StudentMapper studentMapper, BooksRepo booksRepo, CoursesRepo coursesRepo, CoursesMapper coursesMapper) {
		this.studentRepo = studentRepo;
		this.studentMapper = studentMapper;
		this.booksRepo = booksRepo;
		this.coursesRepo = coursesRepo;
		this.coursesMapper = coursesMapper;
	}
	
	public List<StudentResponseDto> getAllStudents() {
		return studentRepo.findAll()
					.stream()
					.map(studentMapper::fromEntityToDto)
					.toList() ;
	}
	
	public StudentName getStudentById(int id) throws Throwable {
		
		Student student = (Student) studentRepo.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
		
		return new StudentName(student.getFirstName(), student.getLastName());
		
	}
	
	public StudentResponseDto createStudent(StudentDto studentDto){
		Student student = studentMapper.fromDtoToEntity(studentDto);
		Student savedStudent = studentRepo.save(student);
		return studentMapper.fromEntityToDto(savedStudent);
	}
	
	public StudentResponseDto updateStudentById(int id, @NonNull StudentDto studentDto) throws Throwable {
		Student student =(Student) studentRepo.findById(id).orElseThrow(()-> new StudentNotFoundException(id));
		student.setFirstName(studentDto.firstName());
		student.setLastName(studentDto.lastName());
		student.setAge(studentDto.age());
		student.setEmail(studentDto.email());
		Student updatedStudent = studentRepo.save(student);
		return studentMapper.fromEntityToDto(updatedStudent);
	}
	
	public String deleteStudentById(int id){
		studentRepo.deleteById(id);
		
		return "Deleted student with id: " + id;
	}
	
	@Transactional
	public String assignBookToStudent(int studentId, int bookId) throws Throwable {
		
		Student student = (Student) studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		
		Book book = booksRepo.findById(bookId)
			.orElseThrow(() -> new RuntimeException("Book not found"));
		
		book.setOwner(student);
		student.getBooks().add(book);
		
		booksRepo.save(book);
		return "Done";
	}
	
	
	
	public StudentResponseDto getStudentWhoOwnsABook(int bookId){
		Book book = booksRepo.findById(bookId).orElse(null);
		Student student = book.getOwner();
		return studentMapper.fromEntityToDto(student);
	}
	
	
	public List<BooksDto> getBooksOfStudent(int studentId) throws Throwable {
		Student student = (Student) studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		return (List<BooksDto>) student.getBooks()
					.stream()
					.map(book -> new BooksDto(book.getTitle(),
						book.getAuthor()));
	}
	
	public void deleteBookFromStudent(int studentId , int bookId) throws Throwable {
		Student student = (Student) studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		Book book = booksRepo.findById(bookId).orElse(null);
		student.getBooks().remove(book);
		studentRepo.save(student);
	}
	
	//Course Assignment Methods here
	@Transactional
	public String enrollStudentInCourse(int studentId, int courseId) throws Throwable {
		
		Student student = (Student) studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		
		Course course = coursesRepo.findById(courseId)
			.orElseThrow(() -> new RuntimeException("Course not found"));
		
		student.getCourses().add(course);
		course.getStudents().add(student);
		
		studentRepo.save(student);
		coursesRepo.save(course);
		return "Done";
	}
	
	
	public Set<Course> getStudentCourses(int studentId) throws Throwable {
		Student student = (Student) studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		
		return student.getCourses();
	}
	
	
	@Transactional
	public void removeStudentFromCourse(int studentId, int courseId) throws Throwable {
		
		Student student = (Student) studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		
		Course course = coursesRepo.findById(courseId)
			.orElseThrow(() -> new RuntimeException("Course not found"));
		
		student.getCourses().remove(course);
		course.getStudents().remove(student);
		
		studentRepo.save(student);
		coursesRepo.save(course);
	}
	
	
}
