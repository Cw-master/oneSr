package cxb.chen.urights.rpc.service.impl;


import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsRoleMapper;
import cxb.chen.urights.dao.model.UrightsRole;
import cxb.chen.urights.dao.model.UrightsRoleExample;
import cxb.chen.urights.rpc.api.UrightsRoleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UrightsRoleService实现
* @author chen
*/
@Service
@Transactional
@BaseService
public class UrightsRoleServiceImpl extends BaseServiceImpl<UrightsRoleMapper, UrightsRole, UrightsRoleExample> implements UrightsRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsRoleServiceImpl.class);

    @Autowired
    UrightsRoleMapper urightsRoleMapper;

}