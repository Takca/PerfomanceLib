package org.pflb.vault.service;

import org.pflb.vault.model.Student;
import org.pflb.vault.repository.StudentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentPersistentStorage {
    @Autowired
    StudentRepository studentRepository;

    public void saveStudent(Student student) {
        studentRepository.save(student);
    }

    public List<Student> getAll() {
       return studentRepository.findAll();
    }

    public Student getStudentById(Long id) {
        return studentRepository.findById(id).get();
    }

    public void deleteStudentById(Long id) {
        studentRepository.deleteById(id);
    }
}
