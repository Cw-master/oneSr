package cxb.chen.urights.rpc.api;


import cxb.chen.common.base.BaseServiceMock;
import cxb.chen.urights.dao.mapper.UrightsUserOrganizationMapper;
import cxb.chen.urights.dao.model.UrightsUserOrganization;
import cxb.chen.urights.dao.model.UrightsUserOrganizationExample;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
* 降级实现UpmsUserOrganizationService接口
* @author chen
*/
public class UrightsUserOrganizationServiceMock extends BaseServiceMock<UrightsUserOrganizationMapper, UrightsUserOrganization, UrightsUserOrganizationExample> implements UrightsUserOrganizationService {

    private static final Logger LOGGER = LoggerFactory.getLogger(UrightsUserOrganizationServiceMock.class);

    @Override
    public int organization(String[] organizationIds, int id) {
        LOGGER.info("UrightsUserOrganizationServiceMock => organization");
        return 0;
    }

}
