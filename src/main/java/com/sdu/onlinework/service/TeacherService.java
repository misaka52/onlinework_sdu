package com.sdu.onlinework.service;

import com.sdu.onlinework.pojo.Teacher;

import java.util.List;


public interface TeacherService {

    void add(Teacher teacher);
    void delete(Teacher teacher);
    void update(Teacher teacher);
    Teacher get(int id);
    List<Teacher> list();
    Teacher get(String tno, String password);

    Teacher getByTno(String tno);
}
