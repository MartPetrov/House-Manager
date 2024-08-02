package project.web;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import project.service.exception.*;

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
    public ModelAndView handleUserAlreadyDoThat(UserAlreadyDoThat userIsModeratorOfThisBuildingException) {
        ModelAndView modelAndView = new ModelAndView("object-problem-message");
        modelAndView.addObject("message", userIsModeratorOfThisBuildingException.getMessage());

        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(UserNotFoundException.class)
    public ModelAndView handleUserNotFound(UserNotFoundException userNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("object-problem-message");
        modelAndView.addObject("message", userNotFoundException.getMessage());

        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.CONFLICT)
    @ExceptionHandler(BuildingNotFoundException.class)
    public ModelAndView handleBuildingNotFound(BuildingNotFoundException buildingNotFoundException) {
        ModelAndView modelAndView = new ModelAndView("object-problem-message");
        modelAndView.addObject("message", buildingNotFoundException.getMessage());

        return modelAndView;
    }

    @ResponseStatus(code = HttpStatus.UNAUTHORIZED)
    @ExceptionHandler(UnsupportedOperationException.class)
    public ModelAndView UnsupportedOperation(UnsupportedOperationException unsupportedOperationException) {
        ModelAndView modelAndView = new ModelAndView("object-problem-message");
        modelAndView.addObject("message", unsupportedOperationException.getMessage());

        return modelAndView;
    }
}
