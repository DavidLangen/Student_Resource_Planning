package com.david.Service;

import com.david.Entity.Student;
import com.david.Exceptions.ResourceNotFoundException;
import com.david.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Collection<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentById(long id){
        return this.studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "Id", id));
    }

    public List<Student> findByName(String name) {
        List<Student> res = studentRepo.findByNameLike(name);
        return res;
    }

    public void deleteById(long id){
        Student student = getStudentById(id);
        studentRepo.delete(student);
    }
}
