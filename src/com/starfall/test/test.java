package com.starfall.test;

import com.starfall.config.sf_config;
import com.starfall.service.DiscussService;
import com.starfall.service.UserService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.testng.annotations.Test;

public class test {
    @Test
    public void test(){
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        UserService userService = context.getBean("userService", UserService.class);
//        User user = userService.getInfo("admin");
//        System.out.println(userService.getName("admin"));
//        System.out.println(user.getUser());
//        userService.reg("abc","aaa","新","1111-11-11","123@q.com");
//        System.out.println(userService.checkUserRepeat("aaa"));
//        userService.updateInformation("admin","管理","jieshao");
        System.out.println(userService.checkOldPassword("admin"));
    }
    @Test
    public void test2(){
        ApplicationContext context = new AnnotationConfigApplicationContext(sf_config.class);
        DiscussService discussService = context.getBean("discussService", DiscussService.class);
//        System.out.println(discussService.getDiscuss(1, null));
        System.out.println(discussService.getPage("admin"));
    }
}
