# CleanStringBuilderBenchmark

Intention of this code is to understand if reuse or creating new StringBuilders using JMH microbenchmarking in something like this artificial case:

<pre><code>
StringBuilder sb = new StringBuilder();
for (int j = 0; j < 10; j++) {
    for (int i = 0; i < 10; i++) {
        sb.append("abcd");
    }
    //pass StringBuilder to some processor such logging
    
    //and...
    sb = new StringBuilder();
    // or sb.delete(...)
    // or sb.length(0) and sb.trimToSize()
    // or sb.length(0) though this case is slightly arguable, but I believe its ok
}
</code></pre>

You can run this java benchmarking using these commands:

<pre><code>
mvn clean install

java -jar target/benchmarks.jar
</code></pre>


Latest figures from the default configuration of benchmark:

<pre><code>

Benchmark                                        Mode  Cnt     Score     Error  Units
CleanStringBuilderBenchmark.deleteContent        avgt    5  1253.093 ± 173.797  ns/op
CleanStringBuilderBenchmark.newStringBuilder     avgt    5  1535.043 ±  23.399  ns/op
CleanStringBuilderBenchmark.setLengthZeroNoTrim  avgt    5  1176.839 ±  23.794  ns/op
CleanStringBuilderBenchmark.setLengthZeroTrim    avgt    5  1795.839 ± 164.180  ns/op

As we can see setLengthZeroNoTrim is 1.3 times faster than newStringBuilder approach.

Possible JEP was discussed with Alexey Shipilev and agreed to be subjective to actually introducing to JDK.


</code></pre>
