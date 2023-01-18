package ru.hogwarts.school.service;

import org.springframework.stereotype.Service;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Faculty;
import ru.hogwarts.school.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class FacultyService {
    Map<Long, Faculty> facultyMap = new HashMap<>();
    private long lastId = 0;

    public Faculty createFaculty(Faculty faculty) {
        faculty.setId(++lastId);
        facultyMap.put(lastId, faculty);
        return faculty;
    }

    public Faculty findFaculty(long id) {
        return facultyMap.get(id);
    }

    public Faculty editFaculty(Faculty faculty) {
        facultyMap.put(faculty.getId(), faculty);
        return faculty;
    }

    public Faculty deleteFaculty(long id) {
        return facultyMap.remove(id);
    }

    public List<Faculty> findFacultyFromColor (String color) {
        List<Faculty> facultyList = new ArrayList<>();
        for (Map.Entry<Long, Faculty> entry : facultyMap.entrySet()) {
            if (entry.getValue().getColor().equals(color)) {
                facultyList.add(entry.getValue());
            }
        }
        return facultyList;
    }

}
