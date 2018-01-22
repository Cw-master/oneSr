package cxb.chen.urights.rpc.service.impl;

import cxb.chen.common.annotation.BaseService;
import cxb.chen.common.base.BaseServiceImpl;
import cxb.chen.urights.dao.mapper.UrightsUserOrganizationMapper;
import cxb.chen.urights.dao.model.UrightsUserOrganization;
import cxb.chen.urights.dao.model.UrightsUserOrganizationExample;
import cxb.chen.urights.rpc.api.UrightsUserOrganizationService;
import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang.math.NumberUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
* UrightsUserOrganizationService实现
* @author chen
*/
@Service
@Transactional
@BaseService
public class UrightsUserOrganizationServiceImpl extends BaseServiceImpl<UrightsUserOrganizationMapper, UrightsUserOrganization, UrightsUserOrganizationExample> implements UrightsUserOrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsUserOrganizationServiceImpl.class);

    @Autowired
    UrightsUserOrganizationMapper urightsUserOrganizationMapper;

    @Override
    public int organization(String[] organizationIds, int id) {
        int result = 0;
        // 删除旧记录
        UrightsUserOrganizationExample urightsUserOrganizationExample = new UrightsUserOrganizationExample();
        urightsUserOrganizationExample.createCriteria()
                .andUserIdEqualTo(id);
        urightsUserOrganizationMapper.deleteByExample(urightsUserOrganizationExample);
        // 增加新记录
        if (null != organizationIds) {
            for (String organizationId : organizationIds) {
                if (StringUtils.isBlank(organizationId)) {
                    continue;
                }
                UrightsUserOrganization urightsUserOrganization = new UrightsUserOrganization();
                urightsUserOrganization.setUserId(id);
                urightsUserOrganization.setOrganizationId(NumberUtils.toInt(organizationId));
                result = urightsUserOrganizationMapper.insertSelective(urightsUserOrganization);
            }
        }
        return result;
    }

}