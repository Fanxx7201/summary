# SHIRO

## 概念与介绍
### 1.shiro是什么?
> shiro是Apache的一个开源的, 权限管理框架  
> 认证、授权、企业会话管理、安全加密  

### 原理
![Image text](https://github.com/Fanxx7201/summary/blob/master/img/shiro.jpg)

### 2.Shiro和Spring Security的区别?
1. Shiro
> 简单、灵活  
> 可脱离Spring  
> 粒度较粗
2. Spring Security
> 复杂、笨重  
> 不可脱离Spring
> 粒度更细

### 3.Shiro认证过程?
1. 构建SecurityManager环境
2. 主体提交认证请求(subject)
3. 请求提交到SecurityManager进行认证
4. Authenticator做认证
5. Realm查询数据库进行验证

### 4.Shiro授权过程?
1. 构建SecurityManager环境
2. 进行主体授权(SecurityManager)-->Authorizer授权器进行授权
3. Realm域获取角色和权限数据