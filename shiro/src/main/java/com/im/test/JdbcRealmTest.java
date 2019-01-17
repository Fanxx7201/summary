package com.im.test;

import com.alibaba.druid.pool.DruidDataSource;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.mgt.DefaultSecurityManager;
import org.apache.shiro.realm.jdbc.JdbcRealm;
import org.apache.shiro.subject.Subject;
import org.junit.Test;

/**
 * @ProjectName: sum
 * @Package: com.im.test
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/1/17 9:41
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class JdbcRealmTest {

    DruidDataSource dataSource = new DruidDataSource();
    {
        dataSource.setUrl("jdbc:mysql://localhost:");
        dataSource.setUsername("root");
        dataSource.setPassword("root");
    }

    @Test
    public void testAuthentication(){
        //jdbcRealm使用默认的sql语句
        JdbcRealm jdbcRealm = new JdbcRealm();
        jdbcRealm.setDataSource(dataSource);
        //设置权限的开关, 如果不设置的话, 是不能获取到用户权限的.
        jdbcRealm.setPermissionsLookupEnabled(true);

        //使用自定义的sql语句
        String sql = "select password from test_user where user_name = ?";
        jdbcRealm.setAuthenticationQuery(sql);


        //1.构建SecurityManager环境
        DefaultSecurityManager defaultSecurityManager = new DefaultSecurityManager();
        defaultSecurityManager.setRealm(jdbcRealm);

        //2.主体提交认证请求
        SecurityUtils.setSecurityManager(defaultSecurityManager);
        Subject subject = SecurityUtils.getSubject();

        //用户验证
        UsernamePasswordToken token = new UsernamePasswordToken("Mark", "123456");
        //登陆验证
        subject.login(token);
        //验证角色
        subject.checkRoles("admin","user");
        //验证权限
        subject.checkPermission("user:select");

        //是否认证的方法
        System.out.println("isAuthenticated:" + subject.isAuthenticated() );
    }
}
