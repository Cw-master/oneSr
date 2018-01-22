package cxb.chen.urights.rpc.api;


import cxb.chen.common.base.BaseServiceMock;
import cxb.chen.urights.dao.mapper.UrightsSystemMapper;
import cxb.chen.urights.dao.model.UrightsSystem;
import cxb.chen.urights.dao.model.UrightsSystemExample;

/**
* 降级实现UrightsSystemService接口
* @author chen
*/
public class UrightsSystemServiceMock extends BaseServiceMock<UrightsSystemMapper, UrightsSystem, UrightsSystemExample> implements UrightsSystemService {

    @Override
    public UrightsSystem selectUrightsSystemByName(String name) {
        return null;
    }

}
