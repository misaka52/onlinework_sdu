package com.sdu.onlinework.mapper;

import com.sdu.onlinework.pojo.Work;
import com.sdu.onlinework.pojo.WorkExample;
import java.util.List;

public interface WorkMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Work record);

    int insertSelective(Work record);

    List<Work> selectByExample(WorkExample example);

    Work selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Work record);

    int updateByPrimaryKey(Work record);
}