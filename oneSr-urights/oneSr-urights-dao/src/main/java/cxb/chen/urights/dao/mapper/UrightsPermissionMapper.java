package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsPermission;
import cxb.chen.urights.dao.model.UrightsPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsPermissionMapper {
    long countByExample(UrightsPermissionExample example);

    int deleteByExample(UrightsPermissionExample example);

    int deleteByPrimaryKey(Integer permissionId);

    int insert(UrightsPermission record);

    int insertSelective(UrightsPermission record);

    List<UrightsPermission> selectByExample(UrightsPermissionExample example);

    UrightsPermission selectByPrimaryKey(Integer permissionId);

    int updateByExampleSelective(@Param("record") UrightsPermission record, @Param("example") UrightsPermissionExample example);

    int updateByExample(@Param("record") UrightsPermission record, @Param("example") UrightsPermissionExample example);

    int updateByPrimaryKeySelective(UrightsPermission record);

    int updateByPrimaryKey(UrightsPermission record);
}