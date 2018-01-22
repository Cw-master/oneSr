package cxb.chen.urights.rpc.api;



import cxb.chen.urights.dao.model.*;

import java.util.List;

/**
 * upms系统接口
 * @author chen
 */
public interface UrightsApiService {

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param urightsUserId
     * @return
     */
    List<UrightsPermission> selectUrightsPermissionByUrightsUserId(Integer urightsUserId);

    /**
     * 根据用户id获取所拥有的权限(用户和角色权限合集)
     * @param urightsUserId
     * @return
     */
    List<UrightsPermission> selectUrightsPermissionByUrightsUserIdByCache(Integer urightsUserId);

    /**
     * 根据用户id获取所属的角色
     * @param urightsUserId
     * @return
     */
    List<UrightsRole> selectUrightsRoleByUrightsUserId(Integer urightsUserId);

    /**
     * 根据用户id获取所属的角色
     * @param urightsUserId
     * @return
     */
    List<UrightsRole> selectUrightsRoleByUrightsUserIdByCache(Integer urightsUserId);

    /**
     * 根据角色id获取所拥有的权限
     * @param urightsRoleId
     * @return
     */
    List<UrightsRolePermission> selectUrightsRolePermisstionByUrightsRoleId(Integer urightsRoleId);

    /**
     * 根据用户id获取所拥有的权限
     * @param urightsUserId
     * @return
     */
    List<UrightsUserPermission> selectUrightsUserPermissionByUrightsUserId(Integer urightsUserId);

    /**
     * 根据条件获取系统数据
     * @param urigthsSystemExample
     * @return
     */
    List<UrightsSystem> selectUrightsSystemByExample(UrightsSystemExample urigthsSystemExample);

    /**
     * 根据条件获取组织数据
     * @param urightOrganizationExample
     * @return
     */
    List<UrightsOrganization> selectUrightsOrganizationByExample(UrightsOrganizationExample urightOrganizationExample);

    /**
     * 根据username获取UpmsUser
     * @param username
     * @return
     */
    UrightsUser selectUrightsUserByUsername(String username);

    /**
     * 写入操作日志
     * @param record
     * @return
     */
    int insertUrightsLogSelective(UrightsLog record);

}
