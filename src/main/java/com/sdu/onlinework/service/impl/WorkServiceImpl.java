package com.sdu.onlinework.service.impl;

import com.sdu.onlinework.comparator.WorkStartTimeComparator;
import com.sdu.onlinework.mapper.*;
import com.sdu.onlinework.pojo.*;
import com.sdu.onlinework.service.SWService;
import com.sdu.onlinework.service.WorkService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

@Service
public class WorkServiceImpl implements WorkService {
    @Autowired
    WorkMapper workMapper;
    @Autowired
    CourseMapper courseMapper;
    @Autowired
    SWService swService;
    @Autowired
    TeacherMapper teacherMapper;
    @Autowired
    SWMapper swMapper;



    @Override
    public void add(Work work) {
        workMapper.insert(work);
    }

    @Override
    public void delete(Work work) {
        workMapper.deleteByPrimaryKey(work.getId());
    }

    @Override
    public void update(Work work) {
        workMapper.updateByPrimaryKeySelective(work);
    }

    @Override
    public Work get(int id) {
        return workMapper.selectByPrimaryKey(id);
    }

    @Override
    public List<Work> list() {
        WorkExample example = new WorkExample();
        example.setOrderByClause("id desc");
        return workMapper.selectByExample(example);
    }

    @Override
    public List<Work> listByTid(int tid) {
        WorkExample example = new WorkExample();
        example.setOrderByClause("startTime desc");
        example.createCriteria().andTidEqualTo(tid);
        List<Work> ws = workMapper.selectByExample(example);
        if(ws == null)
            return null;
        for(Work w : ws) {
            setStatus(w);
            setCourse(w);
        }
        return ws;
    }

    @Override
    public void setStatus(Work w) {
        Date nowTime = new Date();
//        System.out.println(nowTime);
        if(nowTime.getTime() < w.getStartTime().getTime())
            w.setStatus("Schedule");
        else if(nowTime.getTime() <= w.getEndTime().getTime())
            w.setStatus("Running");
        else
            w.setStatus("Ended");
    }

    @Override
    public void setTeacher(Work w) {
        Teacher teacher = teacherMapper.selectByPrimaryKey(w.getTid());
        w.setTeacher(teacher);
    }

    @Override
    public List<Work> listBySid(int sid) {
        SWExample example = new SWExample();
        example.createCriteria().andSidEqualTo(sid);
        List<SW> sws = swMapper.selectByExample(example);
        if(sws == null)
            return null;
        List<Work> ws = new ArrayList<>();
        for(SW sw : sws) {
            Work work = workMapper.selectByPrimaryKey(sw.getWid());
            setTeacher(work);
            setStatus(work);
            setCourse(work);
            ws.add(work);
        }
        //按开始时间排序
        Collections.sort(ws, new WorkStartTimeComparator());
        return ws;
    }

    @Override
    public void setCourse(Work w) {
        Course course = courseMapper.selectByPrimaryKey(w.getCid());
        w.setCourse(course);
    }

    @Override
    public List<Work> listByTidAndCid(int tid, int cid) {
        WorkExample example = new WorkExample();
        example.createCriteria().andTidEqualTo(tid).andCidEqualTo(cid);
        example.setOrderByClause("startTime desc");
        List<Work> ws = workMapper.selectByExample(example);
        if(ws == null)
            return null;
        for(Work w : ws) {
            setStatus(w);
            setCourse(w);
        }
        return ws;
    }
}
