package com.sdu.onlinework.service.impl;

import com.sdu.onlinework.mapper.CourseMapper;
import com.sdu.onlinework.mapper.SCMapper;
import com.sdu.onlinework.mapper.TCMapper;
import com.sdu.onlinework.pojo.*;
import com.sdu.onlinework.pojo.Class;
import com.sdu.onlinework.service.CourseService;
import com.sdu.onlinework.service.TCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TCServiceImpl implements TCService {
    @Autowired
    TCMapper tcMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    CourseService courseService;

    @Override
    public void add(TC tc) {
        tcMapper.insert(tc);
    }

    @Override
    public void delete(TC tc) {
        tcMapper.deleteByPrimaryKey(tc.getId());
    }

    @Override
    public void update(TC tc) {
        tcMapper.updateByPrimaryKeySelective(tc);
    }

    @Override
    public TC get(int id) {
        return tcMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Course> getCourseByTid(int tid) {
        TCExample example = new TCExample();
        example.createCriteria().andTidEqualTo(tid);
        List<TC> tcs = tcMapper.selectByExample(example);

        List<Course> cs = new ArrayList<>();
        for(TC tc : tcs) {
            Course course = courseMapper.selectByPrimaryKey(tc.getCid());
            courseService.fillCntOfStudent(course);
            cs.add(course);
        }
        return cs;
    }

    @Override
    public List<TC> list() {
        TCExample example = new TCExample();
        example.setOrderByClause("id desc");
        return tcMapper.selectByExample(example);
    }
}
