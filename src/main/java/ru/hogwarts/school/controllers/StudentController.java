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
    @GetMapping(value = "/{id}/avatar/preview")
    public ResponseEntity<byte[]> downloadAvatar (@PathVariable Long id) {
        Avatar avatar = avatarService.findAvatarByStudentId(id);

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType(avatar.getMediaType()));
        headers.setContentLength(avatar.getData().length);

        return ResponseEntity.status(HttpStatus.OK).headers(headers).body(avatar.getData());
    }
    @GetMapping(value = "/{id}/avatar/avatar")
    public void downloadAvatar (@PathVariable Long id, HttpServletResponse response) throws IOException {
        Avatar avatar = avatarService.findAvatarByStudentId(id);

        Path path = Path.of(avatar.getFilePath());

        try (InputStream is = Files.newInputStream(path);
             OutputStream os = response.getOutputStream();) {
            response.setContentType(avatar.getMediaType());
            response.setContentLength(Math.toIntExact(avatar.getFileSize()));
            is.transferTo(os);
        }
    }
    @GetMapping(value = "/avatars")
    public void downloadAvatars (Integer pageNumber, Integer pageSize) {
        avatarService.findALlAvatars(pageNumber, pageSize);
    }

    @PostMapping
    public Student createStudent (@RequestBody Student student) {
        studentService.createStudent(student);
        return student;
    }
    @PostMapping(value = "/{id}/avatar", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<String> uploadAvatar (@PathVariable Long id, @RequestParam MultipartFile avatarFile) throws IOException {
        if (avatarFile.getSize() > 1024 * 300) {
            return ResponseEntity.badRequest().body("File is too big");
        }

        avatarService.uploadAvatar(id, avatarFile);
        return ResponseEntity.ok().build();
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
