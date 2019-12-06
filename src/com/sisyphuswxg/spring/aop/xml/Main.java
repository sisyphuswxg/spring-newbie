package com.sisyphuswxg.spring.aop.xml;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        //1. 创建Spring的IOC容器
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext-aop-xml.xml");

        //2. 从IOC容器中获取bean的实例
        ArithmeticCalculator arithmeticCalculator = ctx.getBean(ArithmeticCalculator.class);

        //3. 使用bean
        int result = arithmeticCalculator.add(3, 2);
        System.out.println("result = " + result);

    }
}
