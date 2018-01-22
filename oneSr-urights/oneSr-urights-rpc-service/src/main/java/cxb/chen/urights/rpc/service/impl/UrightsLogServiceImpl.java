package cxb.chen.urights.rpc.service.impl;

import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsLogMapper;
import cxb.chen.urights.dao.model.UrightsLog;
import cxb.chen.urights.dao.model.UrightsLogExample;
import cxb.chen.urights.rpc.api.UrightsLogService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UrightsLogService实现
* @author chen
*/
@Service
@Transactional
@BaseService
public class UrightsLogServiceImpl extends BaseServiceImpl<UrightsLogMapper, UrightsLog, UrightsLogExample> implements UrightsLogService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsLogServiceImpl.class);

    @Autowired
    UrightsLogMapper urightsLogMapper;

}