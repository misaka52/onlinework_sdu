package com.sdu.onlinework.mapper;

import com.sdu.onlinework.pojo.SW;
import com.sdu.onlinework.pojo.SWExample;
import java.util.List;

public interface SWMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SW record);

    int insertSelective(SW record);

    List<SW> selectByExample(SWExample example);

    SW selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SW record);

    int updateByPrimaryKey(SW record);
}