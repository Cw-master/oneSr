package cxb.chen.urights.rpc.api;

import cxb.chen.common.base.BaseServiceMock;
import cxb.chen.urights.dao.mapper.UrightsUserRoleMapper;
import cxb.chen.urights.dao.model.UrightsUserRole;
import cxb.chen.urights.dao.model.UrightsUserRoleExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsUserRoleService接口
* @author chen
*/
public class UrightsUserRoleServiceMock extends BaseServiceMock<UrightsUserRoleMapper, UrightsUserRole, UrightsUserRoleExample> implements UrightsUserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsUserRoleServiceMock.class);

    @Override
    public int role(String[] roleIds, int id) {
        LOGGER.info("UrightsUserRoleServiceMock => role");
        return 0;
    }

}
