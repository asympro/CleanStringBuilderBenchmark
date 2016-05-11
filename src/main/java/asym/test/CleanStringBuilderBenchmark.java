package asym.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

public class CleanStringBuilderBenchmark {

    public static final String ABCD = "abcd";
    public static final int BATCH_SIZE = 100000;
    public static final int ITER_NUM = 5;
    public static final int FORKS = 2;

    @Benchmark
    @Fork(value = FORKS, warmups = FORKS)
    @Warmup(batchSize = BATCH_SIZE, iterations = ITER_NUM)
    @Measurement(batchSize = BATCH_SIZE, iterations = ITER_NUM)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void setLengthZeroTrim() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            sb.setLength(0);
            sb.trimToSize();
        }
    }

    @Benchmark
    @Fork(value = FORKS, warmups = FORKS)
    @Warmup(batchSize = BATCH_SIZE, iterations = ITER_NUM)
    @Measurement(batchSize = BATCH_SIZE, iterations = ITER_NUM)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void setLengthZeroNoTrim() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            sb.setLength(0);
        }
    }


    @Benchmark
    @Fork(value = FORKS, warmups = FORKS)
    @Warmup(batchSize = BATCH_SIZE, iterations = ITER_NUM)
    @Measurement(batchSize = BATCH_SIZE, iterations = ITER_NUM)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void deleteContent() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            sb.delete(0, sb.length());
        }
    }

    @Benchmark
    @Fork(value = FORKS, warmups = FORKS)
    @Warmup(batchSize = BATCH_SIZE, iterations = ITER_NUM)
    @Measurement(batchSize = BATCH_SIZE, iterations = ITER_NUM)
    @BenchmarkMode(Mode.AverageTime)
    @OutputTimeUnit(TimeUnit.NANOSECONDS)
    public void newStringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            sb = new StringBuilder();
        }
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CleanStringBuilderBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}