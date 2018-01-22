package cxb.chen.urights.rpc.service.impl;


import cxb.chen.urights.dao.mapper.*;
import cxb.chen.urights.dao.model.*;
import cxb.chen.urights.rpc.api.UrightsApiService;
import cxb.chen.urights.rpc.mapper.UrightsApiMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * UpmsApiService实现
 * Created by shuzheng on 2016/01/19.
 */
@Service
@Transactional
public class UrightsApiServiceImpl implements UrightsApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsApiServiceImpl.class);

    @Autowired
    UrightsUserMapper urightsUserMapper;

    @Autowired
    UrightsApiMapper urightsApiMapper;

    @Autowired
    UrightsRolePermissionMapper urightsRolePermissionMapper;

    @Autowired
    UrightsUserPermissionMapper urightsUserPermissionMapper;

    @Autowired
    UrightsSystemMapper urightsSystemMapper;

    @Autowired
    UrightsOrganizationMapper urightsOrganizationMapper;

    @Autowired
    UrightsLogMapper urightsLogMapper;

    /**
     * 根据用户id获取所拥有的权限
     * @param urightsUserId
     * @return
     */
    @Override
    public List<UrightsPermission> selectUrightsPermissionByUrightsUserId(Integer urightsUserId) {
        // 用户不存在或锁定状态
        UrightsUser upmsUser = urightsUserMapper.selectByPrimaryKey(urightsUserId);
        if (null == upmsUser || 1 == upmsUser.getLocked()) {
            LOGGER.info("selectUrightsPermissionByUrightsUserId : urightsUserId={}", urightsUserId);
            return null;
        }
        List<UrightsPermission> urightsPermissions = urightsApiMapper.selectUrightsPermissionByUrightsUserId(urightsUserId);
        return urightsPermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param urightsUserId
     * @return
     */
    @Override
    @Cacheable(value = "oneSr-urights-rpc-service-ehcache", key = "'selectUrightsPermissionByUrightsUserId_' + #urightsUserId")
    public List<UrightsPermission> selectUrightsPermissionByUrightsUserIdByCache(Integer urightsUserId) {
        return selectUrightsPermissionByUrightsUserId(urightsUserId);
    }

    /**
     * 根据用户id获取所属的角色
     * @param urightsUserId
     * @return
     */
    @Override
    public List<UrightsRole> selectUrightsRoleByUrightsUserId(Integer urightsUserId) {
        // 用户不存在或锁定状态
        UrightsUser urightsUser = urightsUserMapper.selectByPrimaryKey(urightsUserId);
        if (null == urightsUser || 1 == urightsUser.getLocked()) {
            LOGGER.info("selectUrightsRoleByUrightsUserId : urightsUserId={}", urightsUserId);
            return null;
        }
        List<UrightsRole> urightsRoles = urightsApiMapper.selectUrightsRoleByUrightsUserId(urightsUserId);
        return urightsRoles;
    }

    /**
     * 根据用户id获取所属的角色
     * @param urightsUserId
     * @return
     */
    @Override
    @Cacheable(value = "oneSr-urights-rpc-service-ehcache", key = "'selectUrightsRoleByUrightsUserId_' + #urightsUserId")
    public List<UrightsRole> selectUrightsRoleByUrightsUserIdByCache(Integer urightsUserId) {
        return selectUrightsRoleByUrightsUserId(urightsUserId);
    }

    /**
     * 根据角色id获取所拥有的权限
     * @param urightsRoleId
     * @return
     */
    @Override
    public List<UrightsRolePermission> selectUrightsRolePermisstionByUrightsRoleId(Integer urightsRoleId) {
        UrightsRolePermissionExample urightsRolePermissionExample = new UrightsRolePermissionExample();
        urightsRolePermissionExample.createCriteria()
                .andRoleIdEqualTo(urightsRoleId);
        List<UrightsRolePermission> upmsRolePermissions = urightsRolePermissionMapper.selectByExample(urightsRolePermissionExample);
        return upmsRolePermissions;
    }

    /**
     * 根据用户id获取所拥有的权限
     * @param urightsUserId
     * @return
     */
    @Override
    public List<UrightsUserPermission> selectUrightsUserPermissionByUrightsUserId(Integer urightsUserId) {
        UrightsUserPermissionExample urightsUserPermissionExample = new UrightsUserPermissionExample();
        urightsUserPermissionExample.createCriteria()
                .andUserIdEqualTo(urightsUserId);
        List<UrightsUserPermission> urightsUserPermissions = urightsUserPermissionMapper.selectByExample(urightsUserPermissionExample);
        return urightsUserPermissions;
    }

    /**
     * 根据条件获取系统数据
     * @param urightsSystemExample
     * @return
     */
    @Override
    public List<UrightsSystem> selectUrightsSystemByExample(UrightsSystemExample urightsSystemExample) {
        return urightsSystemMapper.selectByExample(urightsSystemExample);
    }

    /**
     * 根据条件获取组织数据
     * @param urightsOrganizationExample
     * @return
     */
    @Override
    public List<UrightsOrganization> selectUrightsOrganizationByExample(UrightsOrganizationExample urightsOrganizationExample) {
        return urightsOrganizationMapper.selectByExample(urightsOrganizationExample);
    }

    /**
     * 根据username获取UrightsUser
     * @param username
     * @return
     */
    @Override
    public UrightsUser selectUrightsUserByUsername(String username) {
        UrightsUserExample urightsUserExample = new UrightsUserExample();
        urightsUserExample.createCriteria()
                .andUsernameEqualTo(username);
        List<UrightsUser> upmsUsers = urightsUserMapper.selectByExample(urightsUserExample);
        if (null != upmsUsers && upmsUsers.size() > 0) {
            return upmsUsers.get(0);
        }
        return null;
    }

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    @Override
    public int insertUrightsLogSelective(UrightsLog record) {
        return urightsLogMapper.insertSelective(record);
    }

}