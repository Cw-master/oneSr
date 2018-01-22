package cxb.chen.urights.rpc.api;


import cxb.chen.urights.dao.model.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

/**
 * 降级实现UrightsApiService接口
 * @author chen 
 */
public class UrightsApiServiceMock implements UrightsApiService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsApiServiceMock.class);

    @Override
    public List<UrightsPermission> selectUrightsPermissionByUrightsUserId(Integer UrightsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUrightsPermissionByUrightsUserId");
        return null;
    }

    @Override
    public List<UrightsPermission> selectUrightsPermissionByUrightsUserIdByCache(Integer UrightsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUrightsPermissionByUrightsUserIdByCache");
        return null;
    }

    @Override
    public List<UrightsRole> selectUrightsRoleByUrightsUserId(Integer UrightsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUrightsRoleByUrightsUserId");
        return null;
    }

    @Override
    public List<UrightsRole> selectUrightsRoleByUrightsUserIdByCache(Integer UrightsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUrightsRoleByUrightsUserIdByCache");
        return null;
    }

    @Override
    public List<UrightsRolePermission> selectUrightsRolePermisstionByUrightsRoleId(Integer UrightsRoleId) {
        LOGGER.info("UrightsApiServiceMock => selectUrightsRolePermisstionByUrightsRoleId");
        return null;
    }

    @Override
    public List<UrightsUserPermission> selectUrightsUserPermissionByUrightsUserId(Integer UrightsUserId) {
        LOGGER.info("UrightsApiServiceMock => selectUrightsUserPermissionByUrightsUserId");
        return null;
    }

    @Override
    public List<UrightsSystem> selectUrightsSystemByExample(UrightsSystemExample UrightsSystemExample) {
        LOGGER.info("UrightsApiServiceMock => selectUrightsSystemByExample");
        return null;
    }

    @Override
    public List<UrightsOrganization> selectUrightsOrganizationByExample(UrightsOrganizationExample UrightsOrganizationExample) {
        LOGGER.info("UrightsApiServiceMock => selectUrightsOrganizationByExample");
        return null;
    }

    @Override
    public UrightsUser selectUrightsUserByUsername(String username) {
        LOGGER.info("UrightsApiServiceMock => selectUrightsUserByUsername");
        return null;
    }

    @Override
    public int insertUrightsLogSelective(UrightsLog record) {
        LOGGER.info("UrightsApiServiceMock => insertSelective");
        return 0;
    }

}
