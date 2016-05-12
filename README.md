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
Benchmark                                        Mode  Cnt          Score
  Error  Units
CleanStringBuilderBenchmark.deleteContent        avgt   10   70472903.843 ± 3035393.739  ns/op
CleanStringBuilderBenchmark.newStringBuilder     avgt   10  107803873.642 ± 8149905.291  ns/op
CleanStringBuilderBenchmark.setLengthZeroNoTrim  avgt   10   66664449.640 ±   35151.001  ns/op
CleanStringBuilderBenchmark.setLengthZeroTrim    avgt   10  125014260.113 ±   59993.479  ns/op

</code></pre>
