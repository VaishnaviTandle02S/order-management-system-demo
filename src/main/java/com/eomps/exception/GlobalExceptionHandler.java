package com.eomps.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletResponse;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model, HttpServletResponse response) {

        // If response already committed (e.g. void endpoints writing directly),
        // do not attempt to forward to error view
        if (response.isCommitted()) {
            return null;
        }

        ex.printStackTrace();
        model.addAttribute("error", ex.getMessage());
        return "error";
    }
}
