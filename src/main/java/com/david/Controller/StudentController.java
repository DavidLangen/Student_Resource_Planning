package com.david.Controller;

import com.david.Entity.Student;
import com.david.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.util.List;

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

    @GetMapping("/findStudentById")
    @ResponseBody
    public Student student(long id) {
        return studentService.getStudentById(id);
    }

    @PostMapping(value = "/updateStudent")
    public String updateStudent(@RequestParam Student s){
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