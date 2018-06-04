package com.david.Controller;

import com.david.Entity.Course;
import com.david.Exceptions.ResourceNotFoundException;
import com.david.Repository.CourseRepo;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.validation.Valid;

/**
 * This controller handles incoming requests concerning the courses.
 *
 * @author Marius Buerck
 */
@Controller
public class CourseController {

    /**
     * Determines the size of pages used in the pagination for the courses.
     */
    private static final int PAGE_SIZE = 10;

    private String formFieldError = "";
	private String errorMsg = "";

	@Autowired
    private CourseRepo courseRepo;

    /**
     * This controller method handles get-requests to "/courses".
     * It responds with an index view of the courses.
     *
     * @param model The model used in Thymeleaf templating.
     * @param page  Page number of the currently displayed page.
     * @return The "courses"-view.
     */
    @GetMapping("/courses")
    public String courses(Model model, @RequestParam(defaultValue = "0") int page) {
        // populate the model used for Thymeleaf templating
        model.addAttribute("courses", courseRepo.findAll(PageRequest.of(page, PAGE_SIZE)));
        model.addAttribute("currentPage", page);
        if (formFieldError != "") {
            model.addAttribute("formFieldError", formFieldError);
            formFieldError = "";
        }
        if (errorMsg != "") {
            model.addAttribute("errorMsg", errorMsg);
            errorMsg = "";
        }
        return "courses";
    }

    /**
     * This controller method handles post-requests to "/courses/create".
     * It is responsible for creating courses. It responds with a redirect to
     * "/courses"
     *
     * @param course The course to be created.
     * @param result The BindingResult used for error checking.
     * @return A redirect to "/courses".
     */
    @PostMapping(value = "/courses/create")
    public String createCourse(@Valid Course course, BindingResult result){
        if (result.hasErrors()) {
            formFieldError = "Bitte füllen Sie alle Felder aus.";
            return "redirect:/courses";
        }

        // persist the new course
        courseRepo.save(course);
        return "redirect:/courses";
    }

    /**
     * This controller methods handles post-requests to "/courses/update".
     * It is responsible for handling changes to courses.
     * It responds with a redirect to "/courses".
     *
     * @param course The course to be changed.
     * @param result The BindingResult used for error checking.
     * @return A redirect to "/courses".
     */
    @PostMapping(value = "/courses/update")
    public String updateCourse(@Valid Course course, BindingResult result) {
        if (result.hasErrors()) {
            formFieldError = "Bitte füllen Sie alle Felder aus.";
            return "redirect:/courses";
        }

        // persist the changes
        courseRepo.save(course);
        return "redirect:/courses";
    }

    /**
     * This controller method handles get-requests to "/courses/delete".
     * It is responsible for delete courses.
     * It redirects to "/courses".
     *
     * @param id The id of the course to be deleted.
     * @return A redirect to "/courses".
     */
    @GetMapping("/courses/delete/")
    public String deleteCourse(@RequestParam("id") String id,Model model, @RequestParam(defaultValue = "0") int page) {
        // try to get the course through the repo using its id
        Course c = courseRepo.findById(Long.parseLong(id)).orElseThrow(() -> new ResourceNotFoundException("Course", "Id", id));
        // delete the course
        try
        {
            courseRepo.delete(c);
        } catch (Exception e){
            errorMsg = "Es existieren noch Studenten in diesem Kurs!";
        }
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
