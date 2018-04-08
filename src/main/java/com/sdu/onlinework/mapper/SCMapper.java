package com.sdu.onlinework.mapper;

import com.sdu.onlinework.pojo.SC;
import com.sdu.onlinework.pojo.SCExample;
import java.util.List;

public interface SCMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(SC record);

    int insertSelective(SC record);

    List<SC> selectByExample(SCExample example);

    SC selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(SC record);

    int updateByPrimaryKey(SC record);

    int getCountByCid(Integer id);
}