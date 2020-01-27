package com.clone.airbnb.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class ExceptionController {
	
    @ExceptionHandler(Throwable.class)
    public ModelAndView handleError(HttpServletRequest request, Exception e)   {
    	e.printStackTrace();
        return new ModelAndView("error/error");
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView handleError404(HttpServletRequest request, Exception e)   {
    	e.printStackTrace();
        return new ModelAndView("error/404");
    }
    
}