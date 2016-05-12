package asym.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Fork(value = CleanStringBuilderBenchmark.FORKS, warmups = CleanStringBuilderBenchmark.FORKS, jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
@Warmup(batchSize = CleanStringBuilderBenchmark.BATCH_SIZE, iterations = CleanStringBuilderBenchmark.ITER_NUM)
@Measurement(batchSize = CleanStringBuilderBenchmark.BATCH_SIZE, iterations = CleanStringBuilderBenchmark.ITER_NUM)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CleanStringBuilderBenchmark {

    public static final String ABCD = "abcd";
    public static final int BATCH_SIZE = 100000;
    public static final int ITER_NUM = 5;
    public static final int FORKS = 1;

    @Benchmark
    public StringBuilder setLengthZeroTrim() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            sb.setLength(0);
            sb.trimToSize();
        }
        return sb;
    }

    @Benchmark
    public StringBuilder setLengthZeroNoTrim() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            sb.setLength(0);
        }
        return sb;
    }


    @Benchmark
    public StringBuilder deleteContent() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            sb.delete(0, sb.length());
        }
        return sb;
    }

    @Benchmark
    public StringBuilder newStringBuilder() {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            sb = new StringBuilder();
        }
        return sb;
    }


    public static void main(String[] args) throws RunnerException {
        Options opt = new OptionsBuilder()
                .include(CleanStringBuilderBenchmark.class.getSimpleName())
                .build();

        new Runner(opt).run();
    }

}