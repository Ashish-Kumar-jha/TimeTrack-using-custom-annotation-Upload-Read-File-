package com.poc.practice.TimeTrackerAnnotation;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class ExecutionTracker {
private static final Logger log = Logger.getLogger(ExecutionTracker.class);
    @Around("@annotation(com.poc.practice.TimeTrackerAnnotation.TrackExecutionTime)")
    public Object trackTime(ProceedingJoinPoint joinPoint) throws Throwable {
        long startTime=System.currentTimeMillis();
        Object obj=joinPoint.proceed();
        long endTime=System.currentTimeMillis();
        log.debug("Method: "+joinPoint.getSignature());
        log.info("Total time taken: "+ (endTime-startTime));
        System.out.println("Method: "+joinPoint.getSignature());
        System.out.println("Total time taken: "+ (endTime-startTime));
        return obj;
    }
}
