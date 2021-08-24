package net.myfarm.aspect;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalControlAdvice {

    // Exception Handling for databases
    public String dataAccessExceptionHandler(DataAccessException e, Model model) {

        // Set empty string
        model.addAttribute("error", "");

        // Add messages to Model
        model.addAttribute("message", "Data Access Exception");

        // HTTP Error Code (500) to Model
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }

    // Other Exception Handling
    @ExceptionHandler(Exception.class)
    public String exceptionHandler(Exception e, Model model) {

        // Set empty string
        model.addAttribute("error", "");

        // Add messages to Model
        model.addAttribute("message", "Exception Error");

        // HTTP Error Code (500) to Model
        model.addAttribute("status", HttpStatus.INTERNAL_SERVER_ERROR);

        return "error";
    }


}
