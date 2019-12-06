package com.sisyphuswxg.spring.beans.annotation.repository;

import com.sisyphuswxg.spring.beans.annotation.TestObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {

    /*
    默认情况下, 所有使用@Authwired注解的属性都需要被设置.
    当Spring找不到匹配的Bean装配属性时,会抛出异常
    若某一属性允许不被设置,可以设置@Authwired注解的required属性为false
     */
    @Autowired(required=false)
    private TestObject testObject;

    @Override
    public void save() {
        System.out.println("UserRepository Save...");
        System.out.println(testObject);
    }
}
