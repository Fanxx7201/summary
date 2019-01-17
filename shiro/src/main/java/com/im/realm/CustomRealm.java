package com.im.realm;

import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * @ProjectName: sum
 * @Package: com.im.realm
 * @Description:
 * @Author: fanxx
 * @CreateDate: 2019/1/17 10:01
 * <p>Copyright: Copyright (c) 2019</p>
 */
public class CustomRealm extends AuthorizingRealm {
    Map<String, String> userMap = new HashMap<String, String>();
    {
        userMap.put("Mark", "123456");
        //RealmName名称设置
        super.setName("CustomeRealm");
    }
    /**
     * @Description  授权
     * @Date  2019/1/17
     * @Param [principals]
     * @return org.apache.shiro.authz.AuthorizationInfo
     **/
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String userName = (String)principals.getPrimaryPrincipal();
        //从数据库或者缓存中获取数据
        Set<String> roles = getRolesByUserName(userName);
        Set<String> permissions = getPermissionsByUserName(userName);
        //将角色和权限数据返回
        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    private Set<String> getPermissionsByUserName(String userName) {
        Set<String> sets = new HashSet<String>();
        sets.add("user:delete");
        sets.add("user:add");
        return sets;

    }


    /**
     * @Description  模拟从数据库或者是缓存中, 获取角色和权限数据
     * @Date  2019/1/17
     * @Param []
     * @return java.util.Set<java.lang.String>
     **/
    private Set<String> getRolesByUserName(String userName) {
        Set<String> sets = new HashSet();
        sets.add("admin");
        sets.add("user");
        return sets;
    }

    /**
     * @Description  认证
     * @Date  2019/1/17
     * @Param [token]
     * @return org.apache.shiro.authc.AuthenticationInfo
     **/
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        //1.从主体传过来的认证信息中获取用户名
        String userName = (String)token.getPrincipal();
        //2.通过用户名到数据库中获取凭证
        String password = getPasswordByUserName(userName);
        if(password == null){
            return null;
        }
        //返回认证信息
        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("Mark", password, "CustomeRealm");
        return authenticationInfo;
    }

    /**
     * @Description  正常这里应该访问数据库, 现在这个环境我们就不去访问数据库了, 模拟
     * @Date  2019/1/17
     * @Param [userName]
     * @return java.lang.String
     **/
    private String getPasswordByUserName(String userName) {
        return userMap.get(userName);
    }
}
