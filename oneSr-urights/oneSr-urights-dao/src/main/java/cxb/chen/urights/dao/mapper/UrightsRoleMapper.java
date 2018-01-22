package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsRole;
import cxb.chen.urights.dao.model.UrightsRoleExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsRoleMapper {
    long countByExample(UrightsRoleExample example);

    int deleteByExample(UrightsRoleExample example);

    int deleteByPrimaryKey(Integer roleId);

    int insert(UrightsRole record);

    int insertSelective(UrightsRole record);

    List<UrightsRole> selectByExample(UrightsRoleExample example);

    UrightsRole selectByPrimaryKey(Integer roleId);

    int updateByExampleSelective(@Param("record") UrightsRole record, @Param("example") UrightsRoleExample example);

    int updateByExample(@Param("record") UrightsRole record, @Param("example") UrightsRoleExample example);

    int updateByPrimaryKeySelective(UrightsRole record);

    int updateByPrimaryKey(UrightsRole record);
}