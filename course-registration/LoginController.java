package com.university.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    @PostMapping("/login")
    public String validateLogin(HttpServletRequest request, Model model) {
        String email = request.getParameter("email");
        String password = request.getParameter("password");

        List<Map<String, Object>> users = jdbcTemplate.queryForList(
                "SELECT * FROM students WHERE email=? AND password=?",
                email, password);

        if (!users.isEmpty()) {
            HttpSession session = request.getSession();
            session.setAttribute("studentId", users.get(0).get("student_id"));
            return "redirect:/courses";
        } else {
            model.addAttribute("error", "Invalid email or password");
            return "login";
        }
    }
}