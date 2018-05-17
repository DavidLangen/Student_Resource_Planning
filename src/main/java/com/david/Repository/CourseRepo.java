package com.david.Repository;

import com.david.Entity.Course;
import com.david.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface CourseRepo extends PagingAndSortingRepository<Course, Long> {
}
