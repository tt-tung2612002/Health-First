package com.springboot;

import org.openjdk.jmh.profile.GCProfiler;
import org.openjdk.jmh.results.format.ResultFormatType;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.springboot.userservice.controllers.StaticDataController;

// @State(Scope.Thread)
public class MyBenchmark {

    public static void main(String[] args) throws Exception {
        Options opt = new OptionsBuilder()
                .include(DemoApplication.class.getSimpleName())
                .include(StaticDataController.class.getSimpleName())
                .threads(1)
                .shouldDoGC(true)
                .shouldFailOnError(true)
                .resultFormat(ResultFormatType.JSON)
                .shouldFailOnError(true)
                .jvmArgs("-server")
                .forks(0) // 0 makes debugging possible
                .shouldFailOnError(true)
                .addProfiler(GCProfiler.class)
                .build();

        new Runner(opt).run();
    }

}