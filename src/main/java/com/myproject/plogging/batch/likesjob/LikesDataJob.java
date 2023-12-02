package com.myproject.plogging.batch.likesjob;


import com.myproject.plogging.domain.PhotoList;
import jakarta.persistence.EntityManagerFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaCursorItemReader;
import org.springframework.batch.item.database.JpaPagingItemReader;
import org.springframework.batch.item.database.builder.JpaCursorItemReaderBuilder;
import org.springframework.batch.item.database.builder.JpaPagingItemReaderBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionException;
import org.springframework.transaction.TransactionStatus;

@Configuration
@RequiredArgsConstructor
public class LikesDataJob {

    private final EntityManagerFactory em;

    @Bean
    public Job likedDataJob(JobRepository jobRepository){
        return new JobBuilder("likesDataJob", jobRepository)
                .incrementer(new RunIdIncrementer())
                .start(likesStep(jobRepository, new PlatformTransactionManager() {
                    @Override
                    public TransactionStatus getTransaction(TransactionDefinition definition) throws TransactionException {
                        return null;
                    }

                    @Override
                    public void commit(TransactionStatus status) throws TransactionException {

                    }

                    @Override
                    public void rollback(TransactionStatus status) throws TransactionException {

                    }
                }))
                .build();
    }

    @JobScope
    @Bean
    public Step likesStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("likesStep", jobRepository)
                .tasklet(likesTasklet(), transactionManager)
                .build();
    }

    @JobScope
    @Bean
    public Step dbDataStep(
            JobRepository jobRepository,
            PlatformTransactionManager transactionManager

    ) {
        return new StepBuilder("dbDataStep", jobRepository)
                .<PhotoList, PhotoList>chunk(100, transactionManager)// 100개 단위 commit,
                .reader(dbDataReader())
                .processor(dbDataProcessor())
                .writer(dbDataWriter())
                .build();
    }

    private ItemWriter<? super PhotoList> dbDataWriter() {

        return null;
    }

    private ItemProcessor<? super PhotoList,? extends PhotoList> dbDataProcessor() {

        return null;
    }

    private ItemReader<PhotoList> dbDataReader() {
        return new JpaCursorItemReaderBuilder<PhotoList>()
                .name("dbReader")
                .entityManagerFactory(em)
                .queryString(
                        "select o from PhotoList o"
                )
                .build();
    }

    @StepScope
    @Bean
    public Tasklet likesTasklet() {
        return new Tasklet() {
            @Override
            public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
                System.out.println("- - - SpringBatchScheduler is Running - - -");
                return RepeatStatus.FINISHED;
            }
        };
    }

}
