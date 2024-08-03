package project.web.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

import java.lang.reflect.Method;

@Aspect
@Component
public class MonitoringAspect {

    private static final Logger LOGGER = LoggerFactory.getLogger(MonitoringAspect.class);

    @Around("Pointcuts.onWarnIfExecutionTimeExceeds()")
    Object monitorExecutionTime(ProceedingJoinPoint proceedingJoinPoint) throws Throwable {
        WarnIfExecutionExceeds annotation = getAnnotation(proceedingJoinPoint);
        long limitMillis = annotation.limitMillis();

        StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        var result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        long executionTime = stopWatch.lastTaskInfo().getTimeMillis();

        if (executionTime > limitMillis) {
            LOGGER.warn("This method {} executed in {} millis >>> more than the acceptable {} millis", proceedingJoinPoint.getSignature(), executionTime, limitMillis);
        }
        return result;
    }

    private static WarnIfExecutionExceeds getAnnotation(ProceedingJoinPoint proceedingJoinPoint) {
        Method method = ((MethodSignature) proceedingJoinPoint.getSignature()).getMethod();

        return method.getAnnotation(WarnIfExecutionExceeds.class);
    }

}
