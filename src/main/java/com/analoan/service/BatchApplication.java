/**
 * @Company:
 * @Author: lancer
 * @Date: 15-2-9 下午4:52
 * @Version: 1.0
 */
package com.analoan.service;

import com.analoan.Application;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.batch.BatchProperties;
import org.springframework.boot.orm.jpa.EntityScan;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <b>BatchApplication</b>
 * <p><b>详细说明：</b></p>
 * 在这里添加详细说明
 * <p><b>修改列表：</b></p>
 * <table width="" cellspacing=1 cellpadding=3 border=1>
 * <tr bgcolor="#CCCCFF"><td>序号</td><td>作者</td><td>修改日期</td><td>修改内容</td></tr>
 * <!--在此添加修改列表，参考第一行内容-->
 * <tr bgcolor="#CCCCFF"><td>1</td><td>lancer</td><td>15-2-9 下午4:52</td><td>新建内容</td></tr>
 */
@SpringBootApplication
@EnableBatchProcessing
@Configuration
@EnableAutoConfiguration
@EntityScan(basePackages = "com.analoan.entity")
public class BatchApplication {

    @Autowired
    private JobBuilderFactory jobs;

    @Autowired
    private StepBuilderFactory steps;

    @Bean
    protected Tasklet tasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution,
                                        ChunkContext context) {
                return RepeatStatus.FINISHED;
            }
        };
    }

    @Bean
    public Job job() throws Exception {
        return this.jobs.get("job").start(step1()).build();
    }

    @Bean
    protected Step step1() throws Exception {
        return this.steps.get("step1").tasklet(tasklet()).build();
    }

    @Bean
    public JobLauncher jobLauncher(JobRepository jobRepo){
        SimpleJobLauncher simpleJobLauncher = new SimpleJobLauncher();
        simpleJobLauncher.setJobRepository(jobRepo);
        System.out.println("添加任务成功！");
        return simpleJobLauncher;
    }

    public static void main(String[] args) throws Exception {
        // System.exit is common for Batch applications since the exit code can be used to
        // drive a workflow
//        System.exit(SpringApplication.exit(SpringApplication.run(
//                BatchApplication.class, args)));

//        ConfigurableApplicationContext ctx = SpringApplication.run(BatchApplication.class, args);
//        JobLauncher jobLauncher = ctx.getBean(JobLauncher.class);
//        //try catch removed for readability
//        jobLauncher.run(ctx.getBean(Job.class), new JobParameters());
    }
}