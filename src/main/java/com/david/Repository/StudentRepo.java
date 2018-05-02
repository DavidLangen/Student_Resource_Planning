package com.david.Repository;

import com.david.Entity.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface StudentRepo extends PagingAndSortingRepository<Student, Long> {

    Page<Student> findByNameStartingWithIgnoreCase(String name, Pageable pageable);
}
