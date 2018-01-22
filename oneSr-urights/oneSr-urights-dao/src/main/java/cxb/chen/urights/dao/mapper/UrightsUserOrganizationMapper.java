package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsUserOrganization;
import cxb.chen.urights.dao.model.UrightsUserOrganizationExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsUserOrganizationMapper {
    long countByExample(UrightsUserOrganizationExample example);

    int deleteByExample(UrightsUserOrganizationExample example);

    int deleteByPrimaryKey(Integer userOrganizationId);

    int insert(UrightsUserOrganization record);

    int insertSelective(UrightsUserOrganization record);

    List<UrightsUserOrganization> selectByExample(UrightsUserOrganizationExample example);

    UrightsUserOrganization selectByPrimaryKey(Integer userOrganizationId);

    int updateByExampleSelective(@Param("record") UrightsUserOrganization record, @Param("example") UrightsUserOrganizationExample example);

    int updateByExample(@Param("record") UrightsUserOrganization record, @Param("example") UrightsUserOrganizationExample example);

    int updateByPrimaryKeySelective(UrightsUserOrganization record);

    int updateByPrimaryKey(UrightsUserOrganization record);
}