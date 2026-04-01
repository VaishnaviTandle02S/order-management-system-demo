package com.eomps.controller;

import com.eomps.entity.User;
import com.eomps.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/")
public class UserController {

    private static final Logger logger = LoggerFactory.getLogger(UserController.class);

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    // 🔹 Show Login Page (ROOT)
    @GetMapping
    public String root() {
        return "login";
    }

    // 🔹 Show Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return "login";
    }

    // 🔹 Show Register Page
    @GetMapping("/register")
    public String showRegisterPage() {
        return "register";
    }

    // 🔹 Handle Registration
    @PostMapping("/register")
    public String registerUser(@RequestParam String username,
                               @RequestParam String password,
                               Model model) {

        try {
            User user = new User(username, password, "USER");
            userService.register(user);

            model.addAttribute("success", "Registration successful! Please login.");
            return "login";

        } catch (Exception e) {
            logger.error("Registration error for user: {}", username, e);
            model.addAttribute("error", e.getMessage());
            return "register";
        }
    }

    // 🔹 Handle Login
    @PostMapping("/login")
    public String loginUser(@RequestParam String username,
                            @RequestParam String password,
                            HttpSession session,
                            Model model) {

        try {
            User user = userService.login(username, password);

            session.setAttribute("loggedInUser", user); // ✅ better naming

            if ("ADMIN".equalsIgnoreCase(user.getRole())) {
                return "redirect:/admin";
            } else {
                return "redirect:/home";
            }

        } catch (Exception e) {
            logger.warn("Login failed for user: {}", username);
            model.addAttribute("error", e.getMessage());
            return "login";
        }
    }

    // 🔹 Home Page
    @GetMapping("/home")
    public String home(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        model.addAttribute("username", user.getUsername());

        return "home";
    }

    // 🔹 Admin Page
    @GetMapping("/admin")
    public String adminPage(HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        if (!"ADMIN".equalsIgnoreCase(user.getRole())) {
            return "redirect:/home";
        }

        return "admin";
    }

    // 🔹 Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/login";
    }
}