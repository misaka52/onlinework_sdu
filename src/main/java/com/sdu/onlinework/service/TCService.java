package com.sdu.onlinework.service;

import com.sdu.onlinework.pojo.Course;
import com.sdu.onlinework.pojo.TC;

import java.util.List;


public interface TCService {

    void add(TC tc);
    void delete(TC tc);
    void update(TC tc);
    TC get(int id);
    List<TC> list();
    List<Course> getCourseByTid(int tid);
}
