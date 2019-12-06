package com.sisyphuswxg.spring.beans;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.awt.*;

public class Main {

    public static void main(String[] args) {

        /*
        HelloWorld helloworld = new HelloWorld();
        helloworld.setName("wangxg");
        */
        // 1. 创建Spring 的IOC 容器对象 - 对Bean进行初始化并赋值
        // ApplicationContext 代表IOC容器

        // 在Spring容器读取Bean配置 创建Bean实例之前，必须先对他进行初始化。只有在对容器实例化后，才可以从IOC容器里获取Bean实例并使用
        // Spring提供了两种类型的IOC容器实现
        //  1. BeanFactory - IOC的基本实现：是Spring框架的基础设施，面向Spring本身
        //  2. ApplicationContext - 提供了更多的高级特性，是BeanFactory的子接口：面向使用Spring框架的开者，几乎所有场合的应用场合都直接使用ApplicationContext而非底层的BeanFactory
        ApplicationContext ctx = new ClassPathXmlApplicationContext("applicationContext.xml");

        // ApplicationContext 的主要实现类：（ApplicationContext在初始化上下文的时就实例化所有的单例Bean）
        //  - ClassPathXmlApplicationContext: 从类路径下加载配置文件
        //  - FileSystemXmlApplicationContext: 从文件系统中加载配置文件
        //  - ConfigurableApplicationContext 扩展于ApplicationContext，新增两个主要方法refresh()和close()，让ApplicationContext具有启动、刷新和关闭上下文的能力

        // 2. 从IOC 容器中获取bean 实例
        // getBean()方法在BeanFactory中定义，如下是利用id定位到IOC容器中的Bean
        HelloWorld helloworld = (HelloWorld) ctx.getBean("helloWorld");
        // 还可以利用类型返回IOC容器中的Bean，但要求IOC容器中必须只能选择一个该类型的Bean
        //HelloWorld helloworld = ctx.getBean(HelloWorld.class);

        // Spring中支持三种依赖注入的方式： 1. 属性注入 2. 构造器注入 3. 工厂方法注入（很少使用，不推荐）
        //  属性注入：通过setter()方法注入Bean的属性值或依赖的对象（是实际中最常用的注入方式）
        //      使用<property>元素，使用name属性指定Bean的属性名称，value属性或<value>子节点指定属性值
        //  构造方式注入：保证了Bean实例在实例化后就可以使用
        //      构造器注入在<constructor-arg>元素里声明属性，<constructor-arg>中没有name属性

        // 3. 调用方法
        helloworld.hello();

        //Car car = ctx.getBean(Car.class);
        //System.out.println(car);
        Car car = (Car) ctx.getBean("car");
        System.out.println(car);

        Person person = (Person) ctx.getBean("person");
        System.out.println(person);

        Person person1 = (Person) ctx.getBean("person1");
        System.out.println(person1);

        Person person2 = (Person) ctx.getBean("person2");
        System.out.println(person2);

        // list
        Person2 person3 = (Person2) ctx.getBean("person3");
        System.out.println(person3);

        // map
        PersonMap person4 = (PersonMap) ctx.getBean("person4");
        System.out.println(person4);

        // Properties
        DataSource datasource = (DataSource) ctx.getBean("datasource");
        System.out.println(datasource.getProperties());

        // 独立的集合Bean
        Person2 person5 = (Person2) ctx.getBean("person5");
        System.out.println(person5);

        // p命名空间
        Person2 person6 = (Person2) ctx.getBean("person6");
        System.out.println(person6);
    }
}