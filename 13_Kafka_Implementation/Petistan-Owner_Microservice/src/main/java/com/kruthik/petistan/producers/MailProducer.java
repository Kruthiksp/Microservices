package com.kruthik.petistan.producers;

import java.time.Instant;
import java.util.UUID;

import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

import com.kruthik.petistan.dto.MailDTO;
import com.kruthik.petistan.dto.MailEvent;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class MailProducer {

	private final KafkaTemplate<String, MailEvent> kafkaTemplate;

	public String publishEvent(MailDTO mailDTO) {

		MailEvent mailEvent = new MailEvent(UUID.randomUUID().toString(), mailDTO, Instant.now());
		kafkaTemplate.send("mail-topics", mailEvent.eventId(), mailEvent).whenComplete((result, exception) -> {
			if (exception != null)
				System.out.println("Failed to send Event: " + exception);
			else {
				System.out.println("Successfully sent Event");
				
				var metaData = result.getRecordMetadata();
				
				System.out.println("topic: " + metaData.topic());
				System.out.println("Partition: " + metaData.partition());
				System.out.println("offset: " + metaData.offset());
			}
		});
		return mailEvent.eventId();
	}
}
