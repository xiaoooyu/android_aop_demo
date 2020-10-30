package com.xiaoooyu.demo.trace;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Copyright (c) 2020 Teambition All Rights Reserved.
 */
@Aspect
public class TraceAgent {
    private static final String POINTCUT_METHOD =
            "execution(@com.xiaoooyu.demo.trace.DebugTrace * *(..))";
    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.xiaoooyu.demo.trace.DebugTrace *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithDebugTrace() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedDebugTrace() {}

    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        DebugLog.log(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));

        return result;
    }

    private static String buildLogMessage(String methodName, long duration) {
        StringBuilder message = new StringBuilder();
        message.append("trace -->");
        message.append(methodName);
        message.append(" --> ");
        message.append("[");
        message.append(duration);
        message.append("ms");
        message.append("]");

        return message.toString();
    }


}
