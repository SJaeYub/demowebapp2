package com.practice.demowebapp2.service;

import com.practice.demowebapp2.bean.ThreadsBean;
import com.practice.demowebapp2.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class ThreadsService {

    private static final Logger logger = LoggerFactory.getLogger(ThreadsService.class);

    @Autowired
    ThreadsBean threadsBean;

    public List<User> callThreadsTest(List<User> userList) {
        threadsBean.printUserData(userList);

        return userList;
    }

    public void testServiceThreadsTest(List<User> userList) {
        ExecutorService executor = Executors.newFixedThreadPool(userList.size());

        for (User user : userList) {
            executor.submit(() -> {
                User userPosition = threadsBean.testGetPosition(user);
                User callTrading = threadsBean.testCallTrading(userPosition);
            });
        }

        return;
    }

}
