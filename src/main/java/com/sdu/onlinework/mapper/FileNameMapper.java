package com.sdu.onlinework.mapper;

import com.sdu.onlinework.pojo.FileName;
import com.sdu.onlinework.pojo.FileNameExample;
import java.util.List;

public interface FileNameMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(FileName record);

    int insertSelective(FileName record);

    List<FileName> selectByExample(FileNameExample example);

    int getMax();
}