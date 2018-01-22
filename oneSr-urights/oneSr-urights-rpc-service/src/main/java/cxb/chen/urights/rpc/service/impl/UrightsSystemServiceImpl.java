package cxb.chen.urights.rpc.service.impl;

import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsSystemMapper;
import cxb.chen.urights.dao.model.UrightsSystem;
import cxb.chen.urights.dao.model.UrightsSystemExample;
import cxb.chen.urights.rpc.api.UrightsSystemService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
* UrightsSystemService实现
* @author chen 
*/
@Service
@Transactional
@BaseService
public class UrightsSystemServiceImpl extends BaseServiceImpl<UrightsSystemMapper, UrightsSystem, UrightsSystemExample> implements UrightsSystemService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsSystemServiceImpl.class);

    @Autowired
    UrightsSystemMapper urightsSystemMapper;

    @Override
    public UrightsSystem selectUrightsSystemByName(String name) {
        UrightsSystemExample UrightsSystemExample = new UrightsSystemExample();
        UrightsSystemExample.createCriteria()
                .andNameEqualTo(name);
        List<UrightsSystem> UrightsSystems = urightsSystemMapper.selectByExample(UrightsSystemExample);
        if (null != UrightsSystems && UrightsSystems.size() > 0) {
            return UrightsSystems.get(0);
        }
        return null;
    }

}