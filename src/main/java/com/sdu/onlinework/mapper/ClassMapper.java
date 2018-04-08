package com.sdu.onlinework.mapper;

import com.sdu.onlinework.pojo.Class;
import com.sdu.onlinework.pojo.ClassExample;
import java.util.List;

public interface ClassMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Class record);

    int insertSelective(Class record);

    List<Class> selectByExample(ClassExample example);

    Class selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Class record);

    int updateByPrimaryKey(Class record);
}