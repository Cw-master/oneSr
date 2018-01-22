package cxb.chen.urights.rpc.service.impl;


import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsOrganizationMapper;
import cxb.chen.urights.dao.model.UrightsOrganization;
import cxb.chen.urights.dao.model.UrightsOrganizationExample;
import cxb.chen.urights.rpc.api.UrightsOrganizationService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UpmsOrganizationService实现
* Created by shuzheng on 2017/3/20.
*/
@Service
@Transactional
@BaseService
public class UrightsOrganizationServiceImpl extends BaseServiceImpl<UrightsOrganizationMapper, UrightsOrganization, UrightsOrganizationExample> implements UrightsOrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsOrganizationServiceImpl.class);

    @Autowired
    UrightsOrganizationMapper urightsOrganizationMapper;

}