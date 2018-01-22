package cxb.chen.urights.rpc.api;

import cxb.chen.common.base.BaseService;
import cxb.chen.urights.dao.model.UrightsUserRole;
import cxb.chen.urights.dao.model.UrightsUserRoleExample;

/**
* UpmsUserRoleService接口
* Created by shuzheng on 2017/3/20.
*/
public interface UrightsUserRoleService extends BaseService<UrightsUserRole, UrightsUserRoleExample> {

    /**
     * 用户角色
     * @param roleIds 角色ids
     * @param id 用户id
     * @return
     */
    int role(String[] roleIds, int id);

}