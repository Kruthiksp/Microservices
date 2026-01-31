package com.kruthik.petistan.service.impl;

import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.kruthik.petistan.dto.MailDTO;
import com.kruthik.petistan.service.MailService;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class MailServiceImpl implements MailService {

	private final RestClient restClient;

	@Override
	public String sendEmail(MailDTO mailDTO) {
		ResponseEntity<String> response = restClient.post()
				.uri("http://localhost:8083/mail/send")
				.body(mailDTO)
				.retrieve()
				.toEntity(String.class);
		
		return response.getBody();
	}

}
