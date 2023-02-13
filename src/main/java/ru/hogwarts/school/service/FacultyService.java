package ru.hogwarts.school.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;
import ru.hogwarts.school.repository.FacultyRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;


@Service
public class FacultyService {

    Logger logger = LoggerFactory.getLogger(FacultyService.class);

    private final FacultyRepository facultyRepository;


    public FacultyService(FacultyRepository facultyRepository) {
        this.facultyRepository = facultyRepository;
    }

    public Faculty createFaculty(Faculty faculty) {
        logger.debug("Was invoke method for create faculty");
        return facultyRepository.save(faculty);
    }

    public Faculty findFaculty(long id) {
        logger.debug("Was invoke method for find faculty by id = {}", id);
        return facultyRepository.findById(id).get();
    }
    public Collection<Student> findAllStudentsOnFaculty(long id) {
        logger.debug("Was invoke method for find all students of faculty by id = {}", id);
        return facultyRepository.findById(id).get().getStudents();
    }

    public Faculty editFaculty(Faculty faculty) {
        logger.debug("Was invoke method for edit faculty");
        return facultyRepository.save(faculty);
    }

    public void deleteFaculty(long id) {
        logger.debug("Was invoke method for delete faculty by id = {}", id);
        facultyRepository.deleteById(id);
    }

    public List<Faculty> findFacultyByColor (String color) {
        logger.debug("Was invoke method for find faculty by color = {}", color);
        return facultyRepository.findByColor(color);
    }
    public List<Faculty> findAllFaculty() {
        logger.debug("Was invoke method for get all faculty");
        return facultyRepository.findAll();
    }
    public List<Faculty> findFacultyByColorOrName(String color, String name) {
        logger.debug("Was invoke method for find faculty by color or name");
        return facultyRepository.findByColorIgnoreCaseOrNameIgnoreCase(color, name);
    }

}
