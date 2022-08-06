package com.cy.store.config;

import com.cy.store.job.JobDemo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

/**
 * 配置定时任务类
 *
 * @Configuration 注解表示这是一个配置类，这个类中的所有方法都会被spring容器所托管。
 */
@Configuration
public class QuartzConfig {
    @Bean
    public MethodInvokingJobDetailFactoryBean methodInvokingJobDetailFactoryBean(JobDemo jobDemo) {
        MethodInvokingJobDetailFactoryBean bean = new MethodInvokingJobDetailFactoryBean();
        // 设置任务名称
        bean.setTargetObject(jobDemo);
        // 设置任务方法
        bean.setTargetMethod("printTime");
        // 设置任务是否可以并发执行
        bean.setConcurrent(false);
        // 设置任务执行时间
        return bean;
    }

    //2、trigger：什么时候做
    @Bean
    public CronTriggerFactoryBean cronTriggerFactoryBean(MethodInvokingJobDetailFactoryBean bean) {
        // 创建CronTrigger对象
        CronTriggerFactoryBean cronTriggerFactoryBean = new CronTriggerFactoryBean();
        // 设置Cron表达式
        cronTriggerFactoryBean.setJobDetail(bean.getObject());
        // 设置Cron表达式,"0/5 * * * * ?"表示每隔5秒执行一次
        cronTriggerFactoryBean.setCronExpression("0/5 * * * * ?");
        // 设置任务名称
        return cronTriggerFactoryBean;
    }

    //3、scheduled：什么时候做什么事
    @Bean
    public SchedulerFactoryBean schedulerFactoryBean(CronTriggerFactoryBean cronTriggerFactoryBean) {
        // 创建SchedulerFactoryBean对象
        SchedulerFactoryBean schedulerFactoryBean = new SchedulerFactoryBean();
        // 设置触发器
        schedulerFactoryBean.setTriggers(cronTriggerFactoryBean.getObject());
        // 设置调度器名称
        return schedulerFactoryBean;
    }
}