package com.david.Service;

import com.david.Entity.Student;
import com.david.Exceptions.ResourceNotFoundException;
import com.david.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

/**
 * This service is a Wrapper-Class for the Student Repository.
 * It´s wrapped Repository Methods and do some extra actions.
 * @author David Langen
 */
@Service
public class StudentService {

    /**
     * The Student Repository, which is strongly linked with the database
     */
    @Autowired
    private StudentRepo studentRepo;

    /**
     * Determines the size of pages used in the pagination for the student.
     */
    private static int PAGEROW_SIZE = 5;

    /**
     * This service method returns all Student in the database,
     * wrapped with the page class.
     * @param page the current page number
     * @return sorted Student list of the current page
     */
    public Page<Student> getAllStudents(int page){
        return studentRepo.findAll(PageRequest.of(page, PAGEROW_SIZE));
    }

    /**
     * This service method find a Student by his id or throws an exception.
     * @param id of the searched student
     * @throws ResourceNotFoundException
     * @return searched Student object
     */
    public Student getStudentById(long id){
        return this.studentRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Student", "Id", id));
    }

    /**
     * This service method save a student in the database.
     * @param newStudent The student to be saved
     */
    public void AddStudent(Student newStudent)
    {
        studentRepo.save(newStudent);
    }

    /**
     * This service method find a student
     * with his Username and wrapped in the page class.
     * @param name the username of the searched student
     * @param page the current page number
     * @return sorted student list of the current page
     */
    public Page<Student> findByName(String name, int page) {
        Page<Student> res = studentRepo.findByFirstNameStartingWithIgnoreCaseOrLastNameStartingWithIgnoreCase(name, name, PageRequest.of(page, PAGEROW_SIZE));
        return res;
    }

    /**
     * This service method delete a student from the database with the id.
     * @param id The id of the deleted student
     */
    public void deleteById(long id){
            Student student = getStudentById(id);
            studentRepo.delete(student);
    }

    /**
     * This service method update a student
     * @param s the updated student
     */
    public void updateStudent(Student s) {
        studentRepo.save(s);
    }
}
