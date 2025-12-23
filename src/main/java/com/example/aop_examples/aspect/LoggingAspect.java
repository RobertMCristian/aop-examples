package com.example.aop_examples.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.stereotype.Component;

@Component
@Aspect
public class LoggingAspect {
    @Before("execution(* com.example.aop_examples.services.MovieService.*(..))")
    public void logBeforeService() {
        System.out.println("service method is about to run");
    }

    @After("within(com.example.aop_examples.controller.MovieController)")
    public void logAfterController() {
        System.out.println("controller method finished");
    }

    @Before("this(com.example.aop_examples.services.MovieService)")
    public void logProxyType() {
        System.out.println("proxy type is MovieService");
    }

    @AfterReturning("target(com.example.aop_examples.services.MovieService)")
    public void logTargetType() {
        System.out.println("target object is MovieService");
    }

    @Before("execution(* com.example.aop_examples.services.MovieService.*(..)) && args(String, ..)")
    public void logStringArgument() {
        System.out.println("MovieService calld with String arg");
    }

    @Around("within(com.example.aop_examples.controller.MovieController) && " +
            "@annotation(org.springframework.web.bind.annotation.GetMapping)")
    public Object logAroundGet(ProceedingJoinPoint joinPoint) throws Throwable {
        System.out.println("entering GET: " + joinPoint.getSignature());
        Object result = joinPoint.proceed();
        System.out.println("exiting GET: " + joinPoint.getSignature());
        return result;
    }

    @Before("within(com.example.aop_examples.controller..*) && " +
            "@within(org.springframework.web.bind.annotation.RestController)")
    public void logRestController() {
        System.out.println("RestController detected");
    }

    @After("target(com.example.aop_examples.services.MovieService) && " +
            "@target(org.springframework.stereotype.Service)")
    public void logServiceAnnotation() {
        System.out.println("@Service target involved");
    }

    @Before("execution(* com.example.aop_examples.services.MovieService.*(..)) && " +
            "@args(org.springframework.validation.annotation.Validated)")
    public void logValidatedArgs() {
        System.out.println("argument type @Validated");
    }
}
