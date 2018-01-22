package cxb.chen.urights.rpc.api;


import cxb.chen.common.base.BaseService;
import cxb.chen.urights.dao.model.UrightsUserOrganization;
import cxb.chen.urights.dao.model.UrightsUserOrganizationExample;

/**
* UpmsUserOrganizationService接口
* @author chen
*/
public interface UrightsUserOrganizationService extends BaseService<UrightsUserOrganization, UrightsUserOrganizationExample> {

    /**
     * 用户组织
     * @param organizationIds 组织ids
     * @param id 用户id
     * @return
     */
    int organization(String[] organizationIds, int id);

}