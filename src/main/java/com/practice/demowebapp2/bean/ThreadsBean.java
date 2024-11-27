package com.practice.demowebapp2.bean;

import com.practice.demowebapp2.model.TempUMClient;
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
//            executor.submit(() -> printUserData(user));
            executor.submit(() -> {
                logger.info("============================{} Test Start============================", Thread.currentThread().getName());
//        logger.info(user.toString());
                logger.info("============================{} Test End===========================", Thread.currentThread().getName());
            });
        }

        for (User user : userList) {
            logger.info("no threads call start: {}", Thread.currentThread().getName());
            logger.info("no threads call end: {}", Thread.currentThread().getName());
        }

        executor.shutdown();
    }

    public User testCallTrading(User user) {
        TempUMClient tempUMClient = new TempUMClient(user.getUserId(), user.getPassword());
        logger.info("ThreadName: {} - callOrder: {}", Thread.currentThread().getName(), user.getUserId());

        return user;
    }

    public User testGetPosition(User user) {
        TempUMClient tempUMClient = new TempUMClient(user.getUserId(), user.getPassword());
        logger.info("ThreadName: {} - userPosition: {}", Thread.currentThread().getName(), user.getUserId());

        return user;
    }

    private void printUserData(User user) {
        logger.info("============================{} Test Start============================", Thread.currentThread().getName());
//        logger.info(user.toString());
        logger.info("============================{} Test End===========================", Thread.currentThread().getName());
    }
}
