package cxb.chen.urights.rpc.api;


import cxb.chen.common.base.BaseService;
import cxb.chen.urights.dao.model.UrightsSystem;
import cxb.chen.urights.dao.model.UrightsSystemExample;

/**
* UpmsSystemService接口
* Created by shuzheng on 2017/3/20.
*/
public interface UrightsSystemService extends BaseService<UrightsSystem, UrightsSystemExample> {

    /**
     * 根据name获取UrightsSystem
     * @param name
     * @return
     */
    UrightsSystem selectUrightsSystemByName(String name);

}