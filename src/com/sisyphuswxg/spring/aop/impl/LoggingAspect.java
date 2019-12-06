package com.sisyphuswxg.spring.aop.impl;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

// 把这个类声明为一个切面： 1. 需要把该类放入到IOC容器中 2. 再声明为一个切面
@Aspect
@Component
@Order(1)
public class LoggingAspect {

    //重用切入点：可以通过 @Pointcut 注解将一个切入点声明成简单的方法.
    //切入点的方法体通常是空的, 因为将切入点定义与应用程序逻辑混在一起是不合理的
    //切入点方法的访问控制符同时也控制着这个切入点的可见性. 如果切入点要在多个切面中共用, 最好将它们集中在一个公共的类中.
    // - 在这种情况下, 它们必须被声明为public.
    // - 在引入这个切入点时, 必须将类名也包括在内
    // - 如果类没有与这个切面放在同一个包中, 还必须包含包名
    // - 其他通知可以通过方法名称引入该切入点
    @Pointcut("execution(public int com.sisyphuswxg.spring.aop.impl.ArithmeticCalculator.*(int, int))")
    public void declareJoinPointExpression(){}

    //声明该方法是一个前置通知：在目标方法之前执行
    //@Before("execution(public int com.sisyphuswxg.spring.aop.impl.ArithmeticCalculator.add(int, int))")
    @Before("declareJoinPointExpression()")
    public void beforeMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("beforeMethod: The method " + methodName + " begins with " + args);
    }

    //一个切面可以包括一个或多个通知
    //后置通知：在目标方法执行后（无论是否发生异常）执行的通知
    //在后置通知中，还不能访问目标方法执行的结果
    //@After("execution(public int com.sisyphuswxg.spring.aop.impl.ArithmeticCalculator.*(int, int))")
    @After("declareJoinPointExpression()")
    public void afterMethod(JoinPoint joinPoint){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("afterMethod: The method " + methodName + " ends with " + args);
    }

    //返回通知：在方法正常执行结束后返回的通知
    //返回通知是可以访问到方法的返回值的！！
    //@AfterReturning(value="execution(public int com.sisyphuswxg.spring.aop.impl.ArithmeticCalculator.*(..))",
    //    returning="result")
    @AfterReturning(value="declareJoinPointExpression()", returning="result")
    public void afterReturning(JoinPoint joinPoint, Object result){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("afterReturning: The method " + methodName + " ends with result: " + result);
    }

    //异常通知：只在连接点出现异常时才执行异常通知
    //可以访问到异常对象，且可以指定在出现特定异常时在执行通知时通知代码
    //@AfterThrowing(value="execution(public int com.sisyphuswxg.spring.aop.impl.ArithmeticCalculator.*(..))",
    //    throwing="ex")
    @AfterThrowing(value="declareJoinPointExpression()", throwing="ex")
    public void afterThrowing(JoinPoint joinPoint, Exception ex){
        String methodName = joinPoint.getSignature().getName();
        List<Object> args = Arrays.asList(joinPoint.getArgs());
        System.out.println("afterThrowing: The method " + methodName + " occurs exception: " + ex);
    }

    //环绕通知是所有通知类型中功能最为强大的, 能够全面地控制连接点. 甚至可以控制是否执行连接点.
    //环绕通知需要携带ProceedingJoinPoint类型的参数,它是 JoinPoint 的子接口, 允许控制何时执行, 是否执行连接点.
    //环绕通知类似于动态代理的全过程：ProceedingJoinPoint类型的参数可以决定是否执行目标方法
    //  - 在环绕通知中需要明确调用 ProceedingJoinPoint 的 proceed() 方法来执行被代理的方法. 如果忘记这样做就会导致通知被执行了, 但目标方法没有被执行.
    //且环绕方法必须有返回值，返回值是目标方法的返回值，否则会出现空指针异常
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
