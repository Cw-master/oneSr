package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsRolePermission;
import cxb.chen.urights.dao.model.UrightsRolePermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsRolePermissionMapper {
    long countByExample(UrightsRolePermissionExample example);

    int deleteByExample(UrightsRolePermissionExample example);

    int deleteByPrimaryKey(Integer rolePermissionId);

    int insert(UrightsRolePermission record);

    int insertSelective(UrightsRolePermission record);

    List<UrightsRolePermission> selectByExample(UrightsRolePermissionExample example);

    UrightsRolePermission selectByPrimaryKey(Integer rolePermissionId);

    int updateByExampleSelective(@Param("record") UrightsRolePermission record, @Param("example") UrightsRolePermissionExample example);

    int updateByExample(@Param("record") UrightsRolePermission record, @Param("example") UrightsRolePermissionExample example);

    int updateByPrimaryKeySelective(UrightsRolePermission record);

    int updateByPrimaryKey(UrightsRolePermission record);
}