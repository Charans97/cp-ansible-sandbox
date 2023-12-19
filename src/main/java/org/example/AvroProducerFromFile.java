package org.example;


import io.confluent.kafka.serializers.KafkaAvroSerializer;
import org.apache.avro.generic.GenericData;
import org.apache.avro.generic.GenericRecord;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.errors.SerializationException;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.List;
import java.util.Properties;

public class AvroProducerFromFile {

    public static void main(String[] args) {
        String bootstrapServers = "localhost:9092";
        String schemaRegistryUrl = "http://localhost:8081";
        String topic = "avro-topic";
        String recordsFile = "src/main/resources/avro/records.json";

        Properties props = new Properties();
        props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
        props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class.getName());
        props.put("schema.registry.url", schemaRegistryUrl);

        System.out.println("started");

        Producer<String, GenericRecord> producer = new KafkaProducer<>(props);

        try {
            // Create Avro records using generated class from JSON file
            List<String> jsonRecords = Files.readAllLines(new File(recordsFile).toPath());
            File file = new File(recordsFile);
            if (!file.exists()) {
                System.err.println("Error: File not found - " + recordsFile);
                return;
            }

            System.out.println("process1");

            for (String jsonRecord : jsonRecords) {
//                MyRecord avroRecord = JsonUtils.convertJsonToMyRecord(jsonRecord);
                MyRecord avroRecord = new MyRecord();
                avroRecord.setF1("value2");
                String key = "key"; // You can use a unique key for each record if needed
                ProducerRecord<String, GenericRecord> record = new ProducerRecord<>(topic, key, avroRecordToGenericRecord(avroRecord));
                producer.send(record);
            }

            System.out.println("process2");
            // When you're finished producing records, flush the producer to ensure it has all been written to Kafka
            // and then close the producer to free its resources.
            producer.flush();

            System.out.println("flushed");

        } catch (IOException | SerializationException e) {
            e.printStackTrace();
        } finally {
            producer.close();
        }
        System.out.println("closed");
    }

    private static GenericRecord avroRecordToGenericRecord(MyRecord avroRecord) {
        GenericRecord genericRecord = new GenericData.Record(avroRecord.getSchema());
        genericRecord.put("f1", avroRecord.getF1());
        genericRecord.put("f2", avroRecord.getF2());
        genericRecord.put("f3", avroRecord.getF3());
        // Add more fields as needed

        return genericRecord;
    }
}


