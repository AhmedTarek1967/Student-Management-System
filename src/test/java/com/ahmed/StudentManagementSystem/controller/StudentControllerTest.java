package com.ahmed.StudentManagementSystem.controller;

import com.ahmed.StudentManagementSystem.dtos.StudentDto;
import com.ahmed.StudentManagementSystem.dtos.StudentResponseDto;
import com.ahmed.StudentManagementSystem.services.StudentService;
import tools.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StudentController.class)
class StudentControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private StudentService studentService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void createStudent_WithValidData_ShouldReturnCreated() throws Exception {
        StudentDto dto = new StudentDto("John", "Doe", 20, "john.doe@example.com");
        StudentResponseDto responseDto = new StudentResponseDto(1, "John", "Doe", 20, "john.doe@example.com");

        when(studentService.createStudent(any(StudentDto.class))).thenReturn(responseDto);

        mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.firstName").value("John"));
    }

    @Test
    void createStudent_WithInvalidData_ShouldReturnBadRequest() throws Exception {
        StudentDto dto = new StudentDto("", "Doe", 20, "not-an-email");

        mockMvc.perform(post("/api/v1/students")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(dto)))
                .andExpect(status().isBadRequest());
    }
}
