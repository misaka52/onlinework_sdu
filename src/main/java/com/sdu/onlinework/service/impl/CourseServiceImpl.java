package com.sdu.onlinework.service.impl;

import com.sdu.onlinework.mapper.CourseMapper;
import com.sdu.onlinework.mapper.SCMapper;
import com.sdu.onlinework.mapper.StudentMapper;
import com.sdu.onlinework.pojo.*;
import com.sdu.onlinework.service.CourseService;
import com.sdu.onlinework.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    @Autowired
    SCMapper scMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    SCService scService;

    @Override
    public void add(Course course) {
        courseMapper.insert(course);
    }
    @Override
    public void delete(Course course) {
        courseMapper.deleteByPrimaryKey(course.getId());
    }

    @Override
    public void update(Course course) {
        courseMapper.updateByPrimaryKey(course);
    }

    @Override
    public Course get(int id) {
        return courseMapper.selectByPrimaryKey(id);
    }

    @Override
    public void fillCntOfStudent(Course course) {
        SCExample example = new SCExample();
        example.createCriteria().andCidEqualTo(course.getId());
        course.setCntOfStudent(scMapper.getCountByCid(course.getId()));
    }

    @Override
    public List<Course> list() {
        CourseExample example = new CourseExample();
        example.setOrderByClause("id desc");
        return courseMapper.selectByExample(example);
    }
}
