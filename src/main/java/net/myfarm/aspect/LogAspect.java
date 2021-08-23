package net.myfarm.aspect;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;


@Aspect
@Component
@Slf4j
public class LogAspect {

    /**
     *  Generate logs before executing service
     *  Target: Classes that contain [UserService]
     */

    @Before("execution(* *..*UserService.*(..))")
    public void startLog(JoinPoint jp) {
        log.info("Start method: " + jp.getSignature());
    }

    /**
     * Generate logs AFTER executing service
     * Target: Classes that contain [UserService]
     */
    @After("execution(* *..*UserServiece.*(..))")
    public void endLog(JoinPoint jp) {
        log.info("End method:" + jp.getSignature());
    }

    // Generate logs before and after executing controller
    //@Around("bean(*Controller)")
    //@Around("@annotation(org.springframework.web.blind.annotation.GetMapping)")
    @Around("@within(org.springframework.stereotype.Controller)")
    public Object startLog(ProceedingJoinPoint jp) throws Throwable {

        //Start Logs
        log.info("Start method: " + jp.getSignature());

        try {
            // Execute method
            Object result = jp.proceed();

            // End logs
            log.info("End Method: " + jp.getSignature());

            // Result
            return result;
        } catch {
            // Error logs
            log.error("Method ended unexpectedly: " + jp.getSignature());

            // Error re-throw
            throw e;
        }
    }
}
