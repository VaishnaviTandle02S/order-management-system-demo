package com.eomps.controller;

import com.eomps.entity.Order;
import com.eomps.entity.User;
import com.eomps.service.OrderService;
import com.eomps.service.ProductService;
import com.eomps.service.email.EmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.PrintWriter;

@Controller
@RequestMapping("/orders")
public class OrderController {


    private final OrderService orderService;
    private final ProductService productService;
    private final EmailService emailService;

    @Autowired
    public OrderController(OrderService orderService,
                           ProductService productService,
                           EmailService emailService) {
        this.orderService = orderService;
        this.productService = productService;
        this.emailService = emailService;
    }

    @GetMapping("/buy/{productId}")
    public String buyProduct(@PathVariable Long productId,
                             HttpSession session) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        try {
            productService.buyProduct(productId, user);
        } catch (Exception e) {
            e.printStackTrace();
            return "redirect:/products/list?error=true";
        }

        return "redirect:/orders/my";
    }

    // 🔥 USER ORDERS
    @GetMapping("/my")
    public String viewMyOrders(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");

        if (user == null) {
            return "redirect:/login";
        }

        try {
            model.addAttribute("orders",
                    orderService.getUserOrders(user.getId()));
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("orders", null);
        }

        return "my-orders";
    }

    // 🔥 ADMIN / VENDOR ALL ORDERS
    @GetMapping("/all")
    public String viewAllOrders(HttpSession session, Model model) {

        User user = (User) session.getAttribute("loggedInUser");
        boolean isVendor = session.getAttribute("loggedInVendor") != null;

        if (user == null && !isVendor) {
            return "redirect:/login";
        }

        if (user != null && !"ADMIN".equals(user.getRole())) {
            return "redirect:/home";
        }

        try {
            model.addAttribute("orders", orderService.getAllOrders());
        } catch (Exception e) {
            e.printStackTrace();
            model.addAttribute("orders", null);
        }

        return "all-orders";
    }

    // View FTL-rendered order summary in browser
    @GetMapping("/summary/{orderId}")
    public void showOrderSummary(@PathVariable Long orderId,
                                 HttpSession session,
                                 HttpServletResponse response) throws Exception {

        User user = (User) session.getAttribute("loggedInUser");
        if (user == null) {
            response.sendRedirect("login");
            return;
        }

        try {
            Order order = orderService.getOrderById(orderId);
            String rendered = emailService.generateOrderEmail(order);

            response.setContentType("text/plain;charset=UTF-8");
            PrintWriter writer = response.getWriter();
            writer.write(rendered);
            writer.flush();

        } catch (Exception e) {
            e.printStackTrace();
            response.setContentType("text/plain;charset=UTF-8");
            response.getWriter().write("Error generating order summary: " + e.getMessage());
        }
    }
}