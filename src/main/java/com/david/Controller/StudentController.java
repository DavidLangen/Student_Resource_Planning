package com.david.Controller;

import com.david.Entity.Address;
import com.david.Entity.Course;
import com.david.Entity.Student;
import com.david.Service.StudentService;
import com.david.Service.UserDetailsServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.List;
import java.util.Set;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String students(Model model,@RequestParam(defaultValue = "0") int page) {
        model.addAttribute("students",studentService.getAllStudents(page));
        model.addAttribute("currentPage", page);
        return "students";
    }

    @PostMapping(value = "/students/create")
    public String createCStudent(
            @RequestParam("studentNumberCreate") String studentNumber,
            @RequestParam("firstNameCreate") String firstName,
            @RequestParam("lastNameCreate") String lastName,
            @RequestParam("mailCreate") String mail,
            @RequestParam("phoneCreate") String phone,
            @RequestParam("dateOfBirthCreate") String birth,
            @RequestParam("ZipCreate") String zip,
            @RequestParam("TownCreate") String town,
            @RequestParam("StreetCreate") String street,
            @RequestParam("HouseNumberCreate") String houseNumber,
            @RequestParam("coursesCreate") String courses
            )
    {
        // create a new student
        String[] dateParts = birth.split(".");
        Set<Course> coursesSet = null;
        Address address = new Address(zip, town, street, houseNumber);
        Student s = new Student(studentNumber, firstName, lastName, mail, phone,
                new Date(Integer.parseInt(dateParts[2]), Integer.parseInt(dateParts[1]), Integer.parseInt(dateParts[0])), address, coursesSet);

        // persist the new Student
        studentService.AddStudent(s);
        return "redirect:/index";
    }

    @GetMapping("/findStudentById")
    @ResponseBody
    public Student student(long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping(value = "/updateStudent")
    public String updateStudent(@ModelAttribute("student") Student s){
        studentService.updateStudent(s);
        return "redirect:/";
    }

    @GetMapping("/")
    public String findStudents(Model model, @RequestParam(defaultValue = "") String search, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("students",studentService.findByName(search, page));
        model.addAttribute("currentPage", page);
        return "index";
    }

    @PostMapping(value = "/")
    public String deleteStudents(Model model,@RequestParam(defaultValue = "",value="id") List<Integer> ids,@RequestParam(defaultValue = "0") int page)
    {
        if(!ids.isEmpty()) {
            ids.stream().forEach(id -> studentService.deleteById(id));
        }
        model.addAttribute("students", studentService.getAllStudents(page));
        return "index";
    }


}