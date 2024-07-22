package project.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import project.service.exception.ObjectNotFoundException;
import project.service.exception.UserAlreadyDoThat;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ResponseStatus(code = HttpStatus.NOT_FOUND)
    @ExceptionHandler(ObjectNotFoundException.class)
    public ModelAndView handleObjectNotFound(project.service.exception.ObjectNotFoundException objectNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("object-not-found");
        modelAndView.addObject("objectId", objectNotFoundException.getId());

        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(UserAlreadyDoThat.class)
    public ModelAndView handleObjectNotFound(UserAlreadyDoThat userIsModeratorOfThisBuildingException) {
        ModelAndView modelAndView = new ModelAndView("user-is-already-moderator");
        modelAndView.addObject("message", userIsModeratorOfThisBuildingException.getMessage());

        return modelAndView;
    }
}
