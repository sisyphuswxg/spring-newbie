package com.sisyphuswxg.spring.beans.annotation.service;

import com.sisyphuswxg.spring.beans.annotation.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    /*
    默认情况下,当IOC容器里存在多个类型兼容的Bean时,通过类型的自动装配将无法工作
    此时可以在@Qualifier注解里提供Bean的名称.Spring允许对方法的入参标注@Qualifiter已指定注入Bean的名称
     */
    //@Autowired
    //@Qualifier("userRepositoryImpl")
    private UserRepository userRepository;

    @Autowired
    public void setUserRepository(@Qualifier("userRepositoryImpl") UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void add() {
        System.out.println("UserService add...");
        userRepository.save();
    }
}
