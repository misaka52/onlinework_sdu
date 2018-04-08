package com.sdu.onlinework.service.impl;

import com.sdu.onlinework.mapper.ClassMapper;
import com.sdu.onlinework.mapper.SCMapper;
import com.sdu.onlinework.mapper.StudentMapper;
import com.sdu.onlinework.pojo.*;
import com.sdu.onlinework.pojo.Class;
import com.sdu.onlinework.service.SCService;
import com.sdu.onlinework.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentMapper studentMapper;
    @Autowired
    ClassMapper classMapper;
    @Autowired
    SCMapper scMapper;
    @Autowired
    SCService scService;

    @Override
    public void add(Student student) {
        studentMapper.insert(student);
    }

    @Override
    public void delete(Student student) {
        studentMapper.deleteByPrimaryKey(student.getId());
    }

    @Override
    public void update(Student student) {
        studentMapper.updateByPrimaryKeySelective(student);
    }

    @Override
    public Student get(int id) {
        return studentMapper.selectByPrimaryKey(id);
    }

    @Override
    public Student getBySno(String sno) {
        StudentExample example = new StudentExample();
        example.createCriteria().andSnoEqualTo(sno);
        List<Student> ss = studentMapper.selectByExample(example);
        if(ss == null || ss.size() == 0)
            return null;
        return ss.get(0);
    }

    @Override
    public List<Student> listByCid(int cid) {
        List<SC> scs = scService.listByCid(cid);
        List<Student> ss = new ArrayList<>();
        for(SC sc : scs) {
            Student s = studentMapper.selectByPrimaryKey(sc.getSid());
            setClass(s);
            ss.add(s);
        }
        return ss;
    }

    @Override
    public Student get(String sno, String password) {
        StudentExample example = new StudentExample();
        example.createCriteria().andSnoEqualTo(sno).andPasswordEqualTo(password);
        List<Student> result = studentMapper.selectByExample(example);
        if(result.size() == 0)
            return null;
        return result.get(0);
    }

    @Override
    public void getNoWorkStudents(List<Student> allStudents, List<SW> sws) {
        if(sws != null)
            for(SW sw : sws) {
                Student s = studentMapper.selectByPrimaryKey(sw.getSid());
                allStudents.remove(s);
            }
    }

    public void setClass(Student student) {
        Class c = classMapper.selectByPrimaryKey(student.getCid());
        student.setClass_(c);
    }
}
