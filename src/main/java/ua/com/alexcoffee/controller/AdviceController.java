package ua.com.alexcoffee.controller;

import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.service.ShoppingCartService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.exception.ForbiddenException;
import ua.com.alexcoffee.exception.WrongInformationException;

import javax.servlet.http.HttpServletRequest;

@ControllerAdvice
public class AdviceController {

    @Autowired
    private ShoppingCartService shoppingCartService;
    private Logger logger;

    public AdviceController() {
        logger = Logger.getLogger(AdviceController.class);
    }

    public AdviceController(Logger logger) {
        this.logger = logger;
    }

    // Catching NoHandlerFoundException (http status 404)
    @ExceptionHandler(NoHandlerFoundException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public ModelAndView noHandlerFoundException(NoHandlerFoundException ex, HttpServletRequest request) {
        return handleException(ex, request, "Ошибка 404. Не найдено!");
    }

    // Catching BadRequestException (http status 400)
    @ExceptionHandler(BadRequestException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView badRequestException(BadRequestException ex, HttpServletRequest request) {
        return handleException(ex, request, "Ошибка в запросе!");
    }

    // Catching WrongInformationException (http status 400)
    @ExceptionHandler(WrongInformationException.class)
    @ResponseStatus(value = HttpStatus.BAD_REQUEST)
    public ModelAndView checkoutException(WrongInformationException ex, HttpServletRequest request) {
        return handleException(ex, request, "Ошибка в запросе!");
    }

    // Catching ForbiddenException (http status 403)
    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(value = HttpStatus.FORBIDDEN)
    public ModelAndView forbiddenException(ForbiddenException ex, HttpServletRequest request) {
        return handleException(ex, request, "У Вас нет достаточных прав для доступа к этой странице.");
    }

    // Catching DuplicateException (http status 409)
    @ExceptionHandler(DuplicateException.class)
    @ResponseStatus(value = HttpStatus.CONFLICT)
    public ModelAndView duplicateException(DuplicateException ex, HttpServletRequest request) {
        return handleException(ex, request, "Такой объект уже существует!");
    }

    // Catching other Exception (http status 500)
    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public ModelAndView otherException(Exception ex, HttpServletRequest request) {
        return handleException(ex, request, "Временные неполадки с сервером... Приносим свои извинения!");
    }

    private ModelAndView handleException(Exception ex, HttpServletRequest request, String textError) {
        logger.error(request.getRemoteAddr() + " : " + request.getRequestURL());
        logger.error(ex.getMessage(), ex);
        ex.printStackTrace();
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("shopingCartSize", shoppingCartService.getSize());
        modelAndView.addObject("textError", textError);
        modelAndView.setViewName("error");
        return modelAndView;
    }
}
