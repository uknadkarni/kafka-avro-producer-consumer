package com.github.uknadkarni.kafka.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.github.uknadkarni.kafka.model.Equity;

@RestController
public class ProducerController {
	
	
	@Value("${app.topic.name}")
	private String topic;
	
	private final Logger logger = LoggerFactory.getLogger(ProducerController.class);
	
	private KafkaTemplate<String, Equity> kafkaTemplate;

	public ProducerController(KafkaTemplate<String, Equity> kafkaTemplate) {
		this.kafkaTemplate = kafkaTemplate;
	}
	
	@PostMapping("/equities")
	public void create(@RequestBody Equity equity) {
		logger.info("Creating: {}", equity);
		this.kafkaTemplate.send(topic, equity.getTicker().toString(), equity);
	}

}
