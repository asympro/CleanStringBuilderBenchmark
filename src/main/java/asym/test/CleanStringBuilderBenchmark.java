package asym.test;

import org.openjdk.jmh.annotations.*;
import org.openjdk.jmh.infra.Blackhole;
import org.openjdk.jmh.runner.Runner;
import org.openjdk.jmh.runner.RunnerException;
import org.openjdk.jmh.runner.options.Options;
import org.openjdk.jmh.runner.options.OptionsBuilder;

import java.util.concurrent.TimeUnit;

@Fork(value = 1, jvmArgsAppend = {"-XX:+UseParallelGC", "-Xms1g", "-Xmx1g"})
@Warmup(iterations = 5)
@Measurement(iterations = 5)
@BenchmarkMode(Mode.AverageTime)
@OutputTimeUnit(TimeUnit.NANOSECONDS)
public class CleanStringBuilderBenchmark {

    public static final String ABCD = "abcd";

    @Benchmark
    public StringBuilder setLengthZeroTrim(Blackhole bh) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            bh.consume(sb);
            sb.setLength(0);
            sb.trimToSize();
        }
        return sb;
    }

    @Benchmark
    public StringBuilder setLengthZeroNoTrim(Blackhole bh) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            bh.consume(sb);
            sb.setLength(0);
        }
        return sb;
    }

    @Benchmark
    public StringBuilder deleteContent(Blackhole bh) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            bh.consume(sb);
            sb.delete(0, sb.length());
        }
        return sb;
    }

    @Benchmark
    public StringBuilder newStringBuilder(Blackhole bh) {
        StringBuilder sb = new StringBuilder();
        for (int j = 0; j < 10; j++) {
            for (int i = 0; i < 10; i++) {
                sb.append(ABCD);
            }
            bh.consume(sb);
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