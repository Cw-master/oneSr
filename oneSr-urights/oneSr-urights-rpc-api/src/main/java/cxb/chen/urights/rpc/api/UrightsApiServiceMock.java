package cxb.chen.urights.rpc.api;


import cxb.chen.urights.dao.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 降级实现UpmsApiService接口
 * Created by shuzheng on 2017/2/14.
 */
public class UrightsApiServiceMock implements UrightsApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsApiServiceMock.class);

    @Override
    public List<UrightsPermission> selectUpmsPermissionByUpmsUserId(Integer upmsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUpmsPermissionByUpmsUserId");
        return null;
    }

    @Override
    public List<UrightsPermission> selectUpmsPermissionByUpmsUserIdByCache(Integer upmsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUpmsPermissionByUpmsUserIdByCache");
        return null;
    }

    @Override
    public List<UrightsRole> selectUpmsRoleByUpmsUserId(Integer upmsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUpmsRoleByUpmsUserId");
        return null;
    }

    @Override
    public List<UrightsRole> selectUpmsRoleByUpmsUserIdByCache(Integer upmsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUpmsRoleByUpmsUserIdByCache");
        return null;
    }

    @Override
    public List<UrightsRolePermission> selectUpmsRolePermisstionByUpmsRoleId(Integer upmsRoleId) {
        LOGGER.info("UrightsApiServiceMock => selectUpmsRolePermisstionByUpmsRoleId");
        return null;
    }

    @Override
    public List<UrightsUserPermission> selectUpmsUserPermissionByUpmsUserId(Integer upmsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUpmsUserPermissionByUpmsUserId");
        return null;
    }

    @Override
    public List<UrightsSystem> selectUpmsSystemByExample(UrightsSystemExample upmsSystemExample) {
        LOGGER.info("UrightsApiServiceMock => selectUpmsSystemByExample");
        return null;
    }

    @Override
    public List<UrightsOrganization> selectUpmsOrganizationByExample(UrightsOrganizationExample upmsOrganizationExample) {
        LOGGER.info("UrightsApiServiceMock => selectUpmsOrganizationByExample");
        return null;
    }

    @Override
    public UrightsUser selectUpmsUserByUsername(String username) {
        LOGGER.info("UrightsApiServiceMock => selectUpmsUserByUsername");
        return null;
    }

    @Override
    public int insertUpmsLogSelective(UrightsLog record) {
        LOGGER.info("UrightsApiServiceMock => insertSelective");
        return 0;
    }

}
