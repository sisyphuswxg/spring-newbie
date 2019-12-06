package com.sisyphuswxg.spring.aop.helloworld;

public class ArithmeticCalculatorImpl implements ArithmeticCalculator {
    @Override
    public int add(int i, int j) {
        int ret = i + j;
        return ret;
    }

    @Override
    public int sub(int i, int j) {
        int ret = i - j;
        return ret;
    }

    @Override
    public int mul(int i, int j) {
        int ret = i * j;
        return ret;
    }

    @Override
    public int div(int i, int j) {
        int ret = i / j;
        return ret;
    }
}
