package com.david.Dao;

import com.david.Entity.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Repository
public class StudentDao {

    private static Map<Integer, Student> students;

    static {
        students = new HashMap<Integer, Student>(){
            {
                put(1, new Student(1,"Peter", "Angewandte Informatik"));
                put(2, new Student(2,"Alex", "Angewandte Informatik"));
                put(3, new Student(3,"Anna", "Angewandte Informatik"));
            }
        };

    }

    public Collection<Student> getAllStudents(){
        return this.students.values();
    }

    public Student getStudentById(int id){
        return this.students.get(id);
    }
}


