package com.kruthik.petistan.consumer;

import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

import com.kruthik.petistan.dto.MailEvent;
import com.kruthik.petistan.service.MailService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
@RequiredArgsConstructor
public class MailConsumer {
	private final MailService mailService;

	@KafkaListener(topics = "mail-events", groupId = "petistan-mail-ms")
	public void consumeEvent(MailEvent mailEvent) {
		try {
			log.info("Received event: {}", mailEvent);
			String response = mailService.sendEmail(mailEvent.mailDTO());
			log.info("Mail sent for event {}: {}", mailEvent.eventId(), response);
		} catch (Exception ex) {
			log.error("Error processing event {}", mailEvent.eventId(), ex);
			throw ex;
		}
	}
}
