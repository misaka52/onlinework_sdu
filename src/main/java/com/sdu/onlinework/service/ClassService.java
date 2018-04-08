package com.sdu.onlinework.service;

import com.sdu.onlinework.pojo.Class;

import java.util.List;

public interface ClassService {

    void add(Class class_);
    void delete(Class class_);
    void update(Class class_);
    Class get(int id);
    List<Class> list();
}
