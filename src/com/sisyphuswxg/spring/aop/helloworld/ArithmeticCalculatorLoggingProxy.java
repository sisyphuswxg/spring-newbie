package com.sisyphuswxg.spring.aop.helloworld;

import javax.naming.PartialResultException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.Arrays;
import java.util.Properties;

public class ArithmeticCalculatorLoggingProxy {

    //要代理的对象
    private ArithmeticCalculator target;

    public ArithmeticCalculatorLoggingProxy(ArithmeticCalculator target) {
        this.target = target;
    }

    public ArithmeticCalculator getLoggingProxy() {
        ArithmeticCalculator proxy = null;

        //代理对象由哪一个类进行加载
        ClassLoader loader = target.getClass().getClassLoader();

        //代理对象的类型，即其中有哪些方法
        Class [] interfaces = new Class[]{ArithmeticCalculator.class};

        //当调用代理对象其中的方法，该执行的代码
        InvocationHandler h = new InvocationHandler() {
            @Override
            public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                String methodName = method.getName();
                // 日志
                System.out.println("The method " + methodName + " begin with " + Arrays.asList(args));
                // 执行方法
                Object ret = method.invoke(target, args);
                // 日志
                System.out.println("The method " + methodName + " end with " + ret);
                return ret;
            }
        };
        proxy = (ArithmeticCalculator) Proxy.newProxyInstance(loader, interfaces, h);
        return proxy;
    }
}
