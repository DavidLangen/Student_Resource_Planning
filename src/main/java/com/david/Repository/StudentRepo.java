package com.david.Repository;

import com.david.Entity.Student;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {
    @Query(value = "SELECT s.id,s.name,s.course FROM students s WHERE LOWER(s.name) LIKE CONCAT('%',LOWER(?1),'%')", nativeQuery = true)
    List<Student> findByNameLike(String name);
}
