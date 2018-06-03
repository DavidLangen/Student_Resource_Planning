package com.david.Controller;

import com.david.Entity.Course;
import com.david.Exceptions.ResourceNotFoundException;
import com.david.Repository.CourseRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * This controller handles incoming requests concerning the courses.
 * @author Marius Buerck
 */
@Controller
public class CourseController {

    /**
     * Determines the size of pages used in the pagination for the courses.
     */
    private static final int PAGE_SIZE = 10;

    @Autowired
    private CourseRepo courseRepo;

    /**
     * This controller method handles get-requests to "/courses".
     * It responds with an index view of the courses.
     * @param model The model used in Thymeleaf templating.
     * @param page Page number of the currently displayed page.
     * @return The "courses"-view.
     */
    @GetMapping("/courses")
    public String courses(Model model, @RequestParam(defaultValue = "0") int page) {
        // populate the model used for Thymeleaf templating
        model.addAttribute("courses", courseRepo.findAll(PageRequest.of(page,PAGE_SIZE)));
        model.addAttribute("currentPage", page);
        return "courses";
    }

    /**
     * This controller method handles post-requests to "/courses/create".
     * It is responsible for creating courses. It responds with a redirect to
     * "/courses"
     * @param name The name of the course to be created.
     * @param lecturer The name of the lecturer the course is held by.
     * @param description A description text of the course.
     * @return A redirect to "/courses".
     */
    @PostMapping(value = "/courses/create")
    public String createCourse(
            @RequestParam("name") String name,
            @RequestParam("lecturer") String lecturer,
            @RequestParam("description") String description) {
        // create a new course
        Course c = new Course(name, lecturer, description);
        // persist the new course
        courseRepo.save(c);
        return "redirect:/courses";
    }

    /**
     * This controller methods handles post-requests to "/courses/update".
     * It is responsible for handling changes to courses.
     * It responds with a redirect to "/courses".
     * @param id The id of the course to be changed.
     * @param name The new name of the course.
     * @param lecturer The new name of the lecturer of the course.
     * @param description The new description of the course.
     * @return A redirect to "/courses".
     */
    @PostMapping(value = "/courses/update")
    public String updateCourse(
            @RequestParam("id") long id,
            @RequestParam("name") String name,
            @RequestParam("lecturer") String lecturer,
            @RequestParam("description") String description) {
        // try to get the course through the repo using its id
        Course c = courseRepo.findById(id).orElseThrow(()-> new ResourceNotFoundException("Course", "Id", id));
        // set the new attribute values
        c.setName(name);
        c.setLecturer(lecturer);
        c.setDescription(description);
        // persist the changes
        courseRepo.save(c);
        return "redirect:/courses";
    }

    /**
     * This controller method handles get-requests to "/courses/delete".
     * It is responsible for delete courses.
     * It redirects to "/courses".
     * @param id The id of the course to be deleted.
     * @return A redirect to "/courses".
     */
    @GetMapping("/courses/delete/")
    public String deleteCourse(@RequestParam("id") String id) {
        // try to get the course through the repo using its id
        Course c = courseRepo.findById(Long.parseLong(id)).orElseThrow(()-> new ResourceNotFoundException("Course", "Id", id));
        // delete the course
        courseRepo.delete(c);
        return "redirect:/courses";
    }

    /**
     * This controller method handles get-requests to "/courses/raw/".
     * It responds with a list courses.
     * @return Iterable<Course>
     */
    @GetMapping("/courses/raw")
    @ResponseBody
    public Iterable<Course> coursesRaw(){
        return courseRepo.findAll();
    }

    /**
     *  This controller method handles get-requests to "/courses/find/".
     *  It find course by id and return it.
     * @param id The id of the searched course
     * @return Course
     */
    @GetMapping("/courses/find/")
    @ResponseBody
    public Course findCourseById(long id){
        return courseRepo.findById(id).orElseThrow(() -> new ResourceNotFoundException("Course", "id", id));
    }
}
