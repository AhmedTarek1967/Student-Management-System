package com.ahmed.StudentManagementSystem.services;

import com.ahmed.StudentManagementSystem.dtos.StudentDto;
import com.ahmed.StudentManagementSystem.dtos.StudentResponseDto;
import com.ahmed.StudentManagementSystem.entities.Student;
import com.ahmed.StudentManagementSystem.mapper.StudentMapper;
import com.ahmed.StudentManagementSystem.repos.StudentRepo;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

class StudentServiceTest {

    @Mock
    private StudentRepo studentRepo;

    @Mock
    private StudentMapper studentMapper;

    @InjectMocks
    private StudentService studentService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void createStudent_ShouldReturnSavedStudent() {
        StudentDto dto = new StudentDto("John", "Doe", 20, "john.doe@example.com");
        Student student = new Student();
        student.setFirstName("John");
        student.setLastName("Doe");

        Student savedStudent = new Student();
        savedStudent.setId(1);
        savedStudent.setFirstName("John");
        savedStudent.setLastName("Doe");

        StudentResponseDto responseDto = new StudentResponseDto(1, "John", "Doe", 20, "john.doe@example.com");

        when(studentMapper.fromDtoToEntity(dto)).thenReturn(student);
        when(studentRepo.save(student)).thenReturn(savedStudent);
        when(studentMapper.fromEntityToDto(savedStudent)).thenReturn(responseDto);

        StudentResponseDto result = studentService.createStudent(dto);

        assertNotNull(result);
        assertEquals(1, result.id());
        assertEquals("John", result.firstName());
        verify(studentRepo, times(1)).save(student);
    }

    @Test
    void getStudentById_WhenFound_ShouldReturnStudent() {
        Student student = new Student();
        student.setId(1);
        student.setFirstName("John");

        StudentResponseDto responseDto = new StudentResponseDto(1, "John", "Doe", 20, "john.doe@example.com");

        when(studentRepo.findById(1)).thenReturn(Optional.of(student));
        when(studentMapper.fromEntityToDto(student)).thenReturn(responseDto);

        StudentResponseDto result = studentService.getStudentById(1);

        assertNotNull(result);
        assertEquals("John", result.firstName());
    }
}
