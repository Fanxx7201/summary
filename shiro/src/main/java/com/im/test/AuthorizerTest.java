package com.im.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.SimpleAccountRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Before;
import org.junit.Test;


/**
 * @ProjectName: sum
 * @Package: com.im.test
 * @Description: 授权过程....
 * @Author: fanxx
 * @CreateDate: 2019/1/16 15:35
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class AuthorizerTest {
    //Realm域
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        //添加用户的时候, 给用户赋予管理员的角色
        simpleAccountRealm.addAccount("Mark", "123456", "admin");
    }

    @Test
    public void testAuthentication(){
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        //将realm添加到安全管理器中
        defaultSecurityManager.setRealm(simpleAccountRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //用户验证
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        //是否认证的方法
        System.out.println("isAuthenticated:" + subject.isAuthenticated() );
        subject.isAuthenticated();

        //认证完之后, 检查用户的角色
        subject.checkRole("admin"); // 角色是可变参数, 这里可以check多个角色

    }
}
