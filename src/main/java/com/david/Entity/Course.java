package com.david.Entity;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.util.Set;

/**
 * This class represents a course.
 * @author Marius Buerck
 */
@Entity
@Table(name = "courses")
public class Course {

    /**
     * The unique id for this course.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The name of this course.
     */
    @NotBlank
    @Column(name = "name")
    private String name;

    /**
     * The name of the lecturer this course is held by.
     */
    @NotBlank
    @Column(name = "lecturer")
    private String lecturer;

    /**
     * The description of this course
     */
    @NotBlank
    @Column(name = "description")
    private String description;

    /**
     * The students taking this course.
     */
    @ManyToMany(fetch = FetchType.LAZY,
            cascade =
                    {
                            CascadeType.DETACH,
                            CascadeType.MERGE,
                            CascadeType.REFRESH,
                            CascadeType.PERSIST
                    },
            targetEntity = Course.class)
    @JsonIgnore
    @JoinTable(name = "student_course",
            inverseJoinColumns = @JoinColumn(name = "course_id", nullable = false, updatable = false),
            joinColumns = @JoinColumn(name = "student_id", nullable = false, updatable = false),
            foreignKey = @ForeignKey(ConstraintMode.CONSTRAINT),
            inverseForeignKey = @ForeignKey(ConstraintMode.CONSTRAINT))
    private Set<Student> students;

    /**
     * A standard constructor filling all properties of a course object.
     *
     * @param name        The name of this course.
     * @param lecturer    The lecturer this course is held by.
     * @param description The description of this course.
     */
    public Course(@NotBlank String name, @NotBlank String lecturer, @NotBlank String description) {
        this.name = name;
        this.lecturer = lecturer;
        this.description = description;
    }

    /**
     * A default constructor.
     */
    public Course() {
    }

    /**
     * Gets the id of this course.
     *
     * @return The id of this course.
     */
    public long getId() {
        return id;
    }

    /**
     * Sets the id of this course.
     * @param id The id to be set on this course.
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the name of this course.
     *
     * @return The name of this course.
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of this course.
     *
     * @param name The name to be set.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Gets the name of the lecturer this course is held by.
     *
     * @return The name of the lecturer this course is held by.
     */
    public String getLecturer() {
        return lecturer;
    }

    /**
     * Sets the name of the lecturer this course is held by.
     *
     * @param lecturer The name of lecturer to be set.
     */
    public void setLecturer(String lecturer) {
        this.lecturer = lecturer;
    }

    /**
     * Gets the description of this course.
     *
     * @return The description of this course.
     */
    public String getDescription() {
        return description;
    }

    /**
     * Sets the description of this course.
     *
     * @param description The description to be set.
     */
    public void setDescription(String description) {
        this.description = description;
    }

    /**
     * Gets all students associated to this course.
     *
     * @return A Set of Students.
     */
    public Set<Student> getStudents() {
        return students;
    }

    /**
     * Sets all students associated to this course.
     *
     * @param students The Set of Students this Course is associated to.
     */
    public void setStudents(Set<Student> students) {
        this.students = students;
    }
}
