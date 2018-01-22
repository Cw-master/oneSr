package cxb.chen.urights.rpc.api;

import com.alibaba.fastjson.JSONArray;
import cxb.chen.common.base.BaseServiceMock;
import cxb.chen.urights.dao.mapper.UrightsUserPermissionMapper;
import cxb.chen.urights.dao.model.UrightsUserPermission;
import cxb.chen.urights.dao.model.UrightsUserPermissionExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsUserPermissionService接口
* @author chen
*/
public class UrightsUserPermissionServiceMock extends BaseServiceMock<UrightsUserPermissionMapper, UrightsUserPermission, UrightsUserPermissionExample> implements UrightsUserPermissionService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsUserPermissionServiceMock.class);

    @Override
    public int permission(JSONArray datas, int id) {
        LOGGER.info("UrightsUserPermissionServiceMock => permission");
        return 0;
    }

}
