package com.david.Controller;

import com.david.Dao.StudentRepo;
import com.david.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class StudentController {

    @Autowired
    private StudentService studentService;

    @GetMapping("/students")
    public String students(Model model) {
        model.addAttribute("students",studentService.getAllStudents());
        return "students";
    }

    @GetMapping("/students/{id}")
    public String student(@PathVariable(value = "id") long id, Model model) {
        model.addAttribute("student",studentService.getStudentById(id));
        return "students";
    }

    @GetMapping("/")
    public String findStudents(Model model, @RequestParam(defaultValue = "") String search) {
        model.addAttribute("students",studentService.findByName(search));
        return "index";
    }

}