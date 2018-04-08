package com.sdu.onlinework.service.impl;

import com.sdu.onlinework.mapper.TeacherMapper;
import com.sdu.onlinework.pojo.Teacher;
import com.sdu.onlinework.pojo.TeacherExample;
import com.sdu.onlinework.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherServiceImpl implements TeacherService {
    @Autowired
    TeacherMapper teacherMapper;
    @Override
    public void add(Teacher teacher) {
        teacherMapper.insertSelective(teacher);
    }

    @Override
    public void delete(Teacher teacher) {
        teacherMapper.deleteByPrimaryKey(teacher.getId());
    }

    @Override
    public void update(Teacher teacher) {
        teacherMapper.updateByPrimaryKeySelective(teacher);
    }

    @Override
    public Teacher get(int id) {
        return teacherMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Teacher> list() {
        TeacherExample example = new TeacherExample();
        example.setOrderByClause("id desc");
        return teacherMapper.selectByExample(example);
    }

    @Override
    public Teacher get(String tno, String password) {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andTnoEqualTo(tno).andPasswordEqualTo(password);
        List<Teacher> result = teacherMapper.selectByExample(example);
        if(result == null || result.size() == 0)
            return null;
        return result.get(0);
    }

    @Override
    public Teacher getByTno(String tno) {
        TeacherExample example = new TeacherExample();
        example.createCriteria().andTnoEqualTo(tno);
        List<Teacher> ts = teacherMapper.selectByExample(example);
        if(ts == null || ts.size() <= 0)
            return null;
        return ts.get(0);
    }
}
