package com.sdu.onlinework.pojo;

import java.util.List;

public class Course {
    private Integer id;

    private String name;

    //非数据库字段
    private List<Student> students;

    private int cntOfStudent;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }

    public int getCntOfStudent() {
        return cntOfStudent;
    }

    public void setCntOfStudent(int cntOfStudent) {
        this.cntOfStudent = cntOfStudent;
    }
}