package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsUserPermission;
import cxb.chen.urights.dao.model.UrightsUserPermissionExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsUserPermissionMapper {
    long countByExample(UrightsUserPermissionExample example);

    int deleteByExample(UrightsUserPermissionExample example);

    int deleteByPrimaryKey(Integer userPermissionId);

    int insert(UrightsUserPermission record);

    int insertSelective(UrightsUserPermission record);

    List<UrightsUserPermission> selectByExample(UrightsUserPermissionExample example);

    UrightsUserPermission selectByPrimaryKey(Integer userPermissionId);

    int updateByExampleSelective(@Param("record") UrightsUserPermission record, @Param("example") UrightsUserPermissionExample example);

    int updateByExample(@Param("record") UrightsUserPermission record, @Param("example") UrightsUserPermissionExample example);

    int updateByPrimaryKeySelective(UrightsUserPermission record);

    int updateByPrimaryKey(UrightsUserPermission record);
}