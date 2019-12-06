package com.sisyphuswxg.spring.beans.properties;

import com.sisyphuswxg.spring.beans.DataSource;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Main {

    public static void main(String[] args) {

        ApplicationContext ctx = new ClassPathXmlApplicationContext("beans-properties.xml");

        DataSource datasource = (DataSource) ctx.getBean("datasource");
        System.out.println(datasource.getProperties());
    }
}
