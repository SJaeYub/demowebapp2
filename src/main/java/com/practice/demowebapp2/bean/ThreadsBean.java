package com.practice.demowebapp2.bean;

import com.practice.demowebapp2.dto.Member;
import com.practice.demowebapp2.dto.TempUMClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class ThreadsBean {

    private static final Logger logger = LoggerFactory.getLogger(ThreadsBean.class);

    ExecutorService executor;

    public void printUserData(List<Member> memberList) {
        executor = Executors.newFixedThreadPool(memberList.size());

        for (Member member : memberList) {
//            executor.submit(() -> printUserData(user));
            executor.submit(() -> {
                logger.info("============================{} Test Start============================", Thread.currentThread().getName());
//        logger.info(user.toString());
                logger.info("============================{} Test End===========================", Thread.currentThread().getName());
            });
        }

        for (Member member : memberList) {
            logger.info("no threads call start: {}", Thread.currentThread().getName());
            logger.info("no threads call end: {}", Thread.currentThread().getName());
        }

        executor.shutdown();
    }

    public Member testCallTrading(Member member) {
        TempUMClient tempUMClient = new TempUMClient(member.getUserId(), member.getPassword());
        logger.info("ThreadName: {} - callOrder: {}", Thread.currentThread().getName(), member.getUserId());

        return member;
    }

    public Member testGetPosition(Member member) {
        TempUMClient tempUMClient = new TempUMClient(member.getUserId(), member.getPassword());
        logger.info("ThreadName: {} - userPosition: {}", Thread.currentThread().getName(), member.getUserId());

        return member;
    }

    private void printUserData(Member member) {
        logger.info("============================{} Test Start============================", Thread.currentThread().getName());
//        logger.info(user.toString());
        logger.info("============================{} Test End===========================", Thread.currentThread().getName());
    }
}
