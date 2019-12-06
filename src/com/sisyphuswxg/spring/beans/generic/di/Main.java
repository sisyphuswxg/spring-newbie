package com.sisyphuswxg.spring.beans.generic.di;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-generic-di.xml");

        /*
        泛型依赖注入：
            可以为子类注入子类对应的泛型类型的成员变量的引用
         */
        UserService userService = (UserService) ctx.getBean("userService");
        userService.add();
    }
}