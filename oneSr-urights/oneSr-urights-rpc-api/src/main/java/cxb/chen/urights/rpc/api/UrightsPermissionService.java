package cxb.chen.urights.rpc.api;

import com.alibaba.fastjson.JSONArray;
import cxb.chen.common.base.BaseService;
import cxb.chen.urights.dao.model.UrightsPermission;
import cxb.chen.urights.dao.model.UrightsPermissionExample;

/**
* UpmsPermissionService接口
* Created by shuzheng on 2017/3/20.
*/
public interface UrightsPermissionService extends BaseService<UrightsPermission, UrightsPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}