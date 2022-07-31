package com.springboot;

import java.util.concurrent.TimeUnit;

import org.openjdk.jmh.annotations.Benchmark;
import org.openjdk.jmh.annotations.BenchmarkMode;
import org.openjdk.jmh.annotations.Mode;
import org.openjdk.jmh.annotations.OutputTimeUnit;
import org.openjdk.jmh.annotations.Scope;
import org.openjdk.jmh.annotations.Setup;
import org.openjdk.jmh.annotations.State;
import org.openjdk.jmh.annotations.TearDown;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;
import org.openjdk.jmh.runner.options.TimeValue;
import org.openjdk.jmh.runner.options.VerboseMode;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import com.springboot.userservice.controllers.StaticDataController;

// @Threads(1)
// @State(Scope.Benchmark)
// @BenchmarkMode(Mode.SingleShotTime)
// @OutputTimeUnit(TimeUnit.MILLISECONDS)
// @Fork(jvmArgsAppend = { "-Xms2g", "-Xmx2g" })
@BenchmarkMode(Mode.Throughput)
@OutputTimeUnit(TimeUnit.MINUTES)
@State(Scope.Benchmark)
@SpringBootApplication
public class DemoApplication {

    private StaticDataController staticDataController;
    // extends SpringBootServletInitializer {

    // private ConfigurableApplicationContext context;

    // @Override
    // protected SpringApplicationBuilder configure(SpringApplicationBuilder
    // builder) {
    // return configureApplication(builder);
    // }

    // private static SpringApplicationBuilder
    // configureApplication(SpringApplicationBuilder builder) {
    // return builder.sources(applicationClass).bannerMode(Banner.Mode.OFF);
    // }

    // @Benchmark
    // public ConfigurableApplicationContext startUp() {
    // return context = SpringApplication.run(applicationClass);
    // }

    // @TearDown(Level.Invocation)
    // public void close() {
    // context.close();
    // }

    private ConfigurableApplicationContext context;

    @Setup
    public synchronized void initialize() {
        try {
            String args = "";
            if (context == null) {
                context = SpringApplication.run(DemoApplication.class, args);
            }

            // get the entity manager from the spring context
            // entityManager = context.getBean(EntityManager.class);
            staticDataController = context.getBean(StaticDataController.class);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @TearDown
    public void closeContext() {
        context.close();
    }

    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(applicationClass.getSimpleName())
                .threads(1)
                .shouldDoGC(false)
                .warmupIterations(3)
                .warmupTime(TimeValue.microseconds(1000))
                .measurementIterations(10)
                .measurementTime(TimeValue.microseconds(1000))
                // .resultFormat(ResultFormatType.JSON)
                // .jvmArgs("-server")
                .verbosity(VerboseMode.EXTRA)
                .forks(0) // 0 makes debugging possible
                .shouldFailOnError(true)
                // .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void retrieveDataUsingQueryDSL(Blackhole blackhole) {
//        var list = staticDataController.benchmarkMethod1();
//        blackhole.consume(list);
    }

    @Benchmark
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.MICROSECONDS)
    public void retrieveDataUsingSQL(Blackhole blackhole) {
//        var list = staticDataController.benchmarkMethod2();
//        blackhole.consume(list);
    }

    private static final Class<DemoApplication> applicationClass = DemoApplication.class;

}
