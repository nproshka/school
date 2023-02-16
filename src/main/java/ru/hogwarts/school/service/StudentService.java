package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.StudentRepository;

import java.util.*;

@Service
public class StudentService {

    Logger logger = LoggerFactory.getLogger(StudentService.class);
    private final StudentRepository studentRepository;
    public StudentService(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    public Student createStudent(Student student) {
        logger.debug("Was invoked method for create student");
        return studentRepository.save(student);
    }

    public Student findStudent(long id) {
        logger.debug("Was invoked method for find student by id");
        return studentRepository.findById(id).get();
    }
    public Faculty findStudentFaculty(long id) {
        logger.debug("Was invoked method for student faculty by id");
        return studentRepository.findById(id).get().getFaculty();
    }

    public Student editStudent(Student student) {
        logger.debug("Was invoked method for edit student");
        return studentRepository.save(student);
    }

    public void deleteStudent(long id) {
        logger.debug("Was invoked method for delete student");
        studentRepository.deleteById(id);
    }

    public List<Student> findStudentByAge(int age) {
        logger.debug("Was invoked method for find student by age");
        return studentRepository.findByAge(age);
    }
    public List<Student> findAllStudents() {
        logger.debug("Was invoked method for get all students");
        return studentRepository.findAll();
    }
    public List<Student> findAllStudentsBetween(int min, int max) {
        logger.debug("Was invoked method for find student by age between {} and {}", min, max);
        return studentRepository.findByAgeBetween(min, max);
    }

    public Integer countAllStudents() {
        logger.debug("Was invoked method for count all students");
        return studentRepository.countAll();
    }
    public Double getAverageAgeOfAllStudents() {
        logger.debug("Was invoked method for get average age all students");
        return studentRepository.getAverageAgeOfAllStudents();
    }

    public List<Student> getFiveLastStudent() {
        logger.debug("Was invoked method for get last five student");
        return studentRepository.getFiveLastStudent();
    }
    public List<Student> getStudentsWithFirstLetterA() {
        logger.debug("Was invoked method for getStudentsWithFirstLetterA");
        List<Student> filteredStudent = studentRepository.findAll()
                .stream()
                .filter(f -> f.getName().toLowerCase().toUpperCase().startsWith("А"))
                .toList();
        return filteredStudent;
    }
    public Integer[] getAverageStudentAgeByStream() {


        return studentRepository.getAgeList();
    }


}
