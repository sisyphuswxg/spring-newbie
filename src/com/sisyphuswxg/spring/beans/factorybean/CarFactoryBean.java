package com.sisyphuswxg.spring.beans.factorybean;

import org.springframework.beans.factory.FactoryBean;

public class CarFactoryBean implements FactoryBean {

    private String brand;

    public void setBrand(String brand) {
        this.brand = brand;
    }

    // 返回Bean的对象
    @Override
    public Object getObject() throws Exception {
        return new Car("BMW", 500000);
    }

    @Override
    public Class<?> getObjectType() {
        return Car.class;
    }

    @Override
    public boolean isSingleton() {
        return true;
    }
}
