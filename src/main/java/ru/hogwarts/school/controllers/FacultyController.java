package ru.hogwarts.school.controllers;

import org.springframework.web.bind.annotation.*;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.service.FacultyService;

import java.util.List;

@RestController
@RequestMapping("faculty")
public class FacultyController {

    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @GetMapping("{id}")
    public Faculty getFacultyInfo (@PathVariable long id) {
        return facultyService.findFaculty(id);
    }
    @GetMapping("{color}")
    public List<Faculty> sortColorStudent (@PathVariable String color) {
        return facultyService.findFacultyFromColor(color);
    }

    @PostMapping
    public Faculty createFaculty (@RequestBody Faculty faculty) {
        facultyService.createFaculty(faculty);
        return faculty;
    }

    @PutMapping
    public Faculty editFaculty (@RequestBody Faculty faculty) {
        facultyService.editFaculty(faculty);
        return faculty;
    }

    @DeleteMapping("{id}")
    public Faculty deleteFaculty (@PathVariable long id) {
        return facultyService.deleteFaculty(id);
    }
}
