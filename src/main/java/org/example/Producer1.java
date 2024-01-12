package org.example;


import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;
import org.apache.kafka.clients.producer.Producer;
import org.slf4j.LoggerFactory;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.logging.Logger;


public class Producer1 {

    public static void main(String[] args) throws InterruptedException {


        Properties prop = new Properties();

//        try {
//            FileInputStream input = new FileInputStream("/home/sumo/hackathon/clientl.properties");
//            prop.load(input);
//            input.close();
//        }catch (IOException e) {
//            e.printStackTrace();
//            return;
//        }
        prop.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        prop.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());
        prop.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class.getName());

        final KafkaProducer<String, String> producer = new KafkaProducer<String, String>(prop);

        System.out.println("started");

        for (int i=41;i<=50;i++){
           // int partitionNumber = i % 3;
            ProducerRecord<String, String> record = new ProducerRecord<>("stream_ip" , Integer.toString(i) , Integer.toString(i));
            producer.send(record);
            //Thread.sleep(1000 * 15);
        }

        producer.flush();
        System.out.println("flushed");
        producer.close();

    }
}
