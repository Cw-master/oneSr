package cxb.chen.urights.rpc.api;

import com.alibaba.fastjson.JSONArray;
import cxb.chen.common.base.BaseService;
import cxb.chen.urights.dao.model.UrightsRolePermission;
import cxb.chen.urights.dao.model.UrightsRolePermissionExample;

/**
* UpmsRolePermissionService接口
* Created by shuzheng on 2017/3/20.
*/
public interface UrightsRolePermissionService extends BaseService<UrightsRolePermission, UrightsRolePermissionExample> {

    /**
     * 角色权限
     * @param datas 权限数据
     * @param id 角色id
     * @return
     */
    int rolePermission(JSONArray datas, int id);

}