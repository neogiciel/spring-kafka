package neogiciel.web;

import org.apache.kafka.clients.consumer.*;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.Producer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;


import java.time.Duration;
import java.util.Collections;
import java.util.Properties;


public class KafkaService {

	/*
	 * KafkaService()
	 */
	public KafkaService(){
		
		
	}
	
	/*
	 * receiveCallback()
	 */
	void sendMessage(String message) {
		
		Trace.log(0,"******************* Send *****************************") ;
    	//Creating Properties
    	Properties properties = new Properties();
    	properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    	properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    	properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
    	//Creating producers
    	Producer<String, String> producer = new KafkaProducer<>(properties);
    	//prepare the record
    	String recordValue = message;
    	ProducerRecord<String, String> record = new ProducerRecord<>("helloKafka", null, recordValue);
    	Trace.log(0," Envoi message :"+message) ;
    	//Sending message to Kafka Broker
    	producer.send(record);
    	producer.flush();
    	
    	
	}

		
	/*
	 * receiveCallback()
	 */
	void receiveCallback() {
		
		Trace.log(0,"******************* Reception *****************************") ;

    	//create kafka consumer
    	Properties properties = new Properties();
    	properties.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
    	properties.put(ConsumerConfig.GROUP_ID_CONFIG, "consumer-group");
    	properties.put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    	properties.put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class);
    	properties.put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, "earliest");
    	properties.put(ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG, false);
    	Consumer<String, String> consumer = new KafkaConsumer<>(properties);
    	//subscribe to topic
    	consumer.subscribe(Collections.singleton("helloKafka2"));
    	//poll the record from the topic
    	while (true) {
    		ConsumerRecords<String, String> records = consumer.poll(Duration.ofMillis(1000));
    		for (ConsumerRecord<String, String> record : records) {
    				Trace.log(0,"Message received: " + record.value()) ;
    		}
    		consumer.commitAsync();
    	}

	}
	
}
