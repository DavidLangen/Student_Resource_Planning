package com.david.Service;

import com.david.Entity.Student;
import com.david.Exceptions.ResourceNotFoundException;
import com.david.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import javax.validation.constraints.Null;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    private static int PAGEROW_SIZE = 5;

    public Page<Student> getAllStudents(int page){
        return studentRepo.findAll(PageRequest.of(page, PAGEROW_SIZE));
    }

    public Student getStudentById(long id){
        return this.studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "Id", id));
    }

    public Page<Student> findByName(String name, int page) {
        Page<Student> res = studentRepo.findByNameStartingWithIgnoreCase(name, PageRequest.of(page, PAGEROW_SIZE));
        return res;
    }

    public void deleteById(long id){
            Student student = getStudentById(id);
            studentRepo.delete(student);
    }

    public void updateStudent(Student s) {
        studentRepo.save(s);
    }
}
