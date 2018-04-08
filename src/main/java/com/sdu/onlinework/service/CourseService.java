package com.sdu.onlinework.service;

import com.sdu.onlinework.pojo.Course;

import java.util.List;


public interface CourseService {

    void add(Course course);
    void delete(Course course);
    void update(Course course);
    Course get(int id);
    void fillCntOfStudent(Course course);

    List<Course> list();
}
