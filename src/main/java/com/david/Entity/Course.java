package com.david.Entity;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;


@Entity
@Table(name = "courses")
public class Course {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank
    @Column(name = "name")
    private String name;

    @NotBlank
    @Column(name = "lecturer")
    private String lecturer;

    @NotBlank
    @Column(name = "description")
    private String description;

    @ManyToMany(mappedBy = "courses")
    private Set<Student> students;

    public Course(@NotBlank String name, @NotBlank String lecturer, @NotBlank String description) {
        this.name = name;
        this.lecturer = lecturer;
        this.description = description;
    }

    public Course() {}

    public long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLecturer() {
        return lecturer;
    }

    public void setLecturer(String lecturer) {
        this.lecturer= lecturer;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description= description;
    }

    public Set<Student> getStudents() {
        return students;
    }
}
