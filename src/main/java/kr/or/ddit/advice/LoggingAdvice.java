package kr.or.ddit.advice;

import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;

@Slf4j
public class LoggingAdvice {
    public void before(JoinPoint point) {
        long startTime = System.currentTimeMillis();
        Object target = point.getTarget();
        String targetName = target.getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        log.info("==-=-=-=-=-BEFORE=-=-=-=-={}.{} ({})", targetName, methodName, args);

    }

    public void after(JoinPoint point, Object retValue) {
        long endTime = System.currentTimeMillis();
        log.info("==-=-=-=-=AFTER-=-=-=: 반환값 : {} -=-=", retValue);

    }

    public Object around(ProceedingJoinPoint point) throws Throwable {
        long startTime = System.currentTimeMillis();
        Object target = point.getTarget();
        String targetName = target.getClass().getSimpleName();
        String methodName = point.getSignature().getName();
        Object[] args = point.getArgs();
        log.info("==-=-=-=-=-BEFORE=-=-=-=-={}.{} ({})", targetName, methodName, args);

        Object retValue = point.proceed(args);

        long endTime = System.currentTimeMillis();
        log.info("==-=-=-=-=AFTER-=-소요시간 : {}ms=-=: 반환값 : {} -=-=", (endTime-startTime), retValue);

        return retValue;
    }
}
