package com.card.config.shiro;

import com.card.model.User;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.SimpleAuthenticationInfo;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Set;

/**
 * Created by cuihp on 2020/4/26.
 */
@Component
public class ShiroRealm extends AuthorizingRealm{




    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
//        User user = (User) SecurityUtils.getSubject().getPrincipal();
//        String userName = user.getUsername();
//
//        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
//
//        // 获取用户角色集
//        List<Role> roleList = this.roleService.findUserRole(userName);
//        Set<String> roleSet = roleList.stream().map(Role::getRoleName).collect(Collectors.toSet());
//        simpleAuthorizationInfo.setRoles(roleSet);
//
//        // 获取用户权限集
//        List<Menu> permissionList = this.menuService.findUserPermissions(userName);
//        Set<String> permissionSet = permissionList.stream().map(Menu::getPerms).collect(Collectors.toSet());
//        simpleAuthorizationInfo.setStringPermissions(permissionSet);
//        return simpleAuthorizationInfo;
        return null;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        // 获取用户输入的用户名和密码
        String userName = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());

        // 通过用户名到数据库查询用户信息
//        User user = this.userService.findByName(userName);
        User user = new User();
        user.setName("CuiHongPeng");
        user.setPass("pass");
//        if (user == null)
//            throw new UnknownAccountException("用户名或密码错误！");
//        if (!StringUtils.equals(password, user.getPassword()))
//            throw new IncorrectCredentialsException("用户名或密码错误！");
//        if (User.STATUS_LOCK.equals(user.getStatus()))
//            throw new LockedAccountException("账号已被锁定,请联系管理员！");
        return new SimpleAuthenticationInfo(user, "pass", getName());
    }
}
