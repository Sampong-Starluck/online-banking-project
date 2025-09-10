package org.sampong.onlinebanking._common.configuration;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.sampong.onlinebanking._common.annotation.Logger;
import org.sampong.onlinebanking._common.exception.CustomException;
import org.slf4j.MDC;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import java.lang.management.ManagementFactory;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.UUID;

@Aspect
@Component
@Slf4j
public class LoggerAnnotation {

    // Pointcut for methods annotated with @Logger
    @Pointcut("@annotation(logger)")
    public void annotatedMethod(Logger logger) {
    }

    // Pointcut for types (class-level) annotated with @Logger
    @Pointcut("@within(logger)")
    public void annotatedType(Logger logger) {
    }

    // Combined advice; logger will be bound from either pointcut
    @Around(value = "annotatedMethod(logger) || annotatedType(logger)", argNames = "joinPoint,logger")
    public Object loggerAround(ProceedingJoinPoint joinPoint, Logger logger)
            throws Throwable {

        // Fallback: if binding didn't supply the annotation, look it up
        if (logger == null) {
            MethodSignature sig = (MethodSignature) joinPoint.getSignature();
            Method method = sig.getMethod();
            logger = method.getAnnotation(Logger.class);
            if (logger == null && joinPoint.getTarget() != null) {
                logger = joinPoint.getTarget().getClass()
                        .getAnnotation(Logger.class);
            }
        }

        String label = (logger != null && !logger.value().isEmpty())
                ? logger.value()
                : joinPoint.getSignature().toShortString();

        // Correlation id for logs
        String requestId = UUID.randomUUID().toString();
        MDC.put("requestId", requestId);

        // Process id (pid@hostname -> pid)
        String processId = ManagementFactory.getRuntimeMXBean()
                .getName()
                .split("@")[0];

        log.info("Start [{}] [PID:{}] {} args={}",
                requestId, processId, label,
                Arrays.toString(joinPoint.getArgs()));

        long startNanos = System.nanoTime();
        try {
            Object result = joinPoint.proceed();
            long elapsedMs = (System.nanoTime() - startNanos) / 1_000_000;
            log.info("End   [{}] [PID:{}] {} result={} elapsed={}ms", requestId, processId, label, result, elapsedMs);
            return result;

        } catch (Throwable t) {
            // Log full stacktrace
            log.error("Exception [{}] [PID:{}] {} -> {}", requestId, processId, label, t.getMessage(), t);

            // Rethrow CustomException unchanged so handlers can react to it
            if (t instanceof CustomException) {
                throw t;
            }

            // Otherwise wrap and preserve cause
            throw new CustomException(HttpStatus.INTERNAL_SERVER_ERROR, "Something went wrong",t);
        } finally {
            // cleanup MDC
            MDC.remove("requestId");
        }
    }
}