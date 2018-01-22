package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsUserRole;
import cxb.chen.urights.dao.model.UrightsUserRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsUserRoleMapper {
    long countByExample(UrightsUserRoleExample example);

    int deleteByExample(UrightsUserRoleExample example);

    int deleteByPrimaryKey(Integer userRoleId);

    int insert(UrightsUserRole record);

    int insertSelective(UrightsUserRole record);

    List<UrightsUserRole> selectByExample(UrightsUserRoleExample example);

    UrightsUserRole selectByPrimaryKey(Integer userRoleId);

    int updateByExampleSelective(@Param("record") UrightsUserRole record, @Param("example") UrightsUserRoleExample example);

    int updateByExample(@Param("record") UrightsUserRole record, @Param("example") UrightsUserRoleExample example);

    int updateByPrimaryKeySelective(UrightsUserRole record);

    int updateByPrimaryKey(UrightsUserRole record);
}