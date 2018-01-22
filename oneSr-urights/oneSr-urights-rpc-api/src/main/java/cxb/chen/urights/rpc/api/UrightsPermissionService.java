package cxb.chen.urights.rpc.api;

import com.alibaba.fastjson.JSONArray;
import cxb.chen.common.base.BaseService;
import cxb.chen.urights.dao.model.UrightsPermission;
import cxb.chen.urights.dao.model.UrightsPermissionExample;

/**
* UpmsPermissionService接口
* @author chen
*/
public interface UrightsPermissionService extends BaseService<UrightsPermission, UrightsPermissionExample> {

    JSONArray getTreeByRoleId(Integer roleId);

    JSONArray getTreeByUserId(Integer usereId, Byte type);

}