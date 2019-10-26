package com.whf.annotation.study.service;

import com.whf.annotation.study.dao.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private UserDao dao;

    @Autowired
    public UserService(UserDao dao) {
        this.dao = dao;
    }

    public String getUser(){
        return dao.test();
    }
}
