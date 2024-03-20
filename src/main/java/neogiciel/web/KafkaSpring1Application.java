package neogiciel.web;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class KafkaSpring1Application {

	public static void main(String[] args) {
		SpringApplication.run(KafkaSpring1Application.class, args);
		Trace.log(0,"****************************************************************************") ;
		Trace.log(0,"******************* Lancement de l application *****************************") ;
		Trace.log(0,"****************************************************************************") ;
	}

}
