package com.myproject.plogging.batch.scheduler;


import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Collections;

@Component
@RequiredArgsConstructor
public class BasicScheduler {

    private final JobLauncher jobLauncher;

    private final Job basicJobConfig;

    // sec - minute - hour - day of month - month - day of the week

    @Scheduled(cron = "* 2 * * * *")
    public void BasicJobRun() throws JobInstanceAlreadyCompleteException, JobExecutionAlreadyRunningException, JobParametersInvalidException, JobRestartException {

        JobParameters jobParameters = new JobParameters(

                Collections.singletonMap("requestTime", new JobParameter<>(System.currentTimeMillis(), Long.class)));

        jobLauncher.run(new Job() {
            @Override
            public String getName() {
                return "likesDataJob";
            }

            @Override
            public void execute(JobExecution execution) {
            }
        }, jobParameters);
    }

}
