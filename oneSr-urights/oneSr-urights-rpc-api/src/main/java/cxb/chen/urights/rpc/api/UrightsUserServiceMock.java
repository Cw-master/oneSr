package cxb.chen.urights.rpc.api;


import cxb.chen.common.base.BaseServiceMock;
import cxb.chen.urights.dao.mapper.UrightsUserMapper;
import cxb.chen.urights.dao.model.UrightsUser;
import cxb.chen.urights.dao.model.UrightsUserExample;

/**
* 降级实现UpmsUserService接口
* @author chen
*/
public class UrightsUserServiceMock extends BaseServiceMock<UrightsUserMapper, UrightsUser, UrightsUserExample> implements UrightsUserService {

    @Override
    public UrightsUser createUser(UrightsUser upmsUser) {
        return null;
    }

}
