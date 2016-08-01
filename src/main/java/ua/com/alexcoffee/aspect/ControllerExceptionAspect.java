package ua.com.alexcoffee.aspect;

import org.apache.log4j.Logger;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class ControllerExceptionAspect {

    private Logger logger;

    public ControllerExceptionAspect() {
        logger = Logger.getLogger(ControllerExceptionAspect.class);
    }

    public ControllerExceptionAspect(Logger logger) {
        this.logger = logger;
    }

    // Catching all exceptions
    @AfterThrowing(pointcut = "execution(* ua.com.alexcoffee..controller..*(..))", throwing = "exception")
    public void afterThrowingAdvice(Exception exception) {
        logger.error("EXCEPTION IN METHOD -> " + exception.getClass());
    }
}
