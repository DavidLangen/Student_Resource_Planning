package com.david.Controller;

import com.david.Entity.Course;
import com.david.Entity.Student;
import com.david.Service.CourseService;
import com.david.Service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/courses")
    public String courses(Model model, @RequestParam(defaultValue = "0") int page) {
        model.addAttribute("courses", courseService.getAllCourses(page));
        model.addAttribute("currentPage", page);
        return "courses";
    }

    @PostMapping(value = "/courses/create")
    public String createCourse(
            @RequestParam("name") String name,
            @RequestParam("lecturer") String lecturer,
            @RequestParam("description") String description){
        Course c = new Course(name,lecturer,description);
        courseService.updateCourse(c);
        return "redirect:/courses";
    }

    @GetMapping("/courses/delete/")
    public String deleteCourse(Model model,@RequestParam("id") String id, @RequestParam(defaultValue = "0") int page)
    {
        courseService.deleteById(Integer.parseInt(id));
        return "redirect:/courses";
    }
}