//package com.winjean.shiro;
//
//import org.apache.shiro.authc.*;
//import org.apache.shiro.authz.AuthorizationInfo;
//import org.apache.shiro.authz.SimpleAuthorizationInfo;
//import org.apache.shiro.realm.AuthorizingRealm;
//import org.apache.shiro.subject.PrincipalCollection;
//
///**
// * @author ：winjean
// * @date ：Created in 2019/3/8 13:51
// * @description：${description}
// * @modified By：
// * @version: $version$
// */
//public class ShiroRealm extends AuthorizingRealm {
//
//    /**
//     * 授权
//     * @param principals
//     * @return
//     */
//    @Override
//    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
//
//        System.out.println("权限配置------------>MyShiroRealm.doGetAuthorizationInfo()");
//        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
////        UserInfo userInfo  = (UserInfo)principals.getPrimaryPrincipal();
////        for(SysRole role:userInfo.getRoleList()){
////            authorizationInfo.addRole(role.getRole());
////            for(SysPermission p:role.getPermissions()){
////                authorizationInfo.addStringPermission(p.getPermission());
////            }
////        }
//        return authorizationInfo;
//    }
//
//    /**
//     * 认证
//     * @param token
//     * @return
//     * @throws AuthenticationException
//     */
//    @Override
//    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
//        System.out.println("MyShiroRealm.doGetAuthenticationInfo()");
//        //获取用户的输入的账号.
//        String userName = (String)token.getPrincipal();
//
////        System.out.println(token.getCredentials());
//
//        if (null == userName) {
////            throw new AccountException("帐号或密码不正确！");
//            return null;
//        }
//        //通过username从数据库中查找 User对象，如果找到，没找到.
//        //实际项目中，这里可以根据实际情况做缓存，如果不做，Shiro自己也是有时间间隔机制，2分钟内不会重复执行该方法
////        UserInfo userInfo = userInfoService.findByUsername(username);
////        System.out.println("----->>userInfo="+userInfo);
////        if(userInfo == null){
////            return null;
////        }
////        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo(
////                userInfo, //用户名
////                userInfo.getPassword(), //密码
////                ByteSource.Util.bytes(userInfo.getCredentialsSalt()),//salt=username+salt
////                getName()  //realm name
////        );
//
//        SimpleAuthenticationInfo authenticationInfo = new SimpleAuthenticationInfo("winjean","123456",null,getName());
//        return authenticationInfo;
//
//    }
//}
