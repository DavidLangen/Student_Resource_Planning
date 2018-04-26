package com.david.Service;

import com.david.Dao.StudentRepo;
import com.david.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.Collection;

@Service
public class StudentService {

    @Autowired
    private StudentRepo studentRepo;

    public Collection<Student> getAllStudents(){
        return studentRepo.findAll();
    }

    public Student getStudentById(long id){
        return this.studentRepo.findById(id).orElseThrow(()-> new EntityNotFoundException());
    }
}
