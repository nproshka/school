package ru.hogwarts.school.controllers;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.StudentService;

import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @GetMapping
    public String hello() {
        return "Hello world";
    }

    @GetMapping("{id}")
    public Student getStudentInfo (@PathVariable long id) {
        return studentService.findStudent(id);
    }
    @GetMapping("{age}")
    public List<Student> sortAgeStudent (@PathVariable int age) {
        return studentService.findStudentFromAge(age);
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student) {
        studentService.createStudent(student);
        return student;
    }

    @PutMapping
    public Student editStudent (@RequestBody Student student) {
        studentService.editStudent(student);
        return student;
    }

    @DeleteMapping("{id}")
    public Student deleteStudent (@PathVariable long id) {
        return studentService.deleteStudent(id);
    }

}
