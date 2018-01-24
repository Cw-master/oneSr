package cxb.chen.urights.client.shiro.realm;

import cxb.chen.common.util.MD5Util;
import cxb.chen.common.util.PropertiesFileUtil;
import cxb.chen.urights.dao.model.UrightsPermission;
import cxb.chen.urights.dao.model.UrightsRole;
import cxb.chen.urights.dao.model.UrightsUser;
import cxb.chen.urights.rpc.api.UrightsApiService;
import org.apache.commons.lang.StringUtils;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 用户认证和授权
 * @author chen
 */
public class UrightsRealm extends AuthorizingRealm {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsRealm.class);

    @Autowired
    private UrightsApiService urightsApiService;

    /**
     * 授权：验证权限时调用
     * @param principalCollection
     * @return
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        String username = (String) principalCollection.getPrimaryPrincipal();
        UrightsUser urightsUser = urightsApiService.selectUrightsUserByUsername(username);

        // 当前用户所有角色
        List<UrightsRole> UrightsRoles = urightsApiService.selectUrightsRoleByUrightsUserId(urightsUser.getUserId());
        Set<String> roles = new HashSet<>();
        for (UrightsRole urightsRole : UrightsRoles) {
            if (StringUtils.isNotBlank(urightsRole.getName())) {
                roles.add(urightsRole.getName());
            }
        }

        // 当前用户所有权限
        List<UrightsPermission> UrightsPermissions = urightsApiService.selectUrightsPermissionByUrightsUserId(urightsUser.getUserId());
        Set<String> permissions = new HashSet<>();
        for (UrightsPermission urightsPermission : UrightsPermissions) {
            if (StringUtils.isNotBlank(urightsPermission.getPermissionValue())) {
                permissions.add(urightsPermission.getPermissionValue());
            }
        }

        SimpleAuthorizationInfo simpleAuthorizationInfo = new SimpleAuthorizationInfo();
        simpleAuthorizationInfo.setStringPermissions(permissions);
        simpleAuthorizationInfo.setRoles(roles);
        return simpleAuthorizationInfo;
    }

    /**
     * 认证：登录时调用
     * @param authenticationToken
     * @return
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        String username = (String) authenticationToken.getPrincipal();
        String password = new String((char[]) authenticationToken.getCredentials());
        // client无密认证
        String urightsType = PropertiesFileUtil.getInstance("oneSr-urights-client").get("oneSr.urights.type");
        if ("client".equals(urightsType)) {
            return new SimpleAuthenticationInfo(username, password, getName());
        }

        // 查询用户信息
        UrightsUser urightsUser = urightsApiService.selectUrightsUserByUsername(username);

        if (null == urightsUser) {
            throw new UnknownAccountException();
        }
        if (!urightsUser.getPassword().equals(MD5Util.md5(password + urightsUser.getSalt()))) {
            throw new IncorrectCredentialsException();
        }
        if (urightsUser.getLocked() == 1) {
            throw new LockedAccountException();
        }

        return new SimpleAuthenticationInfo(username, password, getName());
    }

}
