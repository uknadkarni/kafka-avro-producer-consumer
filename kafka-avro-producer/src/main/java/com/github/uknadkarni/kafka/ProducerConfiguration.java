package com.github.uknadkarni.kafka;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;
import org.springframework.stereotype.Component;

import com.github.uknadkarni.kafka.model.Equity;

import io.confluent.kafka.serializers.AbstractKafkaAvroSerDeConfig;
import io.confluent.kafka.serializers.KafkaAvroSerializer;

@Component
public class ProducerConfiguration {

	@Value("${kafka.producer.bootstrap-servers}")
	private String bootstrapServers;
	
	@Value("${kafka.producer.schema-registry-url}")
	private String schemaRegistryUrl;
	

	public ProducerConfiguration() {
		// TODO Auto-generated constructor stub
	}
	
	@Bean
	public ProducerFactory<String, Equity> producerFactory(){
		Map<String, Object> props = new HashMap<>();
		props.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, bootstrapServers);
		props.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class);
		props.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, KafkaAvroSerializer.class);
		props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, schemaRegistryUrl);

//		props.put(AbstractKafkaAvroSerDeConfig.SCHEMA_REGISTRY_URL_CONFIG, "http://localhost:8081");

		return new DefaultKafkaProducerFactory<String, Equity>(props);
	}
	
	@Bean
	public KafkaTemplate<String, Equity> kafkaTemplate() {
		return new KafkaTemplate<String, Equity>(producerFactory());
	}
}
