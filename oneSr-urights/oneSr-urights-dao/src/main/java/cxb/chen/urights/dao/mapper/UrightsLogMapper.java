package cxb.chen.urights.dao.mapper;

import cxb.chen.urights.dao.model.UrightsLog;
import cxb.chen.urights.dao.model.UrightsLogExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UrightsLogMapper {
    long countByExample(UrightsLogExample example);

    int deleteByExample(UrightsLogExample example);

    int deleteByPrimaryKey(Integer logId);

    int insert(UrightsLog record);

    int insertSelective(UrightsLog record);

    List<UrightsLog> selectByExampleWithBLOBs(UrightsLogExample example);

    List<UrightsLog> selectByExample(UrightsLogExample example);

    UrightsLog selectByPrimaryKey(Integer logId);

    int updateByExampleSelective(@Param("record") UrightsLog record, @Param("example") UrightsLogExample example);

    int updateByExampleWithBLOBs(@Param("record") UrightsLog record, @Param("example") UrightsLogExample example);

    int updateByExample(@Param("record") UrightsLog record, @Param("example") UrightsLogExample example);

    int updateByPrimaryKeySelective(UrightsLog record);

    int updateByPrimaryKeyWithBLOBs(UrightsLog record);

    int updateByPrimaryKey(UrightsLog record);
}