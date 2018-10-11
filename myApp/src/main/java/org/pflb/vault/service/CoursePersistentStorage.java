package org.pflb.vault.service;

import org.pflb.vault.model.Course;
import org.pflb.vault.model.Creature;
import org.pflb.vault.repository.CourseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CoursePersistentStorage {

    @Autowired
    CourseRepository courseRepository;

    public void saveCourse(Course course) {
        courseRepository.save(course);
    }

    public Course getCourseById(Long id) {
        return courseRepository.getOne(id);
    }

    public List<Course> getAll() { return courseRepository.findAll(); }
}
