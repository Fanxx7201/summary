package com.im.test;

import com.im.realm.CustomRealm;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @ProjectName: sum
 * @Package: com.im.test
 * @Description: 使用我们自定义的Realm进行认证和授权
 * @Author: fanxx
 * @CreateDate: 2019/1/17 10:41
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class CustomeRealmTest {

    @Test
    public void testAuthentication(){


        CustomRealm customRealm = new CustomRealm();
        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(customRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //用户验证
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        subject.login(token);

        //是否认证的方法
        System.out.println("isAuthenticated:" + subject.isAuthenticated() );

        //测试角色和权限是否获取到
        subject.checkRole("admin");
        subject.checkPermissions("user:add","user:delete");

    }
}
