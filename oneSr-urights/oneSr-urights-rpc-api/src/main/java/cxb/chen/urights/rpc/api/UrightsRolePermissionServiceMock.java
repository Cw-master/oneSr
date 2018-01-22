package cxb.chen.urights.rpc.api;

import com.alibaba.fastjson.JSONArray;
import cxb.chen.common.base.BaseServiceMock;
import cxb.chen.urights.dao.mapper.UrightsRolePermissionMapper;
import cxb.chen.urights.dao.model.UrightsRolePermission;
import cxb.chen.urights.dao.model.UrightsRolePermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsRolePermissionService接口
* Created by shuzheng on 2017/3/20.
*/
public class UrightsRolePermissionServiceMock extends BaseServiceMock<UrightsRolePermissionMapper, UrightsRolePermission, UrightsRolePermissionExample> implements UrightsRolePermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsRolePermissionServiceMock.class);

    @Override
    public int rolePermission(JSONArray datas, int id) {
        LOGGER.info("UrightsRolePermissionServiceMock => rolePermission");
        return 0;
    }

}
