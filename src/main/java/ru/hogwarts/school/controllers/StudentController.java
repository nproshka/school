package ru.hogwarts.school.controllers;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import ru.hogwarts.school.model.Avatar;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.service.AvatarService;
import ru.hogwarts.school.service.StudentService;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

@RestController
@RequestMapping("students")
public class StudentController {

    private final StudentService studentService;
    private final AvatarService avatarService;

    public StudentController(StudentService studentService, AvatarService avatarService) {
        this.studentService = studentService;
        this.avatarService = avatarService;
    }

    @GetMapping
    public List<Student> findAllStudents() {
        return studentService.findAllStudents();
    }
    @GetMapping("/count")
    public Integer countAllStudents() {
        return studentService.countAllStudents();
    }
    @GetMapping("/avg")
    public Double getAverageAgeOfAllStudents() {
        return studentService.getAverageAgeOfAllStudents();
    }
    @GetMapping("/fiveLast")
    public List<Student> getFiveLastStudent() {
        return studentService.getFiveLastStudent();
    }

    @GetMapping("{id}")
    public ResponseEntity<Student> getStudentInfo (@PathVariable long id) {
        Student foundStudent = studentService.findStudent(id);
        if (foundStudent == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundStudent);
    }

    @GetMapping("/age/{age}")
    public List<Student> sortAgeStudent (@PathVariable int age) {
        return studentService.findStudentByAge(age);
    }
    @GetMapping("/age")
    public List<Student> sortAgeStudentBetween (@RequestParam int minAge, @RequestParam int maxAge) {
        return studentService.findAllStudentsBetween(minAge, maxAge);
    }
    @GetMapping("/findFaculty")
    public Faculty findFaculty (@RequestParam long id) {
        return studentService.findStudentFaculty(id);
    }

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
