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
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/1/16 15:35
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class AuthenticationTest {
    //Realm域
    SimpleAccountRealm simpleAccountRealm = new SimpleAccountRealm();

    @Before
    public void addUser(){
        simpleAccountRealm.addAccount("Mark", "123456");
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

        //登出的方法
        subject.logout();

    }
}
