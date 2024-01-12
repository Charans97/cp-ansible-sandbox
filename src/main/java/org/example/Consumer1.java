package org.example;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.TopicPartition;
import org.apache.kafka.common.serialization.StringDeserializer;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Collections;
import java.util.Map;
import java.util.Properties;

public class Consumer1 {

    public static void main(String[] args) throws InterruptedException {
        Properties p = new Properties();
        try {
            FileInputStream input = new FileInputStream("/home/sumo/hackathon/client.properties");
            p.load(input);
            input.close();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }

        //p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9094");
        p.setProperty(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "pkc-4r087.us-west2.gcp.confluent.cloud:9092");
        p.setProperty(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class.getName());
        p.setProperty(ConsumerConfig.GROUP_ID_CONFIG, "ConsumerGroup5");
        p.setProperty(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");

        // Configure ConsumerTimestampsInterceptor
        p.setProperty(ConsumerConfig.INTERCEPTOR_CLASSES_CONFIG, ConsumerTimestampsInterceptor.class.getName());

        System.out.println(p.toString());
        final KafkaConsumer<String, String> consumer = new KafkaConsumer<>(p);

        // Subscribe to the topic
        consumer.subscribe(Collections.singletonList("abcd"));

        while (true) {
            ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(500));
            for (ConsumerRecord<String, String> record : records) {
                System.out.println("Received new record: \n" +
                        "Key: " + record.key() + ", " +
                        "Value: " + record.value() + ", " +
                        "Topic: " + record.topic() + ", " +
                        "Partition: " + record.partition() + ", " +
                        "Offset: " + record.offset() + "\n");
                Thread.sleep(1000 * 60);
                consumer.commitSync();
            }
        }
    }

    // Define ConsumerTimestampsInterceptor class (make sure it's in your classpath)
    public static class ConsumerTimestampsInterceptor implements ConsumerInterceptor<String, String> {
        @Override
        public ConsumerRecords<String, String> onConsume(ConsumerRecords<String, String> records) {
            // Implement your logic for intercepting consumed records
            return records;
        }

        @Override
        public void onCommit(Map<TopicPartition, OffsetAndMetadata> offsets) {
            // Implement your logic for intercepting committed offsets
        }

        @Override
        public void close() {
            // Cleanup resources if needed
        }

        @Override
        public void configure(Map<String, ?> configs) {
           // interceptor.classes=io.confluent.connect.replicator.offsets.ConsumerTimestampsInterceptor;
            // Configure the interceptor if needed
        }
    }
}

