package com.sdu.onlinework.service;

import com.sdu.onlinework.pojo.FileName;

public interface FileNameService {
    void add(FileName fileName);
    void delete(FileName fileName);
    FileName get(int id);
    int getMax();
}
