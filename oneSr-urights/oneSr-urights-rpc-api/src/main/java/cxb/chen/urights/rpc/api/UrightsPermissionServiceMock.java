package cxb.chen.urights.rpc.api;

import com.alibaba.fastjson.JSONArray;
import cxb.chen.common.base.BaseServiceMock;
import cxb.chen.urights.dao.mapper.UrightsPermissionMapper;
import cxb.chen.urights.dao.model.UrightsPermission;
import cxb.chen.urights.dao.model.UrightsPermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsPermissionService接口
* @author chen
*/
public class UrightsPermissionServiceMock extends BaseServiceMock<UrightsPermissionMapper, UrightsPermission, UrightsPermissionExample> implements UrightsPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsPermissionServiceMock.class);

    @Override
    public JSONArray getTreeByRoleId(Integer roleId) {
        LOGGER.info("UrightsPermissionServiceMock => getTreeByRoleId");
        return null;
    }

    @Override
    public JSONArray getTreeByUserId(Integer usereId, Byte type) {
        LOGGER.info("UrightsPermissionServiceMock => getTreeByUserId");
        return null;
    }

}
