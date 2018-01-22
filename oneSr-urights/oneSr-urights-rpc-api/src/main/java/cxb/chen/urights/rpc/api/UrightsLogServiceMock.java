package cxb.chen.urights.rpc.api;

import cxb.chen.common.base.BaseServiceMock;
import cxb.chen.urights.dao.mapper.UrightsLogMapper;
import cxb.chen.urights.dao.model.UrightsLog;
import cxb.chen.urights.dao.model.UrightsLogExample;

/**
* 降级实现UpmsLogService接口
* @author chen
*/
public class UrightsLogServiceMock extends BaseServiceMock<UrightsLogMapper, UrightsLog, UrightsLogExample> implements UrightsLogService {

}
