package com.sdu.onlinework.service.impl;

import com.sdu.onlinework.mapper.ClassMapper;
import com.sdu.onlinework.pojo.Class;
import com.sdu.onlinework.pojo.ClassExample;
import com.sdu.onlinework.service.ClassService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClassServiceImpl implements ClassService {
    @Autowired
    ClassMapper classMapper;

    @Override
    public void add(Class class_) {
        classMapper.insertSelective(class_);
    }

    @Override
    public void delete(Class class_) {
        classMapper.deleteByPrimaryKey(class_.getId());
    }

    @Override
    public void update(Class class_) {
        classMapper.updateByPrimaryKeySelective(class_);
    }

    @Override
    public Class get(int id) {
        return classMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Class> list() {
        ClassExample example = new ClassExample();
        example.setOrderByClause("id desc");
        return classMapper.selectByExample(example);
    }
}
