package ua.com.alexcoffee.controller;

import org.junit.AfterClass;
import org.junit.BeforeClass;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ua.com.alexcoffee.exception.BadRequestException;
import ua.com.alexcoffee.exception.DuplicateException;
import ua.com.alexcoffee.exception.ForbiddenException;
import ua.com.alexcoffee.exception.WrongInformationException;
import ua.com.alexcoffee.tools.MockController;

import javax.servlet.http.HttpServletRequest;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static ua.com.alexcoffee.tools.ModelAndViews.checkModelAndViewWithException;

public class AdviceControllerTest {

    private static AdviceController adviceController;

    @BeforeClass
    public static void setUp() {
        System.out.println("\nTesting class \"SEOController\" - START.\n");

        adviceController = MockController.getAdviceController();
    }

    @AfterClass
    public static void tearDown() {
        System.out.println("Testing class \"SEOController\" - FINISH.");
    }

    @Test
    public void noHandlerFoundExceptionTest() throws Exception {
        System.out.print("-> noHandlerFoundException() - ");

        NoHandlerFoundException ex = (NoHandlerFoundException) exceptionMock(NoHandlerFoundException.class);
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.noHandlerFoundException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void badRequestExceptionTest() throws Exception {
        System.out.print("-> badRequestException() - ");

        BadRequestException ex = (BadRequestException) exceptionMock(BadRequestException.class);
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.badRequestException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void wrongInformationExceptionTest() throws Exception {
        System.out.print("-> wrongInformationException() - ");

        WrongInformationException ex = (WrongInformationException) exceptionMock(WrongInformationException.class);
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.wrongInformationException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void forbiddenExceptionTest() throws Exception {
        System.out.print("-> forbiddenException() - ");

        ForbiddenException ex = (ForbiddenException) exceptionMock(ForbiddenException.class);
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.forbiddenException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void duplicateExceptionTest() throws Exception {
        System.out.print("-> duplicateException() - ");

        DuplicateException ex = (DuplicateException) exceptionMock(DuplicateException.class);
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.duplicateException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Test
    public void otherExceptionTest() throws Exception {
        System.out.print("-> otherException() - ");

        Exception ex = exceptionMock(Exception.class);
        HttpServletRequest request = requestMock();

        ModelAndView modelAndView = adviceController.otherException(ex, request);
        checkModelAndViewWithException(modelAndView);

        System.out.println("OK!");
    }

    @Ignore
    private static Exception exceptionMock(Class<? extends Exception> exp) {
        Exception ex = mock(exp);
        when(ex.getMessage()).thenReturn(exp.getName() + " MESSAGE");
        return ex;
    }

    @Ignore
    private static HttpServletRequest requestMock() {
        HttpServletRequest request = mock(HttpServletRequest.class);
        when(request.getRemoteAddr()).thenReturn("RemoteAddr");
        when(request.getRequestURL()).thenReturn(new StringBuffer("URL"));
        return request;
    }
}
