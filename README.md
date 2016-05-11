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
