## <h1>Application Spring Boot Kafka</h1>
***
<table>
  <tr>
    <td><img src="https://blog.mossroy.fr/wp-content/uploads/2019/09/spring-boot-logo.png" alt="drawing" height="280px"/></td>
    <td><img src="https://www.streamthoughts.fr/img/logo-apache-kafka.png" alt="drawing" height="280px"/></td>
  </tr>
</table>

## Informations Générales
***
Mise en place d'un broker Kafka permettant la gestion des messages Asynchrone

## Technologies
***
Technologies utilisées:
* Java 17 
* Maven 3.6
* Spring-boot: 3.2

## Instalation
***
Deploiement de Kafka via docker compose
```
version: "3"
services:
  zookeeper:
    image: 'bitnami/zookeeper:latest'
    ports:
      - '2181:2181'
    environment:
      - ALLOW_ANONYMOUS_LOGIN=yes
  kafka:
    image: 'bitnami/kafka:latest'
    ports:
      - '9092:9092'
    environment:
      - KAFKA_BROKER_ID=1
      - KAFKA_LISTENERS=PLAINTEXT://:9092
      - KAFKA_ADVERTISED_LISTENERS=PLAINTEXT://127.0.0.1:9092
      - KAFKA_ZOOKEEPER_CONNECT=zookeeper:2181
      - ALLOW_PLAINTEXT_LISTENER=yes
    depends_on:
      - zookeeper
```

Lancement de Kafka
docker-compose up -d

Lancement de l'application Spring-boot<br>
```
$ mvn  clean
$ mvn spring-boot:run
```
Le service est accessible sur http://localhost:8080

## FAQs
***
**Serveur Kafka**<br>
Le seveur est accessible via http://localhost:9092





