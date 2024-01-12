package org.example;

import java.time.Duration;
import java.util.Properties;
import java.util.concurrent.CountDownLatch;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.streams.Topology;
import org.apache.kafka.streams.kstream.*;

public class AggregationsMain {

    public static void main(String[] args) {
        // Set up the configuration.
        final Properties props = new Properties();
        props.put(StreamsConfig.APPLICATION_ID_CONFIG, "stream_int");
        props.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
       // props.put(StreamsConfig.CACHE_MAX_BYTES_BUFFERING_CONFIG, 0);
        props.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        // Since the input topic uses Strings for both key and value, set the default Serdes to String.
        props.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        props.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        // Get the source stream.
        final StreamsBuilder builder = new StreamsBuilder();



        KStream<String, String> source = builder.stream("input_topic");

        KGroupedStream<String, String> groupedStream = source.groupByKey();

//        source
//                .groupByKey()
//                .windowedBy(TimeWindows.of(Duration.ofMinutes(5)));
//        KTable<String, Integer> aggregatedTable = groupedStream.aggregate(
//                () -> 0,
//
//
//               source.foreach(aggKey, newValue, aggValue) -> String.valueOf(Integer.parseInt(newValue) * 5),
//                Materialized.with(Serdes.String(), Serdes.Integer()));
//        aggregatedTable.toStream().to("stream_op3", Produced.with(Serdes.String(), Serdes.Integer()));
//
        KStream<String, String> inputStream = builder.stream("input_topic", Consumed.with(Serdes.String(), Serdes.String()));

        inputStream.foreach((key, value) -> {
            System.out.println("Received: key=" + key + ", value=" + value);
        });

        // Process the stream: multiply by 5 and send to the output topic
        inputStream.mapValues(value -> String.valueOf(Integer.parseInt(value) * 5))
                .to("output_topic", Produced.with(Serdes.String(), Serdes.String()));



        final Topology topology = builder.build();
        final KafkaStreams streams = new KafkaStreams(topology, props);
        // Print the topology to the console.
        System.out.println(topology.describe());
        final CountDownLatch latch = new CountDownLatch(1);

        // Attach a shutdown handler to catch control-c and terminate the application gracefully.
        Runtime.getRuntime().addShutdownHook(new Thread("streams-shutdown-hook") {
            @Override
            public void run() {
                streams.close();
                latch.countDown();
            }
        });

        try {
            streams.start();
            latch.await();
        } catch (final Throwable e) {
            System.out.println(e.getMessage());
            System.exit(1);
        }
        System.exit(0);
    }

}