package com.eomps.controller;

import com.eomps.entity.Product;
import com.eomps.entity.User;
import com.eomps.service.ProductService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Controller
@RequestMapping("/products")
public class ProductController {

    private static final Logger logger = LoggerFactory.getLogger(ProductController.class);

    private final ProductService productService;

    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }

    // 🔹 Helper methods
    private User getLoggedInUser(HttpSession session) {
        return (User) session.getAttribute("loggedInUser");
    }

    private boolean isVendorSession(HttpSession session) {
        return session.getAttribute("loggedInVendor") != null;
    }

    private boolean hasProductAccess(HttpSession session) {
        User user = getLoggedInUser(session);
        if (isVendorSession(session)) return true;
        return user != null && ("ADMIN".equalsIgnoreCase(user.getRole()));
    }

    private boolean isAuthenticated(HttpSession session) {
        return getLoggedInUser(session) != null || isVendorSession(session);
    }

    private String loginRedirect(HttpSession session) {
        return isVendorSession(session) ? "redirect:/vendor/login" : "redirect:/login";
    }

    // 🔹 Show Add Product Page
    @GetMapping("/add")
    public String showAddProductPage(HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/login";
        if (!hasProductAccess(session)) return "redirect:/products/list";
        return "add-product";
    }

    // 🔹 Add Product
    @PostMapping("/add")
    public String addProduct(@RequestParam String name,
                             @RequestParam String description,
                             @RequestParam double price,
                             Model model,
                             HttpSession session) {

        if (!isAuthenticated(session)) return "redirect:/login";
        if (!hasProductAccess(session)) return "redirect:/products/list";

        try {
            productService.addProduct(new Product(name, description, price));
            return isVendorSession(session)
                    ? "redirect:/vendor/dashboard"
                    : "redirect:/products/list";
        } catch (Exception e) {
            logger.error("Error adding product", e);
            model.addAttribute("error", e.getMessage());
            return "add-product";
        }
    }

    // 🔹 Product List
    @GetMapping("/list")
    public String listProducts(Model model, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/login";

        User user = getLoggedInUser(session);
        model.addAttribute("products", productService.getAllProducts());
        if (user != null) model.addAttribute("loggedInUser", user);

        return "product-list";
    }

    // 🔹 Delete Product
    @GetMapping("/delete/{id}")
    public String deleteProduct(@PathVariable Long id, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/login";
        if (!hasProductAccess(session)) return "redirect:/products/list";

        try {
            productService.deleteProduct(id);
        } catch (Exception e) {
            logger.error("Error deleting product: {}", id, e);
            return "redirect:/products/list?error=true";
        }
        return "redirect:/products/list";
    }

    // 🔹 Show Edit Page
    @GetMapping("/edit/{id}")
    public String showEditPage(@PathVariable Long id, HttpSession session, Model model) {
        if (!isAuthenticated(session)) return "redirect:/login";
        if (!hasProductAccess(session)) return "redirect:/products/list";

        try {
            model.addAttribute("product", productService.getProductById(id));
        } catch (Exception e) {
            logger.warn("Product not found: {}", id);
            return "redirect:/products/list?error=notfound";
        }
        return "edit-product";
    }

    // 🔹 Update Product
    @PostMapping("/update")
    public String updateProduct(@ModelAttribute("product") Product product, HttpSession session) {
        if (!isAuthenticated(session)) return "redirect:/login";
        if (!hasProductAccess(session)) return "redirect:/products/list";

        try {
            productService.updateProduct(product);
        } catch (Exception e) {
            logger.error("Error updating product", e);
            return "redirect:/products/list?error=true";
        }
        return "redirect:/products/list";
    }
}

