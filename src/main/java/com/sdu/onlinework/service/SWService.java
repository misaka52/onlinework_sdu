package com.sdu.onlinework.service;

import com.sdu.onlinework.pojo.SW;

import java.util.List;


public interface SWService {

    void add(SW sw);
    void delete(SW sw);
    void update(SW sw);
    SW get(int id);
    List<SW> list();
    List<SW> listByWid(int wid);

    SW getByWidAndSid(int wid, int sid);
}
