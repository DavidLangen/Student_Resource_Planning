package com.david.Entity;

import com.fasterxml.jackson.annotation.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Past;
import java.util.Date;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * This class represents a student
 *
 * @author David Langen
 */
@Entity
@JsonIdentityInfo(generator = ObjectIdGenerators.IntSequenceGenerator.class)
@Table(name = "students")
public class Student {

    /**
     * The unique id for the student.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    /**
     * The student number of the student.
     */
    @Column(name = "student_number")
    private String studentNumber;

    /**
     * The first name of the student.
     */
    @NotBlank
    @Column(name = "first_name")
    private String firstName;

    /**
     * The last name of the student
     */
    @NotBlank
    @Column(name = "last_name")
    private String lastName;

    /**
     * The email address of the student
     */
    @NotBlank
    @Column(name = "mail")
    private String mail;

    /**
     * The phone number of tne student
     */
    @Column(name = "phone")
    private String phone;

    /**
     * The birthday of the student.
     */
    @Past
    @NotNull
    @DateTimeFormat(pattern = "yyyy-mm-dd", iso = DateTimeFormat.ISO.DATE)
    @Column(name = "date_of_birth")
    private Date dateOfBirth;

    /**
     * The address of the student
     */
    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "ADDRESS_ID")
    private Address address;

    /**
     * The courses taken by students.
     */
    @JsonManagedReference
    @ManyToMany(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinTable(name = "student_course", joinColumns = @JoinColumn(name = "student_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "course_id", referencedColumnName = "id"))
    private Set<Course> courses;

    /**
     * A default constructor
     */
    public Student() {
    }

    /**
     * A standard constructor filling all properties of a student object.
     *
     * @param studentNumber The student number of the student
     * @param firstName     The first name of the student
     * @param lastName      The last name of the student
     * @param mail          The email address of the student
     * @param phone         The phone number of the student
     * @param dateOfBirth   The brithday of the student
     * @param address       The address of the student
     */
    public Student(@NotBlank String studentNumber, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String mail, String phone, @NotBlank Date dateOfBirth, Address address) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        //this.courses = courses;
    }

    public Student(String studentNumber, @NotBlank String firstName, @NotBlank String lastName, @NotBlank String mail, String phone, @NotNull Date dateOfBirth, Address address, Set<Course> courses) {
        this.studentNumber = studentNumber;
        this.firstName = firstName;
        this.lastName = lastName;
        this.mail = mail;
        this.phone = phone;
        this.dateOfBirth = dateOfBirth;
        this.address = address;
        this.courses = courses;
    }

    /**
     * Gets the id of the student.
     *
     * @return The id of the student.
     */
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets the student number of the student.
     *
     * @return The student number of the student.
     */
    public String getStudentNumber() {
        return studentNumber;
    }

    /**
     * Sets the student number of the student.
     *
     * @param studentNumber The student number to be set.
     */
    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    /**
     * Gets the first name of the student.
     *
     * @return the first name of the student.
     */
    public String getFirstName() {
        return firstName;
    }

    /**
     * Sets the first name of the student.
     *
     * @param firstName the first name to be set.
     */
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    /**
     * Gets the last name of the student.
     *
     * @return the last name of the student.
     */
    public String getLastName() {
        return lastName;
    }

    /**
     * Sets the last name of the student.
     *
     * @param lastName the last name to be set.
     */
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    /**
     * Gets the email address of the student.
     *
     * @return the email address of the student.
     */
    public String getMail() {
        return mail;
    }

    /**
     * Sets the email address of the student.
     *
     * @param mail the email address to be set.
     */
    public void setMail(String mail) {
        this.mail = mail;
    }

    /**
     * Gets the phone number of the student.
     *
     * @return the phone number of the student.
     */
    public String getPhone() {
        return phone;
    }

    /**
     * Sets the phone number of the student.
     *
     * @param phone the phone number to be set.
     */
    public void setPhone(String phone) {
        this.phone = phone;
    }

    /**
     * Gets the birthday of the student.
     *
     * @return the birthday of the student.
     */
    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    /**
     * Sets the birthday of the student.
     *
     * @param dateOfBirth the birthday to be set.
     */
    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    /**
     * Gets the address of the student.
     *
     * @return the address of the student.
     */
    public Address getAddress() {
        return address;
    }

    /**
     * Sets the address of the student.
     *
     * @param address the address to be set.
     */
    public void setAddress(Address address) {
        this.address = address;
    }

    /**
     * Gets the courses taken by the student.
     *
     * @return the courses taken by the student
     */
    public Set<Course> getCourses() {
        return courses;
    }

    /**
     * Sets the courses taken by the student
     *
     * @param courses the courses to be set.
     */
    public void setCourses(Set<Course> courses) {
        this.courses = courses;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", studentNumber='" + studentNumber + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", mail='" + mail + '\'' +
                ", phone='" + phone + '\'' +
                ", dateOfBirth=" + dateOfBirth +
                ", address=" + address +
                ", courses=" + courses +
                '}';
    }
}
