package org.example;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.FileInputStream;
import java.util.Properties;

public class MessageProducer {

    public static void main(String[] args)  {


        Properties properties = new Properties();

        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG,"localhost:9092");
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

//        FileInputStream finputstream = new FileInputStream("C:/Kafka/javaproducerconsumer.properties");
//        properties.load(finputstream);
//        finputstream.close();
        System.out.println("Started");
        KafkaProducer<String, String> kafkaProducer = new KafkaProducer<String, String>(properties);
        ProducerRecord<String, String> producerRecord = new ProducerRecord<String, String>("test", "Hello World");
        kafkaProducer.send(producerRecord);

        System.out.println("flushed");
        kafkaProducer.flush();
        kafkaProducer.close();
    }
}
