package org.example;

import com.google.common.primitives.Bytes;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.streams.KafkaStreams;
import org.apache.kafka.streams.StreamsConfig;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.*;
import org.apache.kafka.streams.processor.ProcessorSupplier;
import org.apache.kafka.streams.state.KeyValueStore;
import org.apache.kafka.streams.state.StoreBuilder;
import org.apache.kafka.streams.state.Stores;
import org.apache.kafka.streams.state.WindowStore;

import java.time.Duration;
import java.util.Properties;

import static scala.None.foreach;
import static scala.collection.immutable.Nil.toStream;

public class KafkaStreamsAggregation {

    public static void main(String[] args) {
        // Set up Kafka Streams configuration
        Properties config = new Properties();
        config.put(StreamsConfig.APPLICATION_ID_CONFIG, "kafka-streams-aggregation");
        config.put(StreamsConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        config.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
        config.put(StreamsConfig.DEFAULT_KEY_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());
        config.put(StreamsConfig.DEFAULT_VALUE_SERDE_CLASS_CONFIG, Serdes.String().getClass().getName());

        // Build Kafka Streams topology
        StreamsBuilder builder = new StreamsBuilder();

        KStream<String, String> inputStream = builder.stream("test");

//        KGroupedStream<String, String> groupedStream = source.groupByKey();
//
//        KTable<String, Integer> aggregatedTable = groupedStream.aggregate(
//                () -> 0
//



        inputStream
                .groupByKey()
                .windowedBy(TimeWindows.of(Duration.ofMinutes(5)))
                .count(Materialized.as("count-store"))
                .toStream()
                .foreach((key, value) ->
                        System.out.println("Window: " + key.window().toString() + " Aggregated Value: " + value));

//                .aggregate(
//                        () -> 0L, /* initializer */
//                        (aggKey, newValue, aggValue) -> aggValue + newValue, /* adder */
//                        Materialized.<String, Long, WindowStore<Bytes, byte[]>>as("stream-topic")
//                                .withValueSerde(Serdes.Long()))
//                                .withKeySerde(Serdes.String());



        // Create Kafka Streams instance
        KafkaStreams streams = new KafkaStreams(builder.build(), config);

        // Start the Kafka Streams application
        streams.start();

        // Add shutdown hook to handle application termination
        Runtime.getRuntime().addShutdownHook(new Thread(streams::close));
    }
}
