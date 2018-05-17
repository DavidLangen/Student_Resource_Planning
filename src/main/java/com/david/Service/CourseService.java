package com.david.Service;

import com.david.Entity.Course;
import com.david.Entity.Student;
import com.david.Exceptions.ResourceNotFoundException;
import com.david.Repository.CourseRepo;
import com.david.Repository.StudentRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

@Service
public class CourseService {

    @Autowired
    private CourseRepo courseRepo;

    private static int PAGEROW_SIZE = 5;

    public Page<Course> getAllCourses(int page){
        return courseRepo.findAll(PageRequest.of(page, PAGEROW_SIZE));
    }

    public Course getCourseById(long id){
        return this.courseRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course", "Id", id));
    }

    public void deleteById(long id){
        Course course = getCourseById(id);
        courseRepo.delete(course);
    }

    public void updateCourse(Course c) {
        courseRepo.save(c);
    }
}
