package ru.hogwarts.school.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
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
    public ResponseEntity<Student> getStudentInfo (@PathVariable long id) {
        Student foundStudent = studentService.findStudent(id);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

//    @GetMapping("{age}")
//    public List<Student> sortAgeStudent (@PathVariable int age) {
//        return studentService.findStudentFromAge(age);
//    }

    @PostMapping
    public Student createStudent (@RequestBody Student student) {
        studentService.createStudent(student);
        return student;
    }

    @PutMapping
    public ResponseEntity<Student> editStudent (@RequestBody Student student) {
        Student foundStudent = studentService.editStudent(student);
        if (foundStudent == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteStudent (@PathVariable long id) {
       studentService.deleteStudent(id);
        return ResponseEntity.ok().build();
    }

}
