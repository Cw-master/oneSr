package cxb.chen.urights.rpc.api;


import cxb.chen.common.base.BaseServiceMock;
import cxb.chen.urights.dao.mapper.UrightsSystemMapper;
import cxb.chen.urights.dao.model.UrightsSystem;
import cxb.chen.urights.dao.model.UrightsSystemExample;

/**
* 降级实现UpmsSystemService接口
* Created by shuzheng on 2017/3/20.
*/
public class UrightsSystemServiceMock extends BaseServiceMock<UrightsSystemMapper, UrightsSystem, UrightsSystemExample> implements UrightsSystemService {

    @Override
    public UrightsSystem selectUpmsSystemByName(String name) {
        return null;
    }

}
