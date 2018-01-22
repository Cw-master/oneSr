package cxb.chen.urights.rpc.service.impl;


import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsUserMapper;
import cxb.chen.urights.dao.model.UrightsUser;
import cxb.chen.urights.dao.model.UrightsUserExample;
import cxb.chen.urights.rpc.api.UrightsUserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UrightsUserService实现
* @author chen
*/
@Service
@Transactional
@BaseService
public class UrightsUserServiceImpl extends BaseServiceImpl<UrightsUserMapper, UrightsUser, UrightsUserExample> implements UrightsUserService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsUserServiceImpl.class);

    @Autowired
    UrightsUserMapper urightsUserMapper;

    @Override
    public UrightsUser createUser(UrightsUser urightsUser) {
        UrightsUserExample urightsUserExample = new UrightsUserExample();
        urightsUserExample.createCriteria()
                .andUsernameEqualTo(urightsUser.getUsername());
        long count = urightsUserMapper.countByExample(urightsUserExample);
        if (count > 0) {
            return null;
        }
        urightsUserMapper.insert(urightsUser);
        return urightsUser;
    }

}