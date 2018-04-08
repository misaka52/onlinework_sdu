package com.sdu.onlinework.service;

import com.sdu.onlinework.pojo.SW;
import com.sdu.onlinework.pojo.Student;

import java.util.List;


public interface StudentService {

    void add(Student student);
    void delete(Student student);
    void update(Student student);
    Student get(int id);
    Student getBySno(String sno);
    List<Student> listByCid(int cid);
    Student get(String sno, String password);
    void getNoWorkStudents(List<Student> allStudents, List<SW> sws);
}
