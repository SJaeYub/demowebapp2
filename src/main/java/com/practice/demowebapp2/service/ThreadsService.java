package com.practice.demowebapp2.service;

import com.practice.demowebapp2.bean.ThreadsBean;
import com.practice.demowebapp2.dto.Member;
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

    public List<Member> callThreadsTest(List<Member> memberList) {
        threadsBean.printUserData(memberList);

        return memberList;
    }

    public void testServiceThreadsTest(List<Member> memberList) {
        ExecutorService executor = Executors.newFixedThreadPool(memberList.size());

        for (Member member : memberList) {
            executor.submit(() -> {
                Member memberPosition = threadsBean.testGetPosition(member);
                Member callTrading = threadsBean.testCallTrading(memberPosition);
            });
        }

        return;
    }

}
