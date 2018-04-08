package com.sdu.onlinework.mapper;

import com.sdu.onlinework.pojo.TC;
import com.sdu.onlinework.pojo.TCExample;
import java.util.List;

public interface TCMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(TC record);

    int insertSelective(TC record);

    List<TC> selectByExample(TCExample example);

    TC selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(TC record);

    int updateByPrimaryKey(TC record);
}