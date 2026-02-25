package com.ahmed.StudentManagementSystem.controller;

import com.ahmed.StudentManagementSystem.services.BooksService;
import com.ahmed.StudentManagementSystem.services.CoursesService;
import com.ahmed.StudentManagementSystem.services.StudentService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class ViewController {

    private final StudentService studentService;
    private final BooksService booksService;
    private final CoursesService coursesService;

    public ViewController(StudentService studentService, BooksService booksService, CoursesService coursesService) {
        this.studentService = studentService;
        this.booksService = booksService;
        this.coursesService = coursesService;
    }

    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("studentCount", studentService.getAllStudents().size());
        model.addAttribute("bookCount", booksService.getAllBooks().size());
        model.addAttribute("courseCount", coursesService.getAllCourses().size());
        return "index";
    }

    @GetMapping("/students-ui")
    public String students(Model model) {
        model.addAttribute("students", studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/books-ui")
    public String books(Model model) {
        model.addAttribute("books", booksService.getAllBooks());
        return "books";
    }

    @GetMapping("/courses-ui")
    public String courses(Model model) {
        model.addAttribute("courses", coursesService.getAllCourses());
        return "courses";
    }
}
