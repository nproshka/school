package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class StudentService {

    Map<Long, Student> studentMap = new HashMap<>();
    private long lastId = 0;

    public Student createStudent(Student student) {
        student.setId(++lastId);
        studentMap.put(lastId, student);
        return student;
    }

    public Student findStudent(long id) {
        return studentMap.get(id);
    }

    public Student editStudent(Student student) {
        studentMap.put(student.getId(), student);
        return student;
    }

    public Student deleteStudent(long id) {
        return studentMap.remove(id);
    }

    public List<Student> findStudentFromAge (int age) {
        List<Student> studentList = new ArrayList<>();
        for (Map.Entry<Long, Student> entry : studentMap.entrySet()) {
            if (entry.getValue().getAge() == age) {
                studentList.add(entry.getValue());
            }
        }
        return studentList;
    }

}
