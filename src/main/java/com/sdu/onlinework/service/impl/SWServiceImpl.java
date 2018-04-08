package com.sdu.onlinework.service.impl;

import com.sdu.onlinework.mapper.*;
import com.sdu.onlinework.pojo.*;
import com.sdu.onlinework.pojo.Class;
import com.sdu.onlinework.service.SWService;
import com.sdu.onlinework.service.StudentService;
import com.sdu.onlinework.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SWServiceImpl implements SWService {
    @Autowired
    SWMapper swMapper;
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ClassMapper classMapper;
    @Autowired
    WorkMapper workMapper;
    @Autowired
    WorkService workService;
    @Autowired
    TeacherMapper teacherMapper;

    @Override
    public void add(SW sw) {
        swMapper.insertSelective(sw);
    }

    @Override
    public void delete(SW sw) {
        swMapper.deleteByPrimaryKey(sw.getId());
    }

    @Override
    public void update(SW sw) {
        swMapper.updateByPrimaryKeySelective(sw);
    }

    @Override
    public SW get(int id) {
        return swMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<SW> list() {
        SWExample example = new SWExample();
        example.setOrderByClause("id desc");
        return swMapper.selectByExample(example);
    }

    @Override
    public List<SW> listByWid(int wid) {
        SWExample example = new SWExample();
        example.createCriteria().andWidEqualTo(wid);
        List<SW> sws = swMapper.selectByExample(example);
        if(sws == null)
            return null;
        for(SW sw : sws) {
            setStudent(sw);
            setWork(sw);
        }
        return sws;
    }

    @Override
    public SW getByWidAndSid(int wid, int sid) {
        SWExample example = new SWExample();
        example.createCriteria().andWidEqualTo(wid).andSidEqualTo(sid);
        List<SW> sws = swMapper.selectByExample(example);
        if(sws == null || sws.size() == 0)
            return null;
        return sws.get(0);
    }

    private void setWork(SW sw){
        Work work = workMapper.selectByPrimaryKey(sw.getWid());
        workService.setStatus(work);
        workService.setCourse(work);
        workService.setTeacher(work);
        sw.setWork(work);
    }

    private void setStudent(SW sw) {
        Student student = studentMapper.selectByPrimaryKey(sw.getSid());
        Class class_ = classMapper.selectByPrimaryKey(student.getCid());
        student.setClass_(class_);
        sw.setStudent(student);
    }
}
