package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsOrganization;
import cxb.chen.urights.dao.model.UrightsOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsOrganizationMapper {
    long countByExample(UrightsOrganizationExample example);

    int deleteByExample(UrightsOrganizationExample example);

    int deleteByPrimaryKey(Integer organizationId);

    int insert(UrightsOrganization record);

    int insertSelective(UrightsOrganization record);

    List<UrightsOrganization> selectByExample(UrightsOrganizationExample example);

    UrightsOrganization selectByPrimaryKey(Integer organizationId);

    int updateByExampleSelective(@Param("record") UrightsOrganization record, @Param("example") UrightsOrganizationExample example);

    int updateByExample(@Param("record") UrightsOrganization record, @Param("example") UrightsOrganizationExample example);

    int updateByPrimaryKeySelective(UrightsOrganization record);

    int updateByPrimaryKey(UrightsOrganization record);
}