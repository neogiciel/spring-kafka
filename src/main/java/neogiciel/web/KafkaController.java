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


/*
 * Lancement du docker
 * 
 * docker run -it --rm --name rabbitmq -p 5672:5672 -p 15672:15672 rabbitmq:3.11-management
 * 
 * Management:
 * http://localhost:15672/
 * guest /guest
 */

@RestController
public class KafkaController {

	private final static String QUEUE_NAME = "bus-neogiciel";
	
	@RequestMapping("/")
    public String home(){
        return "Hello World!";
    }

    
    @RequestMapping("/send")
    public String send(@RequestParam String id){
    	
    	KafkaService kafka = new KafkaService();
    	
    	for (int i=0; i<4; i++){
    		String message = "Nouvel evenement (id) = "+ id+"/ i = "+i;
    		kafka.sendMessage(message);
    		Trace.log(0," [x] Sent '" + message + "'") ;
    	 }
    	
    	return "send";
    }


    
    @RequestMapping("/rcv")
    public String rcv(){
    	Thread t = new Thread() {
            public void run() {
            	Trace.log(0,"Lancement du thread") ;
            	KafkaService kafka = new KafkaService();
            	kafka.receiveCallback();
            }
        };
        t.start();
        
    	
    	
    	return "rcv";
    
    }
    
}
