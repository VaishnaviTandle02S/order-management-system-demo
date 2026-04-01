package com.eomps.service.email;

import com.eomps.entity.Order;
import freemarker.template.Configuration;
import freemarker.template.Template;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.StringWriter;
import java.util.HashMap;
import java.util.Map;

@Service
public class EmailService {

    private final Configuration freemarkerConfig;

    @Autowired
    public EmailService(Configuration freemarkerConfig) {
        this.freemarkerConfig = freemarkerConfig;
    }

    // 🔥 THIS METHOD WAS MISSING / WRONG
    public String generateOrderEmail(Order order) {

        try {
            Template template = freemarkerConfig.getTemplate("order-summary.ftl");

            Map<String, Object> model = new HashMap<>();
            model.put("username", order.getUser().getUsername());
            model.put("product", order.getProduct().getName());
            model.put("price", order.getTotalPrice());
            model.put("date", order.getOrderDate());

            StringWriter writer = new StringWriter();
            template.process(model, writer);

            return writer.toString();

        } catch (Exception e) {
            throw new IllegalArgumentException("Error generating email", e);
        }
    }
}