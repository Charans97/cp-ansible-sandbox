package org.example;


import com.fasterxml.jackson.databind.deser.std.StringDeserializer;
import io.confluent.kafka.serializers.KafkaAvroDeserializer;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.consumer.Consumer;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.clients.consumer.ConsumerRecords;
import org.apache.kafka.clients.consumer.KafkaConsumer;

import java.time.Duration;
import java.util.Collections;
import java.util.Properties;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {


    public static void main(String[] args) {
        // Set up consumer properties
        Properties properties = new Properties();
        properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        properties.put(ConsumerConfig.GROUP_ID_CONFIG, "avro-consumer-group1");
        properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, KafkaAvroDeserializer.class.getName());
        properties.put("schema.registry.url", "http://localhost:8081");

        // Create a Kafka consumer
        Consumer<String, GenericRecord> consumer = new KafkaConsumer<>(properties);

        // Subscribe to the Kafka topic
        consumer.subscribe(Collections.singletonList("test"));

        // Start consuming records
        try {
            while (true) {
                ConsumerRecords<String, GenericRecord> records = consumer.poll(Duration.ofMillis(10));
                records.forEach(record -> {
                    String key = record.key();
                    GenericRecord value = record.value();
                    System.out.println("Received Avro Record - Key: " + key + ", Value: " + value);
                });
            }
        } finally {
            consumer.close();
        }
    }
}

