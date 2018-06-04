package com.david.Controller;

import com.david.Entity.Address;
import com.david.Entity.Student;
import com.david.Service.StudentService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Date;
import java.util.List;

/**
 * This controller handles incoming requests concerning the student.
 * @author David Langen
 */
@Controller
@ControllerAdvice
public class StudentController {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    /**
     * Wrapper class for the Student Repository
     */
    @Autowired
    private StudentService studentService;


    @PostMapping(value = "/students/create")
    public String createStudent(
            @RequestParam("studentNumberCreate") String studentNumber,
            @RequestParam("firstNameCreate") String firstName,
            @RequestParam("lastNameCreate") String lastName,
            @RequestParam("mailCreate") String mail,
            @RequestParam("phoneCreate") String phone,
            @RequestParam("dateOfBirthCreate") String birth,
            @RequestParam("ZipCreate") String zip,
            @RequestParam("TownCreate") String town,
            @RequestParam("StreetCreate") String street,
            @RequestParam("HouseNumberCreate") String houseNumber
            )
    {
        // create a new student
        String[] dateParts = birth.split("-");
        Address address = new Address(zip, town, street, houseNumber);
        Student s = new Student(studentNumber, firstName, lastName, mail, phone,
                new Date(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0])), address);

        // persist the new Student
        studentService.AddStudent(s);
        return "redirect:/";
    }

    /**
     * This controller method handles get-request to "/student/find/"
     * It responds with a parsed Student object.
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
     * @param s The parsed student object by spring
     * @param a The parsed adress object by spring
     * @param result The result of the Validation
     * @return A redirect to "/".
     */
    @PostMapping(value = "/student/update")
    public String updateStudent(@Valid Student s, @Valid Address a, BindingResult result){
        logger.info("Addresse"+a);
        s.setAddress(a);
        studentService.updateStudent(s);
        return "redirect:/";
    }

    /**
     * This controller method handles get-requests to "/".
     * It responds with an index view of students or a list of students searched by a searchterm.
     * @param model The model used in Thymeleaf templating.
     * @param search The searchterm (default is a empty string)
     * @param page The current page number
     * @return the "student"-view
     */
    @GetMapping("/")
    public String findStudents(Model model, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("students",studentService.findByName(search, page));
        model.addAttribute("currentPage", page);
        return "index";
    }

    /**
     * This controller method handles get-requests to "/students/delete".
     * It is responsible for delete students.
     * It redirects to "/courses".
     * @param ids The ids of the student(s) to be deleted.
     * @return A redirect to "/".
     */
    @PostMapping(value = "/students/delete")
    public String deleteStudents(@RequestParam(defaultValue = "",value="id") List<Integer> ids)
    {
        if(!ids.isEmpty()) {
            ids.stream().forEach(id -> studentService.deleteById(id));
        }
        return "redirect:/";
    }


}