package com.sisyphuswxg.spring.beans.scope;

import com.sisyphuswxg.spring.beans.autowire.Car;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-scope.xml");

        Car car = (Car) ctx.getBean("car");
        System.out.println(car);
        Car car1 = (Car) ctx.getBean("car");
        Car car2 = (Car) ctx.getBean("car");
        System.out.println(car1 == car2);
    }
}