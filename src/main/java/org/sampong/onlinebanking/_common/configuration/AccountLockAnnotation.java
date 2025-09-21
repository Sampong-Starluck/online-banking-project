package org.sampong.onlinebanking._common.configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.sampong.onlinebanking._common.annotation.AccountLock;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.stereotype.Component;

//import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.concurrent.locks.ReentrantLock;

@Aspect
@Component
@RequiredArgsConstructor
@Slf4j
public class AccountLockAnnotation {

    private final AccountLockManager lockManager;
    private final ExpressionParser parser = new SpelExpressionParser();

    // Pointcut for methods annotated with @AccountLock
    @Pointcut("@annotation(accountLock)")
    public void annotatedMethod(AccountLock accountLock) {
    }

    @Around("@annotation(accountLock)")
    public Object lockAccounts(ProceedingJoinPoint pjp, AccountLock accountLock) throws Throwable {
        MethodSignature sig = (MethodSignature) pjp.getSignature();

        String[] paramNames = sig.getParameterNames();
        Object[] args = pjp.getArgs();
        var ctx = new StandardEvaluationContext();
        for (int i = 0; i < paramNames.length; i++) {
            ctx.setVariable(paramNames[i], args[i]);
        }

        // Collect account IDs from SpEL
        Set<Long> ids = new HashSet<>();
        for (String keyExpr : accountLock.values()) {
            Long id = parser.parseExpression(keyExpr).getValue(ctx, Long.class);
            if (id != null) ids.add(id);
        }

        // Sort to avoid deadlock (lock smaller id first)
        List<Long> sorted = new ArrayList<>(ids);
        sorted.sort(Long::compareTo);

        List<ReentrantLock> acquired = new ArrayList<>();
        try {
            for (Long id : sorted) {
                ReentrantLock lock = lockManager.getLock(id);
                log.debug("Waiting for lock on account {}", id);
                lock.lock(); // wait until available
                acquired.add(lock);
                log.debug("Got lock on account {}", id);
            }
            return pjp.proceed();

        } finally {
            for (int i = acquired.size() - 1; i >= 0; i--) {
                acquired.get(i).unlock();
                log.debug("Released lock on account {}", sorted.get(i));
            }
        }
    }
}
