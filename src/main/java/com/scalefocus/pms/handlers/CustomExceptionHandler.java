// All Rights Reserved, Copyright © ScaleFocus

package com.scalefocus.pms.handlers;

import com.scalefocus.pms.constants.AttributeConstants;
import com.scalefocus.pms.constants.ViewNameConstants;
import com.scalefocus.pms.exceptions.*;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class CustomExceptionHandler {

    /**
     * @param exception - the exception thrown.
     * @return - returns modelAndView.
     */
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ExceptionHandler({UserNotFoundException.class, CustomNotfoundException.class, PhaseNotFoundException.class,
            ProjectNotFoundException.class, TeamNotFoundException.class})
    public ModelAndView handleNotFound(Exception exception) {

        ModelAndView modelAndView = new ModelAndView();

        modelAndView.setViewName(ViewNameConstants.NOT_FOUND_ERROR_PAGE);
        modelAndView.addObject(AttributeConstants.EXCEPTION, exception);

        return modelAndView;
    }
}
