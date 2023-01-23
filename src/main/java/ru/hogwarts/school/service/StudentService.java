package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.List;

@Service
public class StudentService {
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        return studentRepository.findById(id).get();
    }
    public Faculty findStudentFaculty(long id) {
        return studentRepository.getById(id).getFaculty();
    }

    public Student editStudent(Student student) {
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentByAge(int age) {
        return studentRepository.findByAge(age);
    }
    public List<Student> findAllStudents() {
        return studentRepository.findAll();
    }
    public List<Student> findAllStudentsBetween(int min, int max) {
        return studentRepository.findByAgeBetween(min, max);
    }

}
