package com.david.Controller;

import com.david.Entity.Address;
import com.david.Entity.Course;
import com.david.Entity.Student;
import com.david.Exceptions.ResourceNotFoundException;
import com.david.Repository.CourseRepo;
import com.david.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.*;
import java.util.stream.Collectors;

/**
 * This controller handles incoming requests concerning the student.
 *
 * @author David Langen
 */
@Controller
@ControllerAdvice
public class StudentController {

    /**
     * A logger used to print messages to the servers output.
     */
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Wrapper class for the Student Repository
     */
    @Autowired
    private StudentService studentService;

    /**
     * A repository used to access the courses on the database.
     */
    @Autowired
    private CourseRepo courseRepo;

    /**
     * Id of the student having a validation error.
     */
    private long validationErrId;

    /**
     * The message to be displayed in case of validation errors in the forms.
     */
    private String formFieldError;

    /**
     * This controller method handles requests to "/students/create". Therefore it handles student object creation.
     *
     * @param s         The Student object to be persisted.
     * @param resultS   The BindingResults used for validation of the Student object.
     * @param a         The Address object attached to the Student to be persisted.
     * @param resultA   The BindingResult used for Validation of the Address object.
     * @param courseids The courses associated to the given Student object.
     * @return A redirect to "/", which shows the list of all students.
     */
    @PostMapping(value = "/students/create")
    public String createStudent(
            @Valid Student s,
            BindingResult resultS,
            @Valid Address a,
            BindingResult resultA,
            @RequestParam(value = "courseids[]", defaultValue = "") List<Long> courseids
    ) {
        if (resultS.hasErrors() || resultA.hasErrors()) {
            if (resultS.hasFieldErrors("mail")) {
                formFieldError = "Das Emailfeld darf nicht leer sein.";
            }
            if (resultS.hasFieldErrors("dateOfBirth")) {
                formFieldError = "Der Geburtstag muss in der Vergangenheit liegen und nicht leer sein.";
            }
            if (resultS.hasFieldErrors("firstName")) {
                formFieldError = "Bitte geben Sie einen Vornamen an.";
            }
            if (resultS.hasFieldErrors("lastName")) {
                formFieldError = "Bitte geben Sie einen Nachnamen an.";
            }
            return "redirect:/";
        }

        Set<Course> courses = courseids.stream().map(id -> courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("course", "id", id))).collect(Collectors.toSet());
        s.setCourses(courses);
        s.setAddress(a);
        // persist the new Student
        studentService.AddStudent(s);
        return "redirect:/";
    }

    /**
     * This controller method handles get-request to "/student/find/"
     * It responds with a parsed Student object.
     *
     * @param id The id of the searched student
     * @return Student
     */
    @GetMapping("/student/find/")
    @ResponseBody
    public Student student(long id) {
        return studentService.getStudentById(id);
    }


    /**
     * This controller method handles post-requests to "/student/update/"
     *
     * @param s         The parsed student object by spring
     * @param a         The parsed address object by spring
     * @param resultS   The result of the Validation
     * @param resultA   The result of the Validation
     * @param courseids The ids of the courses the given student is associated to.
     * @return A redirect to "/".
     */
    @PostMapping(value = "/student/update")
    public String updateStudent(
            @Valid Student s,
            BindingResult resultS,
            @Valid Address a,
            BindingResult resultA,
            @RequestParam(value = "courseids[]", defaultValue = "") List<Long> courseids
    ) {

        if (resultS.hasErrors() || resultA.hasErrors()) {
            validationErrId = s.getId();
            return "redirect:/#updateModal";
        }

        logger.info("Addresse" + a);
        logger.info("Student" + s);
        s.setAddress(a);
        Set<Course> courses = courseids.stream().map(id -> courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("course", "id", id))).collect(Collectors.toSet());
        s.setCourses(courses);
        studentService.updateStudent(s);
        return "redirect:/";
    }

    /**
     * This controller method handles get-requests to "/".
     * It responds with an index view of students or a list of students searched by a searchterm.
     *
     * @param model  The model used in Thymeleaf templating.
     * @param search The searchterm (default is a empty string)
     * @param page   The current page number
     * @return the "student"-view
     */
    @GetMapping("/")
    public String findStudents(Model model, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("students", studentService.findByName(search, page));
        model.addAttribute("currentPage", page);
        model.addAttribute("validationErrId", validationErrId);
        if (validationErrId != 0) {
            validationErrId = 0;
        }
        if (formFieldError != "") {
            model.addAttribute("formFieldError", formFieldError);
            formFieldError = "";
        }
        return "index";
    }

    /**
     * This controller method handles get-requests to "/students/delete".
     * It is responsible for delete students.
     * It redirects to "/courses".
     *
     * @param ids The ids of the student(s) to be deleted.
     * @return A redirect to "/".
     */
    @PostMapping(value = "/students/delete")
    public String deleteStudents(@RequestParam(defaultValue = "", value = "id") List<Integer> ids) {
        if (!ids.isEmpty()) {
            ids.stream().forEach(id -> studentService.deleteById(id));
        }
        return "redirect:/";
    }
}
