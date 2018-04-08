package com.sdu.onlinework.service;

import com.sdu.onlinework.pojo.SC;

import java.util.List;


public interface SCService {

    void add(SC sc);
    void delete(SC sc);
    void update(SC sc);
    SC get(int id);
    SC getByCidAndSid(int cid, int sid);
    List<SC> list();
    List<SC> listByCid(int cid);
    List<SC> listBySid(int sid);
}
