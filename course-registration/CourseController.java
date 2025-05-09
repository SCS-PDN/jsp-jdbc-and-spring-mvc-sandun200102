package com.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

@Controller
public class CourseController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/courses")
    public String showCourses(Model model) {
        List<Map<String, Object>> courses = jdbcTemplate.queryForList("SELECT * FROM courses");
        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId, HttpServletRequest request) {
        int studentId = (int) request.getSession().getAttribute("studentId");

        int count = jdbcTemplate.queryForObject(
                "SELECT COUNT(*) FROM registrations WHERE student_id=? AND course_id=?",
                Integer.class, studentId, courseId);

        if (count == 0) {
            jdbcTemplate.update("INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, CURDATE())",
                    studentId, courseId);
        }

        return "success";
    }
}
