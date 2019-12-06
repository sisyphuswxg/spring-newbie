package com.sisyphuswxg.spring.aop.helloworld;

public class Main {

    public static void main(String[] args) {
        //ArithmeticCalculator arithmeticCalculator = null;
        //arithmeticCalculator = new ArithmeticCalculatorImpl();
        ArithmeticCalculator target = new ArithmeticCalculatorImpl();
        ArithmeticCalculator proxy = new ArithmeticCalculatorLoggingProxy(target).getLoggingProxy();

        //int ret = arithmeticCalculator.add(1, 2);
        //System.out.println(ret);
        int ret = proxy.add(1, 2);
        System.out.println(ret);

        //ret = arithmeticCalculator.div(4, 2);
        //System.out.println(ret);
        ret = proxy.div(4, 2);
        System.out.println(ret);
    }
}
