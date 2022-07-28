package com.springboot.userservice;

import org.junit.jupiter.api.Test;
import org.openjdk.jmh.results.RunResult;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import com.springboot.userservice.services.StaticDataServiceImpl;

public class BenchMark {

    @Test
    public void launchBenchmark() throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(StaticDataServiceImpl.class.getSimpleName())
                .build();
        var runResults = new Runner(opt).run();
        for (RunResult runResult : runResults) {
            System.out.println(runResult);
        }
    }

}