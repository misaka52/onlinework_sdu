package com.sdu.onlinework.service;

import com.sdu.onlinework.pojo.Work;

import java.util.List;


public interface WorkService {

    void add(Work work);
    void delete(Work work);
    void update(Work work);
    Work get(int id);
    List<Work> list();
    List<Work> listByTid(int tid);
    List<Work> listByTidAndCid(int tid, int cid);
    void setCourse(Work w);
    void setStatus(Work w);
    void setTeacher(Work w);

    List<Work> listBySid(int sid);
}
