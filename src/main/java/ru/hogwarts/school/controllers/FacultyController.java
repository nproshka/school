package ru.hogwarts.school.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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

    @GetMapping
    public List<Faculty> findAllFaculty () {
        return facultyService.findAllFaculty();
    }
    @GetMapping("{id}")
    public ResponseEntity<Faculty> getFacultyInfo (@PathVariable long id) {
        Faculty foundFaculty = facultyService.findFaculty(id);
        if (foundFaculty == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @GetMapping("/color/{color}")
    public List<Faculty> sortColorStudent (@PathVariable String color) {
        return facultyService.findFacultyByColor(color);
    }

    @PostMapping
    public Faculty createFaculty (@RequestBody Faculty faculty) {
        facultyService.createFaculty(faculty);
        return faculty;
    }

    @PutMapping
    public ResponseEntity<Faculty> editFaculty (@RequestBody Faculty faculty) {
        Faculty foundFaculty = facultyService.editFaculty(faculty);
        if (foundFaculty == null) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).build();
        }
        return ResponseEntity.ok(foundFaculty);
    }

    @DeleteMapping("{id}")
    public ResponseEntity deleteFaculty (@PathVariable long id) {
       facultyService.deleteFaculty(id);
        return ResponseEntity.ok().build();
    }
}
