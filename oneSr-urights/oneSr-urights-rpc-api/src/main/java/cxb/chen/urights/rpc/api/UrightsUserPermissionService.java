package cxb.chen.urights.rpc.api;

import com.alibaba.fastjson.JSONArray;
import cxb.chen.common.base.BaseService;
import cxb.chen.urights.dao.model.UrightsUserPermission;
import cxb.chen.urights.dao.model.UrightsUserPermissionExample;

/**
* UpmsUserPermissionService接口
* @author chen
*/
public interface UrightsUserPermissionService extends BaseService<UrightsUserPermission, UrightsUserPermissionExample> {

    /**
     * 用户权限
     * @param datas 权限数据
     * @param id 用户id
     * @return
     */
    int permission(JSONArray datas, int id);

}