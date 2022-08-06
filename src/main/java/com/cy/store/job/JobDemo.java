package com.cy.store.job;

import org.springframework.stereotype.Component;

import java.util.Date;
@Component
public class JobDemo {
    public void printTime(){
        System.out.println("现在时刻："+new Date());
    }
}
