package cxb.chen.urights.rpc.service.impl;

import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsUserRoleMapper;
import cxb.chen.urights.dao.model.UrightsUserRole;
import cxb.chen.urights.dao.model.UrightsUserRoleExample;
import cxb.chen.urights.rpc.api.UrightsUserRoleService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UrightsUserRoleService实现
* @author chen
*/
@Service
@Transactional
@BaseService
public class UrightsUserRoleServiceImpl extends BaseServiceImpl<UrightsUserRoleMapper, UrightsUserRole, UrightsUserRoleExample> implements UrightsUserRoleService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsUserRoleServiceImpl.class);

    @Autowired
    UrightsUserRoleMapper urightsUserRoleMapper;

    @Override
    public int role(String[] roleIds, int id) {
        int result = 0;
        // 删除旧记录
        UrightsUserRoleExample urightsUserRoleExample = new UrightsUserRoleExample();
        urightsUserRoleExample.createCriteria()
                .andUserIdEqualTo(id);
        urightsUserRoleMapper.deleteByExample(urightsUserRoleExample);
        // 增加新记录
        if (null != roleIds) {
            for (String roleId : roleIds) {
                if (StringUtils.isBlank(roleId)) {
                    continue;
                }
                UrightsUserRole urightsUserRole = new UrightsUserRole();
                urightsUserRole.setUserId(id);
                urightsUserRole.setRoleId(NumberUtils.toInt(roleId));
                result = urightsUserRoleMapper.insertSelective(urightsUserRole);
            }
        }
        return result;
    }

}