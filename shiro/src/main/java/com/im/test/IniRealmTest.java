package com.im.test;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.text.IniRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @ProjectName: sum
 * @Package: com.im.test
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/1/16 17:00
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class IniRealmTest {

    @Test
    public void testAuthentication(){
        //参数是路径
        IniRealm iniRealm = new IniRealm("classpath:user.ini");





        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(iniRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //用户验证
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        //是否认证的方法
        System.out.println("isAuthenticated:" + subject.isAuthenticated() );
        subject.isAuthenticated();



    }
}
