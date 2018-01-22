package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsSystem;
import cxb.chen.urights.dao.model.UrightsSystemExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsSystemMapper {
    long countByExample(UrightsSystemExample example);

    int deleteByExample(UrightsSystemExample example);

    int deleteByPrimaryKey(Integer systemId);

    int insert(UrightsSystem record);

    int insertSelective(UrightsSystem record);

    List<UrightsSystem> selectByExample(UrightsSystemExample example);

    UrightsSystem selectByPrimaryKey(Integer systemId);

    int updateByExampleSelective(@Param("record") UrightsSystem record, @Param("example") UrightsSystemExample example);

    int updateByExample(@Param("record") UrightsSystem record, @Param("example") UrightsSystemExample example);

    int updateByPrimaryKeySelective(UrightsSystem record);

    int updateByPrimaryKey(UrightsSystem record);
}