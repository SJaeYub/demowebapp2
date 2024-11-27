package com.practice.demowebapp2.bean;

import com.practice.demowebapp2.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadsBean {

    private static final Logger logger = LoggerFactory.getLogger(ThreadsBean.class);

    ExecutorService executor;

    public void printUserData(List<User> userList) {
        executor = Executors.newFixedThreadPool(userList.size());

        for (User user : userList) {
            executor.submit(() -> printUserData(user));
        }

        executor.shutdown();
    }

    private void printUserData(User user) {
        logger.info("============================{} Test Start============================", Thread.currentThread().getName());
//        logger.info(user.toString());
        logger.info("============================{} Test End===========================", Thread.currentThread().getName());
    }
}
