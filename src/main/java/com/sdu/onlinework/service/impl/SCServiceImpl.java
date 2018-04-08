package com.sdu.onlinework.service.impl;

import com.sdu.onlinework.mapper.ClassMapper;
import com.sdu.onlinework.mapper.CourseMapper;
import com.sdu.onlinework.mapper.SCMapper;
import com.sdu.onlinework.mapper.TeacherMapper;
import com.sdu.onlinework.pojo.*;
import com.sdu.onlinework.pojo.Class;
import com.sdu.onlinework.service.SCService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SCServiceImpl implements SCService {
    @Autowired
    SCMapper scMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public void add(SC sc) {
        scMapper.insert(sc);
    }

    @Override
    public void delete(SC sc) {
        scMapper.deleteByPrimaryKey(sc.getId());
    }

    @Override
    public void update(SC sc) {
        scMapper.updateByPrimaryKeySelective(sc);
    }

    @Override
    public SC get(int id) {
        return scMapper.selectByPrimaryKey(id);
    }

    @Override
    public SC getByCidAndSid(int cid, int sid) {
        SCExample example = new SCExample();
        example.createCriteria().andCidEqualTo(cid).andSidEqualTo(sid);
        List<SC> scs = scMapper.selectByExample(example);
        if(scs == null || scs.size() == 0)
            return null;
        return scs.get(0);
    }

    @Override
    public List<SC> list() {
        SCExample example = new SCExample();
        example.setOrderByClause("id desc");
        return scMapper.selectByExample(example);
    }

    @Override
    public List<SC> listByCid(int cid) {
        SCExample example = new SCExample();
        example.createCriteria().andCidEqualTo(cid);
        return scMapper.selectByExample(example);
    }

    @Override
    public List<SC> listBySid(int sid) {
        SCExample example = new SCExample();
        example.createCriteria().andSidEqualTo(sid);
        List<SC> scs = scMapper.selectByExample(example);
        if(scs == null)
            return null;
        for(SC sc : scs){
            setCourse(sc);
            setTeacher(sc);
        }
        return scs;
    }

    public void setCourse(SC sc) {
        Course course = courseMapper.selectByPrimaryKey(sc.getCid());
        sc.setCourse(course);
        //System.out.println(class_.getId() + class_.getName());
    }

    private void setTeacher(SC sc) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(sc.getTid());
        sc.setTeacher(teacher);
    }
}
