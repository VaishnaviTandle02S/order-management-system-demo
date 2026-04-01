package com.eomps.controller;

import com.eomps.entity.Vendor;
import com.eomps.service.VendorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

@Controller
@RequestMapping("/vendor")
public class VendorController {

    private static final String VENDOR_LOGIN = "vendor-login";
    private static final String VENDOR_REGISTER = "vendor-register";

    private final VendorService vendorService;

    @Autowired
    public VendorController(VendorService vendorService) {
        this.vendorService = vendorService;
    }

    // 🔹 Show Vendor Login Page
    @GetMapping("/login")
    public String showLoginPage() {
        return VENDOR_LOGIN;
    }

    // 🔹 Show Vendor Register Page
    @GetMapping("/register")
    public String showRegisterPage() {
        return VENDOR_REGISTER;
    }

    // 🔹 Handle Registration
    @PostMapping("/register")
    public String registerVendor(@RequestParam String name,
                                 @RequestParam String email,
                                 @RequestParam String password,
                                 Model model) {

        try {
            Vendor vendor = new Vendor(name, email, password);
            vendorService.register(vendor);

            model.addAttribute("success", "Registration successful!");
            return VENDOR_LOGIN;

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return VENDOR_REGISTER;
        }
    }

    // 🔹 Handle Login
    @PostMapping("/login")
    public String loginVendor(@RequestParam String email,
                              @RequestParam String password,
                              HttpSession session,
                              Model model) {

        try {
            Vendor vendor = vendorService.login(email, password);

            session.setAttribute("loggedInVendor", vendor);

            return "redirect:/vendor/dashboard";

        } catch (Exception e) {
            model.addAttribute("error", e.getMessage());
            return VENDOR_LOGIN;
        }
    }

    // 🔹 Vendor Dashboard
    @GetMapping("/dashboard")
    public String dashboard(HttpSession session, Model model) {

        Vendor vendor = (Vendor) session.getAttribute("loggedInVendor");

        if (vendor == null) {
            return "redirect:/vendor/login";
        }

        model.addAttribute("vendorName", vendor.getName());

        return "vendor-dashboard";
    }

    // 🔹 Logout
    @GetMapping("/logout")
    public String logout(HttpSession session) {

        session.invalidate();

        return "redirect:/vendor/login";
    }
}