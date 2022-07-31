package com.springboot.userservice;

import java.util.concurrent.TimeUnit;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Level;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.test.context.junit4.SpringRunner;

import com.querydsl.jpa.impl.JPAQueryFactory;
import com.springboot.DemoApplication;
import com.springboot.userservice.entity.QAppUser;

@SpringBootTest
@RunWith(SpringRunner.class)
@State(Scope.Benchmark)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.MICROSECONDS)
public class MyBenchmark {

    static ConfigurableApplicationContext context;

    @Setup(Level.Trial)
    public synchronized void initialize() {
        try {
            String args = "";
            if (context == null) {
                context = SpringApplication.run(DemoApplication.class, args);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @PersistenceContext
    private EntityManager entityManager;

    @Test
    public void executeJmhRunner() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(this.getClass().getSimpleName())
                .threads(1)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                // .resultFormat(ResultFormatType.JSON)
                // .shouldFailOnError(true)
                .jvmArgs("-server")
                .forks(0) // 0 makes debugging possible
                .shouldFailOnError(true)
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    public void someBenchmarkMethod() {
        JPAQueryFactory queryFactory = new JPAQueryFactory(entityManager);
        QAppUser user = QAppUser.appUser;

        queryFactory.select(user).from(user).where(user.username.eq("tt-tung261")).fetch();
    }
}