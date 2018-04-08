package com.sdu.onlinework.service.impl;

import com.sdu.onlinework.mapper.FileNameMapper;
import com.sdu.onlinework.pojo.FileName;
import com.sdu.onlinework.pojo.FileNameExample;
import com.sdu.onlinework.service.FileNameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FileNameServiceImpl implements FileNameService {
    @Autowired
    FileNameMapper fileNameMapper;

    @Override
    public void add(FileName fileName) {
        fileNameMapper.insert(fileName);
    }

    @Override
    public void delete(FileName fileName) {
        fileNameMapper.deleteByPrimaryKey(fileName.getId());
    }

    @Override
    public FileName get(int id) {
        FileNameExample example = new FileNameExample();
        example.createCriteria().andIdEqualTo(id);
        List<FileName> fns = fileNameMapper.selectByExample(example);
        if(fns == null || fns.size() == 0)
            return null;
        return fns.get(0);
    }

    @Override
    public int getMax() {
        return fileNameMapper.getMax();
    }
}
