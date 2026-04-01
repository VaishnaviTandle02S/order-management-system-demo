package com.eomps.jms.consumer;

import com.eomps.dao.OrderDAO;
import com.eomps.entity.Order;
import com.eomps.service.email.EmailService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.stereotype.Component;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@Component
public class OrderConsumer {

    private static final Logger logger = LoggerFactory.getLogger(OrderConsumer.class);

    private static final String ORDER_QUEUE = "order.queue";
    private static final String DLQ_QUEUE = "order.dlq";

    private final OrderDAO orderDAO;
    private final JmsTemplate jmsTemplate;
    private final EmailService emailService;

    @Autowired
    public OrderConsumer(OrderDAO orderDAO,
                         JmsTemplate jmsTemplate,
                         EmailService emailService) {
        this.orderDAO = orderDAO;
        this.jmsTemplate = jmsTemplate;
        this.emailService = emailService;
    }

    // 🔥 LISTEN TO QUEUE
    @JmsListener(destination = ORDER_QUEUE)
    public void processOrder(Order order) {

        int retryCount = 0;
        final int maxRetries = 3;

        while (retryCount < maxRetries) {
            try {
                logger.info("📩 Received order from queue (Attempt {})", retryCount + 1);

                // 💾 Save to DB
                orderDAO.save(order);

                // 📧 Generate email content
                String emailContent = emailService.generateOrderEmail(order);
                logger.info("📧 Email Content:\n{}", emailContent);

                logger.info("✅ Order processed successfully");
                return;

            } catch (Exception e) {
                retryCount++;
                logger.error("❌ Error processing order, retry {}", retryCount, e);
            }
        }

        // 🔥 SEND TO DLQ AFTER MAX RETRIES
        try {
            jmsTemplate.convertAndSend(DLQ_QUEUE, order);
            logger.error("🚨 Order moved to DLQ after {} retries", maxRetries);
        } catch (Exception e) {
            logger.error("❌ Failed to send order to DLQ", e);
        }
    }
}