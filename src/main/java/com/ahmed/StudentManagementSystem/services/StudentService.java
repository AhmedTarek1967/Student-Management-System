package com.ahmed.StudentManagementSystem.services;

import com.ahmed.StudentManagementSystem.dtos.*;
import com.ahmed.StudentManagementSystem.entities.Book;
import com.ahmed.StudentManagementSystem.entities.Course;
import com.ahmed.StudentManagementSystem.entities.Student;
import com.ahmed.StudentManagementSystem.exceptions.booksexception.BookNotFoundException;
import com.ahmed.StudentManagementSystem.exceptions.courseexceptions.CourseNotFoundException;
import com.ahmed.StudentManagementSystem.exceptions.studentexceptions.StudentNotFoundException;
import com.ahmed.StudentManagementSystem.mapper.BooksMapper;
import com.ahmed.StudentManagementSystem.mapper.CoursesMapper;
import com.ahmed.StudentManagementSystem.mapper.StudentMapper;
import com.ahmed.StudentManagementSystem.repos.BooksRepo;
import com.ahmed.StudentManagementSystem.repos.CoursesRepo;
import com.ahmed.StudentManagementSystem.repos.StudentRepo;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class StudentService {

	private final StudentRepo studentRepo;
	private final StudentMapper studentMapper;
	private final BooksRepo booksRepo;
	private final CoursesRepo coursesRepo;
	private final CoursesMapper coursesMapper;
	private final BooksMapper booksMapper;

	public StudentService(StudentRepo studentRepo, StudentMapper studentMapper, BooksRepo booksRepo,
						  CoursesRepo coursesRepo, CoursesMapper coursesMapper, BooksMapper booksMapper) {
		this.studentRepo = studentRepo;
		this.studentMapper = studentMapper;
		this.booksRepo = booksRepo;
		this.coursesRepo = coursesRepo;
		this.coursesMapper = coursesMapper;
		this.booksMapper = booksMapper;
	}

	public List<StudentResponseDto> getAllStudents() {
		return studentRepo.findAll()
					.stream()
					.map(studentMapper::fromEntityToDto)
					.toList();
	}

	public StudentResponseDto getStudentById(int id) {
		Student student = studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		return studentMapper.fromEntityToDto(student);
	}

	public StudentResponseDto createStudent(StudentDto studentDto){
		Student student = studentMapper.fromDtoToEntity(studentDto);
		Student savedStudent = studentRepo.save(student);
		return studentMapper.fromEntityToDto(savedStudent);
	}

	public StudentResponseDto updateStudentById(int id, StudentDto studentDto) {
		Student student = studentRepo.findById(id).orElseThrow(() -> new StudentNotFoundException(id));
		student.setFirstName(studentDto.firstName());
		student.setLastName(studentDto.lastName());
		student.setAge(studentDto.age());
		student.setEmail(studentDto.email());
		Student updatedStudent = studentRepo.save(student);
		return studentMapper.fromEntityToDto(updatedStudent);
	}

	public void deleteStudentById(int id){
		if (!studentRepo.existsById(id)) {
			throw new StudentNotFoundException(id);
		}
		studentRepo.deleteById(id);
	}

	@Transactional
	public void assignBookToStudent(int studentId, int bookId) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		Book book = booksRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

		book.setOwner(student);
		student.getBooks().add(book);

		booksRepo.save(book);
	}

	public StudentResponseDto getStudentWhoOwnsABook(int bookId){
		Book book = booksRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));
		Student student = book.getOwner();
		if (student == null) {
			return null;
		}
		return studentMapper.fromEntityToDto(student);
	}

	public List<BookResponseDto> getBooksOfStudent(int studentId) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		return student.getBooks()
					.stream()
					.map(booksMapper::fromEntityToResponseDto)
					.toList();
	}

	@Transactional
	public void deleteBookFromStudent(int studentId , int bookId) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		Book book = booksRepo.findById(bookId).orElseThrow(() -> new BookNotFoundException(bookId));

		if (student.equals(book.getOwner())) {
			student.getBooks().remove(book);
			book.setOwner(null);
			booksRepo.save(book);
		}
	}

	@Transactional
	public void enrollStudentInCourse(int studentId, int courseId) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		Course course = coursesRepo.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));

		student.getCourses().add(course);
		course.getStudents().add(student);

		studentRepo.save(student);
		coursesRepo.save(course);
	}

	public Set<CourseResponseDto> getStudentCourses(int studentId) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		return student.getCourses().stream()
				.map(coursesMapper::fromEntityToResponseDto)
				.collect(Collectors.toSet());
	}

	@Transactional
	public void removeStudentFromCourse(int studentId, int courseId) {
		Student student = studentRepo.findById(studentId).orElseThrow(() -> new StudentNotFoundException(studentId));
		Course course = coursesRepo.findById(courseId).orElseThrow(() -> new CourseNotFoundException(courseId));

		student.getCourses().remove(course);
		course.getStudents().remove(student);

		studentRepo.save(student);
		coursesRepo.save(course);
	}
}
