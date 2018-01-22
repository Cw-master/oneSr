package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsUser;
import cxb.chen.urights.dao.model.UrightsUserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsUserMapper {
    long countByExample(UrightsUserExample example);

    int deleteByExample(UrightsUserExample example);

    int deleteByPrimaryKey(Integer userId);

    int insert(UrightsUser record);

    int insertSelective(UrightsUser record);

    List<UrightsUser> selectByExample(UrightsUserExample example);

    UrightsUser selectByPrimaryKey(Integer userId);

    int updateByExampleSelective(@Param("record") UrightsUser record, @Param("example") UrightsUserExample example);

    int updateByExample(@Param("record") UrightsUser record, @Param("example") UrightsUserExample example);

    int updateByPrimaryKeySelective(UrightsUser record);

    int updateByPrimaryKey(UrightsUser record);
}