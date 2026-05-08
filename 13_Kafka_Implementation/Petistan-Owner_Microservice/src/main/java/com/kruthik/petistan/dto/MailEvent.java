package com.kruthik.petistan.dto;

import java.time.Instant;

public record MailEvent(String eventId, MailDTO mailDTO, Instant timestamp) {
	
}
