package cxb.chen.urights.rpc.api;


import cxb.chen.common.base.BaseService;
import cxb.chen.urights.dao.model.UrightsUser;
import cxb.chen.urights.dao.model.UrightsUserExample;

/**
* UpmsUserService接口
* @author chen
*/
public interface UrightsUserService extends BaseService<UrightsUser, UrightsUserExample> {

    UrightsUser createUser(UrightsUser upmsUser);

}