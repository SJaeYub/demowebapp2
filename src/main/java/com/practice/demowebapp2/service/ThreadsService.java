package com.practice.demowebapp2.service;

import com.practice.demowebapp2.bean.ThreadsBean;
import com.practice.demowebapp2.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ThreadsService {

    @Autowired
    ThreadsBean threadsBean;

    public List<User> callThreadsTest(List<User> userList) {
        threadsBean.printUserData(userList);

        return userList;
    }

}
