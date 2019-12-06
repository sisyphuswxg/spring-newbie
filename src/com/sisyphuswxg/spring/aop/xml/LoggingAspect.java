package com.sisyphuswxg.spring.aop.xml;

import org.aspectj.lang.JoinPoint;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

public class LoggingAspect {

    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("beforeMethod: The method " + methodName + " begins with " + args);
    }

    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("afterMethod: The method " + methodName + " ends with " + args);
    }

    public void afterReturning(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("afterReturning: The method " + methodName + " ends with result: " + result);
    }

    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("afterThrowing: The method " + methodName + " occurs exception: " + ex);
    }

    /*
    @Around("execution(* ArithmeticCalculator.*(..))")
    public Object aroundMethod(ProceedingJoinPoint pjd){

        Object result = null;
        String methodName = pjd.getSignature().getName();
        List<Object> args = Arrays.asList(pjd.getArgs());

        //执行目标方法
        try {
            //前置通知
            System.out.println("beforeMethod: The method " + methodName + " begins with " + args);
            result = pjd.proceed();
            //返回通知
            System.out.println("afterReturning: The method " + methodName + " ends with result: " + result);
        }catch (Throwable ex){
            //异常通知
            System.out.println("afterThrowing: The method " + methodName + " occurs exception: " + ex);
            throw new RuntimeException(ex);
        }
        //后置通知
        System.out.println("afterMethod: The method " + methodName + " ends with " + args);
        return result;
    }
     */

}
